package com.xrs.sample.custom;

import com.iontrading.talk.api.annotation.TalkProperty;
import com.iontrading.talk.api.annotation.TalkType;

@TalkType(propertyOrder = {"id", "code", "description", "price", "qty"})
public class Item {

  @TalkProperty
  private String id;

  @TalkProperty
  private String code;

  @TalkProperty
  private String description;

  @TalkProperty
  private double price;

  @TalkProperty
  private int qty;

  public Item() {
  }

  public Item(final String id, final String code, final String description, final double price,
      final int qty) {
    this.id = id;
    this.code = code;
    this.description = description;
    this.price = price;
    this.qty = qty;
  }

  public String getId() {
    return this.id;
  }

  public void setId(final String id) {
    this.id = id;
  }

  public String getCode() {
    return this.code;
  }

  public void setCode(final String code) {
    this.code = code;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(final String description) {
    this.description = description;
  }

  public double getPrice() {
    return this.price;
  }

  public void setPrice(final double price) {
    this.price = price;
  }

  public int getQty() {
    return this.qty;
  }

  public void setQty(final int qty) {
    this.qty = qty;
  }
}
