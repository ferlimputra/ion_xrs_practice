package com.xrs.sample.custom;

import com.iontrading.isf.applicationserver.spi.BootstrapListener;

public class CustomBootStrapListener implements BootstrapListener {

  @Override
  public void onSuccess() {
    System.out.println("Component registered");
  }

  @Override
  public void onFailure(Throwable t) {
    System.out.println(String.format("Failed to register component. Message: %s", t.getMessage()));
    t.printStackTrace();
  }

}
