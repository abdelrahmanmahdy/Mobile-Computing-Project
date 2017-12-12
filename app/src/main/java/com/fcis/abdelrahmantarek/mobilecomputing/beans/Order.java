package com.fcis.abdelrahmantarek.mobilecomputing.beans;

import android.location.Location;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by abdelrahmantarek on 12/10/17.
 */

public class Order extends RealmObject {

    @PrimaryKey
    private int id;
    private RealmList<Product> products;
    private double subTotal;
    private int lat;
    private int lng;
}
