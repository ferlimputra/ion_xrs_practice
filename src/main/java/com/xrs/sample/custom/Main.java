package com.xrs.sample.custom;

import com.iontrading.isf.applicationserver.spi.AS;

public class Main {

  public static void main(final String[] args) {
    // new Main(args, "-42c86e8abd070669", "CustomXRS").start();
    AS.createLaunchConfiguration().withArgs(args).withModules(CustomModule.class)
        .withBoostrapListener(CustomBootStrapListener.class).launch();
  }
}
