package com.xrs.sample.custom;

import javax.inject.Inject;
import com.iontrading.talk.api.annotation.TalkFunction;
import com.iontrading.talk.api.annotation.TalkParam;

public class ItemService {

  @Inject
  public ItemService() {
    //
  }

  @TalkFunction
  public void addItem(@TalkParam(name = "Item") Item item) {
    // System.out.println("""
    // Function 'addItem' is called.
    // New Item added:
    // - Code: %s
    // - Description: %s
    // - Price: %f
    // - Qty: %d
    // """.formatted(item.getCode(), item.getDescription(), item.getPrice(), item.getQty()));
  }
}
