package com.xrs.sample.persistence;

import javax.persistence.Entity;
import javax.persistence.Table;
import com.iontrading.isf.committer.spi.AbstractPersistablePublishableEntity;
import com.iontrading.talk.api.annotation.TalkProperty;
import com.iontrading.talk.api.annotation.TalkType;

@Entity
@Table(name = "TRADE")
@TalkType(name = "Trade", propertyOrder = {"tradeId", "instrumentId", "value", "qty", "verb"})
public class PersistableTrade extends AbstractPersistablePublishableEntity {

  private static final long serialVersionUID = 5020218447744179422L;

  @TalkProperty
  private String tradeId;

  @TalkProperty
  private String instrumentId;

  @TalkProperty
  private double value;

  @TalkProperty
  private double qty;

  @TalkProperty
  private String verb;

  public PersistableTrade() {
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
