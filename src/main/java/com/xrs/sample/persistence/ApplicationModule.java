package com.xrs.sample.persistence;

import javax.inject.Singleton;
import com.google.inject.AbstractModule;
import com.iontrading.isf.committer.guice.PersistenceNotificationModule;
import com.iontrading.isf.modules.annotation.ModuleDescriptor;
import com.iontrading.talk.api.guice.TalkModule;

@ModuleDescriptor(requires = {PersistenceNotificationModule.class})
public class ApplicationModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(TradeService.class).in(Singleton.class);
    TalkModule.exportFunctions(binder(), TradeService.class);
  }
}
