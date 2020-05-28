package com.xrs.sample.persistence;

import com.iontrading.isf.committer.spi.MapperException;
import com.iontrading.isf.committer.spi.PersistableFromDomainMapper;
import com.iontrading.isf.committer.spi.PersistableToDomainMapper;

public class TradeMapper implements PersistableFromDomainMapper<PersistableTrade, DomainTrade>,
    PersistableToDomainMapper<PersistableTrade, DomainTrade> {

  @Override
  public DomainTrade read(PersistableTrade input) throws MapperException {
    DomainTrade trade = new DomainTrade();
    trade.setTradeId(input.getTradeId());
    trade.setInstrumentId(input.getInstrumentId());
    trade.setQty(input.getQty());
    trade.setValue(input.getValue());
    trade.setVerb(input.getVerb());
    return trade;
  }

  @Override
  public Class<? extends DomainTrade> getDomainEntityClass() {
    return DomainTrade.class;
  }

  @Override
  public PersistableTrade write(DomainTrade input) throws MapperException {
    PersistableTrade persistable = new PersistableTrade();
    persistable.setTradeId(input.getTradeId());
    persistable.setInstrumentId(input.getInstrumentId());
    persistable.setQty(input.getQty());
    persistable.setValue(input.getValue());
    persistable.setVerb(input.getVerb());
    return persistable;
  }

  @Override
  public Class<? extends PersistableTrade> getPersistableEntityClass() {
    return PersistableTrade.class;
  }

}
