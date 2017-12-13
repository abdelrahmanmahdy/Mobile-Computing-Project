package com.fcis.abdelrahmantarek.mobilecomputing.beans;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by abdelrahmantarek on 12/10/17.
 */

public class Product extends RealmObject {

    @PrimaryKey
    private long id;
    private String name;
    private double price;
    private long catId;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getCatId() {
        return catId;
    }

    public void setCatId(long catId) {
        this.catId = catId;
    }

    public Product() {
        id = System.currentTimeMillis();
    }
}
