package com.xrs.sample.talkPublisher.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.iontrading.isf.modules.annotation.ModuleDescriptor;
import com.iontrading.talk.ionbus.guice.TalkIonBusModule;
import com.xrs.sample.talkPublisher.services.ProductPublisherService;
import com.xrs.sample.talkPublisher.services.ProductPublisherServiceImpl;

@ModuleDescriptor(requires = TalkIonBusModule.class)
public class ProductPublisherModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(ProductPublisherService.class).to(ProductPublisherServiceImpl.class).in(Singleton.class);
  }
}
