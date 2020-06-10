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
import com.xrs.sample.talkpublisher.domain.Book;

public class BookServiceImpl implements BookService {

  @Inject
  private CommitterTransactionFactory committer;

  @Override
  public AsyncResult<String> addBook(String bookId, String name, double price, int qty) {
    final AsyncResultPromise<String> result = AsyncResults.create();
    final CommitterTransaction transaction = committer.create();

    final Book newBook = new Book(bookId, name, price, qty);
    transaction.addOrUpdate(newBook);
    transaction.commit(new CommitterListener() {

      @Override
      public void onRollback(Collection<? extends DomainEntity> entities, Throwable reason) {
        System.out
            .println(String.format("Rolling back transaction. Reason: %s", reason.getMessage()));
        result.failure(reason);
      }

      @Override
      public void onPending(Collection<? extends DomainEntity> entities) {
        System.out.println("Transaction is pending");
      }

      @Override
      public void onCommit(Collection<? extends DomainEntity> entities) {
        System.out.println("Transaction Committed successfully.");
        result.success(newBook.getBookId());
      }
    });
    return result;
  }

}
