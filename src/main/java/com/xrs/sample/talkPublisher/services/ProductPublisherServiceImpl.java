package com.xrs.sample.talkpublisher.services;

import javax.inject.Inject;
import com.iontrading.talk.api.PublishInfo;
import com.iontrading.talk.api.Publisher;
import com.xrs.sample.talkpublisher.data.ProductDatabase;
import com.xrs.sample.talkpublisher.domain.Product;

public class ProductPublisherServiceImpl implements ProductPublisherService {

  @Inject
  private Publisher publisher;

  @Override
  public void publishProduct() {
    if (ProductDatabase.products.isEmpty()) {
      throw new IllegalStateException("Product list is empty");
    }

    final Product product = ProductDatabase.products.get(0);
    final PublishInfo info = publisher.publish(product);
    System.out.println(String.format("Product published. Id: %s", info.getFullId()));
  }

  @Override
  public void publishProductList() {
    if (ProductDatabase.products.isEmpty()) {
      throw new IllegalStateException("Product list is empty");
    }

    PublishInfo info =
        publisher.publishToList(Product.class, ProductDatabase.products, "Product_Chain");
    System.out.println(String.format("Product list published. Chain Id: %s", info.getFullId()));
  }
}
