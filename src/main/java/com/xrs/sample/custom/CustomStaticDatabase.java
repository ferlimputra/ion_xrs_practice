package com.xrs.sample.custom;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import com.iontrading.xrs.api.IContext;

public class CustomStaticDatabase {

  private static CustomStaticDatabase instance;

  private Map<String, Item> database = new HashMap<>();
  private boolean initialized = false;
  private IContext context;
  private ICustomRealTimeListener rtListener;

  private Item[] items = new Item[] {new Item("ID_1", "Book", "Book Desc", 100, 10),
      new Item("ID_2", "Pen", "Pen Desc", 10, 200), new Item("ID_3", "Paper", "Paper Desc", 1, 500),
      new Item("ID_4", "Pencil", "Pencil Desc", 5, 100)};

  private CustomStaticDatabase() {
  }

  public synchronized static CustomStaticDatabase getInstance() {
    if (instance == null) {
      instance = new CustomStaticDatabase();
    }
    return instance;
  }

  public synchronized void init(IContext context, String src) {
    if (initialized) {
      return;
    }

    this.context = context;
    for (Item item : items) {
      database.put(item.getId(), item);
    }
    initialized = true;
  }

  public void scan(ICustomSnapshotListener listener) {
    if (listener != null) {
      synchronized (database) {
        for (Iterator<Item> iter = database.values().iterator(); iter.hasNext();) {
          listener.onSnapshot(iter.next());
        }
      }
    }
  }

  public void add(Item o) {
    synchronized (database) {
      database.put(o.getId(), o);
    }
  }

  public void upd(Item o) {
    synchronized (database) {
      database.put(o.getId(), o);
    }
  }

  public void setRTListener(ICustomRealTimeListener l) {
    rtListener = l;
  }
}
