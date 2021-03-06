package com.xrs.sample.talkpublisher.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.iontrading.talk.api.guice.TalkModule;
import com.xrs.sample.talkpublisher.services.BookService;
import com.xrs.sample.talkpublisher.services.BookServiceImpl;
import com.xrs.sample.talkpublisher.services.ProductService;
import com.xrs.sample.talkpublisher.services.ProductServiceImpl;

public class ProductFunctionExporterModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(ProductService.class).to(ProductServiceImpl.class).in(Singleton.class);
    bind(BookService.class).to(BookServiceImpl.class).in(Singleton.class);
    TalkModule.exportFunctions(binder(), ProductService.class);
    TalkModule.exportFunctions(binder(), BookService.class);
  }
}
