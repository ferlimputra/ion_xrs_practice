package com.xrs.sample.talkPublisher;

import java.time.Instant;
import com.iontrading.isf.applicationserver.spi.AS;
import com.xrs.sample.talkPublisher.modules.ProductFunctionExporterModule;
import com.xrs.sample.talkPublisher.modules.ProductPublisherModule;

public class Main {
  public static void main(String[] args) {
    AS.createLaunchConfiguration().withArgs(args)
        .withModules(ProductFunctionExporterModule.class, ProductPublisherModule.class)
        .withComponentInfo("Talk_Publisher", "Desc", "1.0", Instant.now().toString()).launch();
  }
}
