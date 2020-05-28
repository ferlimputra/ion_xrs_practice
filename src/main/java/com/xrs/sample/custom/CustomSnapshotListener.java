package com.xrs.sample.custom;

import com.iontrading.xrs.api.IContext;
import com.iontrading.xrs.api.IModuleServiceLocator;
import com.iontrading.xrs.api.IQuery;
import com.iontrading.xrs.api.events.IEventFactory;
import com.iontrading.xrs.api.events.IEventMgr;
import com.iontrading.xrs.api.events.XRSEventException;
import com.iontrading.xrs.api.helper.SnapshotModuleHelper;

public class CustomSnapshotListener implements ICustomSnapshotListener {

  private SnapshotModuleHelper helper;
  private IQuery query;
  private IContext context;
  private IModuleServiceLocator locator;

  public CustomSnapshotListener(SnapshotModuleHelper helper, IQuery query) {
    this.helper = helper;
    this.query = query;
  }

  @Override
  public void onSnapshot(Item o) {
    final IEventMgr evtMgr = locator.getEvtManager();
    final IEventFactory evtFct = locator.getEventFactory();

    // try {
    // if (helper.canPlayQuery(query)) {
    // evtMgr.pushEvent(evtFct.createSnapshotObjectEvent(context, query, o));
    // }
    // } catch (InterruptedException | XRSEventException e) {
    // e.printStackTrace();
    // }
  }

}
