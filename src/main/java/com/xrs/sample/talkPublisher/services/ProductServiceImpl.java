package com.xrs.sample.talkpublisher.services;

import javax.inject.Inject;
import com.iontrading.isf.commons.async.AsyncResult;
import com.iontrading.isf.commons.async.AsyncResultPromise;
import com.iontrading.isf.commons.async.AsyncResults;
import com.iontrading.isf.commons.callback.Callback;
import com.xrs.sample.talkpublisher.data.ProductDatabase;
import com.xrs.sample.talkpublisher.domain.Product;

public class ProductServiceImpl implements ProductService {

  @Inject
  private ProductPublisherService publisherService;

  @Override
  public AsyncResult<String> addProduct(final Product product) {
    System.out.println("Function AddProduct is called");

    final AsyncResultPromise<String> asyncResult = AsyncResults.create();
    asyncResult.addCallback(new Callback<String>() {

      @Override
      public void onSuccess(String result) {
        System.out.println(result);
      }

      @Override
      public void onFailure(Throwable exception) {
        System.out.println("AddProduct failed. Reason: %s".formatted(exception.getMessage()));
      }
    });

    try {
      ProductDatabase.products.add(product);
      asyncResult.success("""
            New Product added:
            - Id: %s
            - Name: %s
            - Price: %f
            - Qty: %d
          """.formatted(product.getId(), product.getName(), product.getPrice(), product.getQty()));
    } catch (Exception e) {
      asyncResult.failure(e);
    }
    return asyncResult;
  }

  @Override
  public void publishProduct() {
    publisherService.publishProduct();
  }

  @Override
  public void publishProductList() {
    publisherService.publishProductList();
  }

}
