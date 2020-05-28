package com.xrs.sample.custom;

import com.iontrading.xrs.api.IContext;
import com.iontrading.xrs.api.IModuleServiceLocator;
import com.iontrading.xrs.api.IQuery;
import com.iontrading.xrs.api.ISnapshotModule;
import com.iontrading.xrs.api.ModuleStatus;
import com.iontrading.xrs.api.XRSGenericResult;
import com.iontrading.xrs.api.XRSStatus;
import com.iontrading.xrs.api.events.IEvent;
import com.iontrading.xrs.api.events.IEventFactory;
import com.iontrading.xrs.api.events.IEventMgr;
import com.iontrading.xrs.api.events.XRSEventException;
import com.iontrading.xrs.api.helper.SnapshotModuleHelper;
import com.iontrading.xrs.api.helper.SnapshotModuleHelperImpl;

public class CustomSnapshotModule implements ISnapshotModule {

  private static final String MODULE_NAME = "Custom Snapshot Module";

  private volatile IModuleServiceLocator locator;
  private volatile IContext context;
  private volatile CustomStaticDatabase db;
  private volatile SnapshotModuleHelper helper;
  private volatile ModuleStatus status = new ModuleStatus(XRSStatus.STARTING, "Not started");

  @Override
  public void init(IContext context, IModuleServiceLocator locator) {
    System.out.println("Initializing %s".formatted(MODULE_NAME));

    this.context = context;
    this.locator = locator;
    this.helper = SnapshotModuleHelperImpl.create(locator);

    this.db = CustomStaticDatabase.getInstance();
    db.init(context, locator.getConfig().getXRSSource());

    this.status = new ModuleStatus(XRSStatus.RUNNING, "%s running".formatted(MODULE_NAME));

    try {
      final IEventMgr evtMgr = locator.getEvtManager();
      final IEventFactory evtFct = locator.getEventFactory();
      final IEvent evt = evtFct.createModuleStatusEvent(context, this);
      evtMgr.pushEvent(evt);
    } catch (XRSEventException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void shutDown() {
    helper.shutDown();
  }

  @Override
  public String getName() {
    return MODULE_NAME;
  }

  @Override
  public ModuleStatus getModuleStatus() {
    return status;
  }

  @Override
  public String getDetails() {
    return "...";
  }

  @Override
  public XRSGenericResult prepareQuery(final IQuery query) {
    if (!status.getStatus().equals(XRSStatus.RUNNING)) {
      return XRSGenericResult.error("%s is not running".formatted(MODULE_NAME));
    }
    return XRSGenericResult.ok();
  }

  @Override
  public void onQueryReadyForSnapshot(final IQuery query) {
    helper.onQueryReadyForSnapshot(query);

    final IEventMgr evtMgr = locator.getEvtManager();
    final IEventFactory evtFct = locator.getEventFactory();

    try {
      evtMgr.pushEvent(evtFct.createSnapshotStartEvent(context, query));
    } catch (XRSEventException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void closeQuery(final IQuery query) {
    helper.closeQuery(query);
  }

  @Override
  public void pauseQuery(final IQuery query) {
    helper.pauseQuery(query);
  }

  @Override
  public void resumeQuery(final IQuery query) {
    helper.resumeQuery(query);
  }

}
