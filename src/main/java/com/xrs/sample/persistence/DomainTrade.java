package com.xrs.sample.persistence;

import java.util.Optional;
import com.iontrading.isf.committer.spi.DomainEntity;
import com.iontrading.isf.committer.spi.RevisionIdentifier;

public class DomainTrade implements DomainEntity {

  private RevisionIdentifier id;
  private final DomainTrade prev;

  private String tradeId;
  private String instrumentId;
  private double value;
  private double qty;
  private String verb;

  public DomainTrade() {
    this.prev = null;
  }

  public DomainTrade(DomainTrade prev) {
    this.prev = prev;
  }

  @Override
  public RevisionIdentifier getIdentifier() {
    return id;
  }

  @Override
  public void setIdentifier(final RevisionIdentifier newId) {
    id = newId;
  }

  @Override
  public RevisionIdentifier getPreviousIdentifier() {
    return Optional.ofNullable(prev).map(p -> p.getIdentifier()).orElse(null);
  }

  public RevisionIdentifier getId() {
    return id;
  }

  public void setId(RevisionIdentifier id) {
    this.id = id;
  }

  public DomainTrade getPrev() {
    return prev;
  }

  public String getTradeId() {
    return tradeId;
  }

  public void setTradeId(String tradeId) {
    this.tradeId = tradeId;
  }

  public String getInstrumentId() {
    return instrumentId;
  }

  public void setInstrumentId(String instrumentId) {
    this.instrumentId = instrumentId;
  }

  public double getValue() {
    return value;
  }

  public void setValue(double value) {
    this.value = value;
  }

  public double getQty() {
    return qty;
  }

  public void setQty(double qty) {
    this.qty = qty;
  }

  public String getVerb() {
    return verb;
  }

  public void setVerb(String verb) {
    this.verb = verb;
  }

}
