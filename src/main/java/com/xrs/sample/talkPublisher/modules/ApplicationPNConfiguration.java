package com.xrs.sample.talkpublisher.modules;

import com.iontrading.isf.committer.spi.AbstractPNConfiguration;
import com.iontrading.isf.committer.spi.PNConfigurator;
import com.xrs.sample.talkpublisher.domain.Book;
import com.xrs.sample.talkpublisher.domain.Review;

public class ApplicationPNConfiguration extends AbstractPNConfiguration {

  @Override
  public void onConfigure(final PNConfigurator configurator) {
    configurator.registerPersistable(Book.class).notRevisionable().register();
    configurator.registerDomain(Book.class).register();

    configurator.registerPersistable(Review.class).notRevisionable().register();
    configurator.registerDomain(Review.class).register();
  }

}
