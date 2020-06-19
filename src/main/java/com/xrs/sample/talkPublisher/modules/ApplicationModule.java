package com.xrs.sample.talkpublisher.modules;

import com.google.inject.AbstractModule;
import com.iontrading.isf.committer.guice.PersistenceNotificationModule;
import com.iontrading.isf.modules.annotation.ModuleDescriptor;

@ModuleDescriptor(requires = {PersistenceNotificationModule.class})
public class ApplicationModule extends AbstractModule {


}
