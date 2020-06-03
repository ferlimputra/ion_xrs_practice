package com.xrs.sample.talkPublisher.services;

import javax.inject.Inject;
import com.iontrading.talk.api.PublishInfo;
import com.iontrading.talk.api.Publisher;
import com.xrs.sample.talkPublisher.data.ProductDatabase;
import com.xrs.sample.talkPublisher.domain.Product;

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
    System.out.println("Product published. Id: %s".formatted(info.getFullId()));
  }

  @Override
  public void publishProductList() {
    if (ProductDatabase.products.isEmpty()) {
      throw new IllegalStateException("Product list is empty");
    }

    final PublishInfo info =
        publisher.publishToList(Product.class, ProductDatabase.products, "Product_Chain");
    System.out.println("Product list published. Chain Id: %s".formatted(info.getFullId()));
  }
}
