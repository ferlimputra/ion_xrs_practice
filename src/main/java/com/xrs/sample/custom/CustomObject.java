package com.xrs.sample.custom;

import java.util.concurrent.ConcurrentHashMap;
import com.iontrading.xrs.api.IContext;
import com.iontrading.xrs.api.IXRSObject;

public class CustomObject implements IXRSObject {

  private String id = "";
  private final ConcurrentHashMap<String, Comparable<?>> fields = new ConcurrentHashMap<>();

  public CustomObject(final String id, final IContext context) throws NullPointerException {
    if (id == null || id.isEmpty()) {
      throw new NullPointerException("CustomObject Id is empty");
    }
    this.id = id;
  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public Object getFieldValue(final String fieldName) {
    return fields.get(fieldName);
  }

  @Override
  public boolean isFieldSet(final String fieldName) {
    return fields.containsKey(fieldName);
  }

  private void set(final String fieldName, final Comparable<?> value) {
    fields.put(fieldName, value);
  }

  public void setFieldValue(final String fieldName, final Integer value) {
    set(fieldName, value);
  }

  public void setFieldValue(final String fieldName, final Double value) {
    set(fieldName, value);
  }

  public void setFieldValue(final String fieldName, final String value) {
    set(fieldName, value);
  }

}
