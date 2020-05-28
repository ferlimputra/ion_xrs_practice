package com.xrs.sample.custom;

import com.iontrading.xrs.api.IContext;
import com.iontrading.xrs.api.IModuleServiceLocator;
import com.iontrading.xrs.api.IRealTimeModule;
import com.iontrading.xrs.api.ModuleStatus;
import com.iontrading.xrs.api.XRSStatus;

public class CustomRealTimeModule implements IRealTimeModule, ICustomRealTimeListener {

  private static final String MODULE_NAME = "Custom RealTime Module";

  private volatile IContext context;
  private volatile IModuleServiceLocator locator;
  private volatile CustomStaticDatabase database;

  @Override
  public void init(IContext context, IModuleServiceLocator locator) {
    this.context = context;
    this.locator = locator;
    database = CustomStaticDatabase.getInstance();
    database.init(context, locator.getConfig().getXRSSource());
    database.setRTListener(this);
  }

  @Override
  public void shutDown() {
    // TODO Auto-generated method stub

  }

  @Override
  public String getName() {
    return MODULE_NAME;
  }

  @Override
  public ModuleStatus getModuleStatus() {
    return new ModuleStatus(XRSStatus.RUNNING, "%s running".formatted(MODULE_NAME));
  }

  @Override
  public String getDetails() {
    return "%s details".formatted(MODULE_NAME);
  }

  @Override
  public void onAdd(Item o) {
    // TODO Auto-generated method stub

  }

  @Override
  public void onUpdate(Item o) {
    // TODO Auto-generated method stub

  }

}
