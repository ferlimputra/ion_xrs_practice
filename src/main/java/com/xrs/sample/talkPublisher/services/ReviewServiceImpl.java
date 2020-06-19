package com.xrs.sample.talkpublisher.services;

import java.util.Collection;
import com.google.inject.Inject;
import com.iontrading.isf.committer.spi.CommitterListener;
import com.iontrading.isf.committer.spi.CommitterTransaction;
import com.iontrading.isf.committer.spi.CommitterTransactionFactory;
import com.iontrading.isf.committer.spi.DomainEntity;
import com.iontrading.isf.commons.async.AsyncResult;
import com.iontrading.isf.commons.async.AsyncResultPromise;
import com.iontrading.isf.commons.async.AsyncResults;
import com.xrs.sample.talkpublisher.domain.Review;

public class ReviewServiceImpl implements ReviewService {

  @Inject
  private CommitterTransactionFactory committer;

  @Override
  public AsyncResult<String> addReview() {
    final AsyncResultPromise<String> result = AsyncResults.create();
    final CommitterTransaction transaction = committer.create();
    final Review review = new Review("REV_1", "John", 10);

    transaction.addOrUpdate(review);
    transaction.commit(new CommitterListener() {

      @Override
      public void onRollback(final Collection<? extends DomainEntity> entities,
          final Throwable reason) {
        System.out
            .println(String.format("Rolling back transaction. Reason: %s", reason.getMessage()));
        result.failure(reason);
      }

      @Override
      public void onPending(final Collection<? extends DomainEntity> entities) {
        //
      }

      @Override
      public void onCommit(final Collection<? extends DomainEntity> entities) {
        System.out.println("Transaction Committed successfully.");
        result.success(review.getReviewId());
      }
    });

    return result;
  }

  @Override
  public AsyncResult<String> addReview(final CommitterTransaction transaction) {
    final AsyncResultPromise<String> result = AsyncResults.create();
    try {
      final Review review = new Review("REV_1", "John", 10);
      transaction.addOrUpdate(review);
      result.success(review.getReviewId());
    } catch (Exception e) {
      result.failure(e.getCause());
    }
    return result;
  }

}
