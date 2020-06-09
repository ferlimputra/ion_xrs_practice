package com.xrs.sample.talkpublisher;

import java.time.Instant;
import com.iontrading.isf.applicationserver.spi.AS;
import com.xrs.sample.talkpublisher.modules.ProductFunctionExporterModule;
import com.xrs.sample.talkpublisher.modules.ProductPublisherModule;

public class Main {
  public static void main(String[] args) {
    AS.createLaunchConfiguration().withArgs(args)
        .withModules(ProductFunctionExporterModule.class, ProductPublisherModule.class)
        .withComponentInfo("Talk_Publisher", "Desc", "1.0", Instant.now().toString()).launch();
  }
}
