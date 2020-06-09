package com.xrs.sample.talkpublisher.services;

import com.iontrading.isf.commons.async.AsyncResult;
import com.iontrading.talk.api.annotation.TalkFunction;
import com.iontrading.talk.api.annotation.TalkParam;
import com.xrs.sample.talkpublisher.domain.Product;

public interface ProductService {

  @TalkFunction
  public AsyncResult<String> addProduct(@TalkParam(name = "Product") Product product);

  @TalkFunction
  public void publishProduct();

  @TalkFunction
  public void publishProductList();
}
