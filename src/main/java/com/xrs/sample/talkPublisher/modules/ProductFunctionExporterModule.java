package com.xrs.sample.talkPublisher.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.iontrading.talk.api.guice.TalkModule;
import com.xrs.sample.talkPublisher.services.ProductService;
import com.xrs.sample.talkPublisher.services.ProductServiceImpl;

public class ProductFunctionExporterModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(ProductService.class).to(ProductServiceImpl.class).in(Singleton.class);
    TalkModule.exportFunctions(binder(), ProductService.class);
  }
}
