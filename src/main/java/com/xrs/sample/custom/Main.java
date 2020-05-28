package com.xrs.sample.custom;

import com.iontrading.isf.applicationserver.spi.AS;
import com.iontrading.jmix.application.Application;
import com.iontrading.jmix.application.IServiceLocator;
import com.iontrading.jmix.application.IStartupConfigurator;
import com.iontrading.mkv.exceptions.MkvException;
import com.iontrading.xrs.api.XRSStatus;
import com.iontrading.xrs.api.lib.ContextInitInfo;
import com.iontrading.xrs.api.lib.ContextModuleCollection;
import com.iontrading.xrs.api.lib.IXRSStatusUpdateListener;
import com.iontrading.xrs.api.lib.XRSLib;
import com.iontrading.xrs.api.lib.XRSLibConfigurationException;

public class Main extends Application implements IXRSStatusUpdateListener {

  private static final String DEFAULT_CURRENCY = "IDR";
  private static final String DEFAULT_SOURCE = "PUTRA_SOURCE";
  private static final String CONTEXT_NAME = "Store";
  private static final String OBJECT_NAME = "Item";
  private static final String SERVICE_NAME = "ItemService";

  private final XRSLib xrs;

  public Main(final String[] args, final String componentKey, final String logCategory) {
    super(args, componentKey, logCategory);
    System.out.println("""
        --- Constructor ---
        Initialize the Custom XRS Framework""");
    xrs = new XRSLib();
  }

  @Override
  public void beforeStart(final IStartupConfigurator configurator) {
    System.out.println("--- Before Start ---");
    super.beforeStart(configurator);
  }

  @Override
  public void afterStart(final IServiceLocator locator) throws MkvException {
    System.out.println("--- After Start ---");
    super.afterStart(locator);

    System.out.println("Registering the context");
    xrs.registerContext(getContext());

    System.out.println("Starting XRS");
    try {
      xrs.start(this);
    } catch (XRSLibConfigurationException e) {
      System.out.println("Failed to Start XRS");
      e.printStackTrace();
    }
  }

  @Override
  public void onShutDown(final IServiceLocator locator) {
    System.out.println("--- Shutting Down ---");
    xrs.stop();
  }

  @Override
  public void onStatusUpdate(final XRSStatus status, final String statusMsg) {
    System.out.println("Status Update: %s (%s)".formatted(statusMsg, status.toString()));
  }

  private ContextInitInfo getContext() {
    final ContextModuleCollection moduleCollection = new ContextModuleCollection();
    moduleCollection.setStructureModule(new CustomStructureModule());
    moduleCollection.setSnapshotModule(new CustomSnapshotModule());
    moduleCollection.setRealTimeModule(new CustomRealTimeModule());

    final ContextInitInfo contextInitInfo =
        new ContextInitInfo(CONTEXT_NAME, OBJECT_NAME, true, moduleCollection);
    contextInitInfo.exportService(SERVICE_NAME);
    return contextInitInfo;
  }

  public static void main(final String[] args) {
    // new Main(args, "-42c86e8abd070669", "CustomXRS").start();
    AS.createLaunchConfiguration().withArgs(args).withModules(CustomModule.class)
        .withBoostrapListener(CustomBootStrapListener.class).launch();
  }
}
