package com.xrs.sample.talkpublisher.services;

import com.iontrading.isf.commons.async.AsyncResult;
import com.iontrading.talk.api.annotation.TalkFunction;
import com.iontrading.talk.api.annotation.TalkParam;

public interface BookService {

  @TalkFunction
  public AsyncResult<String> addBook(@TalkParam(name = "bookId") String bookId,
      @TalkParam(name = "name") String name, @TalkParam(name = "price") double price,
      @TalkParam(name = "qty") int qty);

}
