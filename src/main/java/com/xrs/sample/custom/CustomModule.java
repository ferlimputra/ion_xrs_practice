package com.xrs.sample.custom;

import com.google.inject.AbstractModule;
import com.iontrading.isf.modules.annotation.ModuleDescriptor;
import com.iontrading.talk.api.guice.TalkModule;
import com.iontrading.xrs.api.ContextInitInfoProvider;
import com.iontrading.xrs.api.lib.ContextInitInfo;
import com.iontrading.xrs.api.lib.ContextModuleCollection;
import com.iontrading.xrs.guice.XrsModule;

@ModuleDescriptor(requires = {XrsModule.class})
public class CustomModule extends AbstractModule {

  private static final String CONTEXT_NAME = "Store";
  private static final String OBJECT_NAME = "Item";
  private static final String SERVICE_NAME = "ItemService";

  @Override
  protected void configure() {
    // Register XRS
    System.out.println("Registering Xrs Module");
    XrsModule.registerContext(binder(), new ContextInitInfoProvider() {

      @Override
      public ContextInitInfo getContext() {
        final ContextModuleCollection moduleCollection = new ContextModuleCollection();
        moduleCollection.setStructureModule(new CustomStructureModule());
        moduleCollection.setSnapshotModule(new CustomSnapshotModule());
        moduleCollection.setRealTimeModule(new CustomRealTimeModule());

        final ContextInitInfo contextInitInfo =
            new ContextInitInfo(CONTEXT_NAME, OBJECT_NAME, true, moduleCollection);
        contextInitInfo.exportService(SERVICE_NAME);
        return contextInitInfo;
      }
    });

    // Publish functions
    System.out.println("Publishing functions...");
    TalkModule.exportFunctions(binder(), ItemService.class);
  }
}
