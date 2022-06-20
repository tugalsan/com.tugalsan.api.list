package com.tugalsan.api.list.client;

import java.util.concurrent.atomic.AtomicReference;

public class TGS_ListSyncItem<T> {
    
    private final AtomicReference<T> ref = new AtomicReference();
    
    public TGS_ListSyncItem() {
        
    }
    
    public TGS_ListSyncItem(T initValue) {
        ref.set(initValue);
    }
    
    public void set(T item) {
        ref.set(item);
    }
    
    public T get() {
        return ref.get();
    }
}
