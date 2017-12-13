package com.fcis.abdelrahmantarek.mobilecomputing.beans;

import android.location.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by abdelrahmantarek on 12/10/17.
 */

public class Order extends RealmObject {

    @PrimaryKey
    private long id;
    private double subTotal;
    private int lat;
    private int lng;
    private boolean done;

    public Order() {
        id = System.currentTimeMillis();
        subTotal = 0;
        lat = lng = 0;
        done = false;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public int getLat() {
        return lat;
    }

    public void setLat(int lat) {
        this.lat = lat;
    }

    public int getLng() {
        return lng;
    }

    public void setLng(int lng) {
        this.lng = lng;
    }
}
