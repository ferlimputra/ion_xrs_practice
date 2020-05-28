package com.xrs.sample.persistence;

import java.time.LocalDate;
import com.iontrading.isf.applicationserver.spi.AS;

public class App {

  public App(String[] args) {
    AS.createLaunchConfiguration().withArgs(args)
        .withModules(ApplicationModule.class, ApplicationPNConfiguration.class).withComponentInfo(
            "TradeProcessor", "Trade Processor test", "1.0", LocalDate.now().toString())
        .launch();
  }

  public static void main(String[] args) {
    new App(args);
  }
}
