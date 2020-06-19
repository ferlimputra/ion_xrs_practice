package com.xrs.sample.talkpublisher.services;

import com.google.inject.ImplementedBy;
import com.iontrading.isf.committer.spi.CommitterTransaction;
import com.iontrading.isf.commons.async.AsyncResult;

@ImplementedBy(ReviewServiceImpl.class)
public interface ReviewService {

  public AsyncResult<String> addReview();

  public AsyncResult<String> addReview(final CommitterTransaction transaction);
}
