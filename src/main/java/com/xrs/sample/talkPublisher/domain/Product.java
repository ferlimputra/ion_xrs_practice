package com.xrs.sample.talkpublisher.domain;

import com.iontrading.talk.api.annotation.Identifier;
import com.iontrading.talk.api.annotation.TalkProperty;
import com.iontrading.talk.api.annotation.TalkType;

@TalkType(propertyOrder = {"id", "name", "price", "qty"})
public class Product {

  @Identifier
  @TalkProperty
  private String id;

  @TalkProperty
  private String name;

  @TalkProperty
  private double price;

  @TalkProperty
  private int qty;

  public Product() {
  }

  public Product(final String id, final String name, final double price, final int qty) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.qty = qty;
  }

  public String getId() {
    return id;
  }

  public void setId(final String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(final double price) {
    this.price = price;
  }

  public int getQty() {
    return qty;
  }

  public void setQty(final int qty) {
    this.qty = qty;
  }

}
