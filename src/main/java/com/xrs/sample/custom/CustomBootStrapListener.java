package com.xrs.sample.custom;

import com.iontrading.isf.applicationserver.spi.BootstrapListener;

public class CustomBootStrapListener implements BootstrapListener {

  @Override
  public void onSuccess() {
    System.out.println("Component registered");
  }

  @Override
  public void onFailure(Throwable t) {
    System.out.println("Failed to register component. Message: %s".formatted(t.getMessage()));
    t.printStackTrace();
  }

}
