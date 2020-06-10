package com.xrs.sample.custom;

import java.util.ArrayList;
import com.iontrading.mkv.enums.MkvFieldType;
import com.iontrading.xrs.api.DefaultXRSField;
import com.iontrading.xrs.api.IContext;
import com.iontrading.xrs.api.IModuleServiceLocator;
import com.iontrading.xrs.api.IStructureModule;
import com.iontrading.xrs.api.IXRSField;
import com.iontrading.xrs.api.ModuleStatus;
import com.iontrading.xrs.api.XRSStatus;
import com.iontrading.xrs.api.events.IEvent;
import com.iontrading.xrs.api.events.IEventFactory;
import com.iontrading.xrs.api.events.IEventMgr;
import com.iontrading.xrs.api.events.XRSEventException;

public class CustomStructureModule implements IStructureModule {

  private static final String MODULE_NAME = "Custom Structure Module";

  private final ArrayList<IXRSField> fields = new ArrayList<>();

  private IContext context;
  private IModuleServiceLocator locator;
  private XRSStatus status = XRSStatus.STARTING;
  private String statusMsg = "";

  @Override
  public void init(final IContext context, final IModuleServiceLocator locator) {
    this.context = context;
    this.locator = locator;

    fields.add(new DefaultXRSField("Id", MkvFieldType.STR));
    fields.add(new DefaultXRSField("Name", MkvFieldType.STR));
    fields.add(new DefaultXRSField("Price", MkvFieldType.REAL));
    fields.add(new DefaultXRSField("Qty", MkvFieldType.INT));
    fields.add(new DefaultXRSField("Added", MkvFieldType.DATE));

    updateStatus(XRSStatus.RUNNING, String.format("Fields count: %d", fields.size()));
  }

  @Override
  public void shutDown() {
    updateStatus(XRSStatus.DISCONNECTED, String.format("%s Shutting down", MODULE_NAME));
  }

  @Override
  public String getName() {
    return MODULE_NAME;
  }

  @Override
  public ModuleStatus getModuleStatus() {
    return new ModuleStatus(status, statusMsg);
  }

  @Override
  public String getDetails() {
    return String.format("%s Details", MODULE_NAME);
  }

  @Override
  public Iterable<IXRSField> getFieldsStructure() {
    return fields;
  }

  private void updateStatus(final XRSStatus pStatus, final String pStatusMsg) {
    status = pStatus;
    statusMsg = pStatusMsg;

    try {
      final IEventMgr evtMgr = locator.getEvtManager();
      final IEventFactory evtFct = locator.getEventFactory();
      final IEvent evt = evtFct.createModuleStatusEvent(context, this);
      evtMgr.pushEvent(evt);
    } catch (final XRSEventException e) {
      e.printStackTrace();
    }
  }
}
