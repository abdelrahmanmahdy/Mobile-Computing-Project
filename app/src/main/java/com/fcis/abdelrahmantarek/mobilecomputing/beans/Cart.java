package com.fcis.abdelrahmantarek.mobilecomputing.beans;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by abdelrahmantarek on 12/13/17.
 */

public class Cart extends RealmObject {
    @PrimaryKey
    private long id;
    private long prod;
    private long order;
    private int count;

    public Cart() {
        id = System.currentTimeMillis();
    }

    public long getId() {
        return id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public long getProd() {
        return prod;
    }

    public void setProd(long prod) {
        this.prod = prod;
    }

    public long getOrder() {
        return order;
    }

    public void setOrder(long order) {
        this.order = order;
    }
}
