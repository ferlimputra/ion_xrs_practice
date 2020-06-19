package com.xrs.sample.talkpublisher.domain;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import com.iontrading.isf.committer.spi.AbstractPersistablePublishableEntity;
import com.iontrading.isf.committer.spi.DomainEntity;
import com.iontrading.isf.committer.spi.RevisionIdentifier;
import com.iontrading.talk.api.annotation.TalkProperty;
import com.iontrading.talk.api.annotation.TalkType;

@Entity
@Table(name = "BOOK")
@TalkType(name = "Book")
public class Book extends AbstractPersistablePublishableEntity implements DomainEntity {
  private static final long serialVersionUID = 1L;

  @Transient
  private transient RevisionIdentifier revId;

  @TalkProperty
  private String bookId;

  @TalkProperty
  private String name;

  @TalkProperty
  private double price;

  @TalkProperty
  private int qty;

  public Book() {
  }

  public Book(final String bookId, final String name, final double price, final int qty) {
    this.bookId = bookId;
    this.name = name;
    this.price = price;
    this.qty = qty;
  }

  public String getBookId() {
    return bookId;
  }

  public void setBookId(final String bookId) {
    this.bookId = bookId;
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

  @Override
  @Transient
  public RevisionIdentifier getIdentifier() {
    return revId;
  }

  @Override
  public void setIdentifier(RevisionIdentifier newId) {
    this.revId = newId;
  }

  @Override
  @Transient
  public RevisionIdentifier getPreviousIdentifier() {
    return revId;
  }

}
