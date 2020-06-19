package com.xrs.sample.talkpublisher.services;

import java.util.Collection;
import javax.inject.Inject;
import com.iontrading.isf.committer.spi.CommitterListener;
import com.iontrading.isf.committer.spi.CommitterTransaction;
import com.iontrading.isf.committer.spi.CommitterTransactionFactory;
import com.iontrading.isf.committer.spi.DomainEntity;
import com.iontrading.isf.commons.async.AsyncResult;
import com.iontrading.isf.commons.async.AsyncResultPromise;
import com.iontrading.isf.commons.async.AsyncResults;
import com.iontrading.isf.commons.callback.Callback;
import com.xrs.sample.talkpublisher.domain.Book;

public class BookServiceImpl implements BookService {

  @Inject
  private CommitterTransactionFactory committer;

  @Inject
  private ReviewService reviewService;

  @Override
  public AsyncResult<String> addBook(String bookId, String name, double price, int qty) {
    final AsyncResultPromise<String> asyncResult = AsyncResults.create();
    final CommitterTransaction transaction = committer.create();

    final Book newBook = new Book(bookId, name, price, qty);
    // final Review review1 = new Review("REV_1", "John", 10);
    // final Review review2 = new Review("REV_2", "Jane", 5);

    transaction.addOrUpdate(newBook);
    // transaction.addOrUpdate(review1);
    // transaction.addOrUpdate(review2);

    // Transaction propagation: requires new
    // reviewService.addReview().addCallback(new Callback<String>() {

    // Transaction propagation: required
    reviewService.addReview(transaction).addCallback(new Callback<String>() {

      @Override
      public void onSuccess(String result) {
        System.out.println(String
            .format("Review (%s) inserted successfully. Committing outer transaction...", result));
        transaction.commit(new CommitterListener() {

          @Override
          public void onRollback(Collection<? extends DomainEntity> entities, Throwable reason) {
            System.out.println(
                String.format("Rolling back transaction. Reason: %s", reason.getMessage()));
            asyncResult.failure(reason);
          }

          @Override
          public void onPending(Collection<? extends DomainEntity> entities) {
            //
          }

          @Override
          public void onCommit(Collection<? extends DomainEntity> entities) {
            System.out.println("Transaction Committed successfully.");
            asyncResult.success(newBook.getBookId());
          }
        });
      }

      @Override
      public void onFailure(Throwable exception) {
        System.out.println(String.format("Inner transaction failed to commit. Reason: %s",
            exception.getMessage()));
        exception.printStackTrace();
      }
    });
    return asyncResult;
  }

}
