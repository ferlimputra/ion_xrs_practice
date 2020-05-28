package com.xrs.sample.persistence;

import com.iontrading.isf.committer.spi.AbstractPNConfiguration;
import com.iontrading.isf.committer.spi.PNConfigurator;

public class ApplicationPNConfiguration extends AbstractPNConfiguration {

  @Override
  public void onConfigure(PNConfigurator configurator) {
    configurator.registerPersistable(PersistableTrade.class).register();

    configurator.registerDomain(DomainTrade.class).withPersistable(PersistableTrade.class)
        .fromDomain(TradeMapper.class).toDomain(TradeMapper.class).register();
  }
}
