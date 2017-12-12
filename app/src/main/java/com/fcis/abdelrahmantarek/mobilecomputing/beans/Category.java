package com.fcis.abdelrahmantarek.mobilecomputing.beans;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by abdelrahmantarek on 12/10/17.
 */

public class Category extends RealmObject {

    @PrimaryKey
    private long id;
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
