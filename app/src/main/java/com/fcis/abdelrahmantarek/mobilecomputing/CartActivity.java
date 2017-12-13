package com.fcis.abdelrahmantarek.mobilecomputing;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.fcis.abdelrahmantarek.mobilecomputing.beans.Cart;
import com.fcis.abdelrahmantarek.mobilecomputing.beans.Order;
import com.fcis.abdelrahmantarek.mobilecomputing.beans.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

import static java.lang.System.in;

public class CartActivity extends AppCompatActivity {

    Order order;
    Realm realm;
    ListView cartList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Cart");
        setSupportActionBar(toolbar);
        cartList = findViewById(R.id.cart_list);

        realm = Realm.getDefaultInstance();
        RealmResults<Cart> res = realm.where(Cart.class).findAll();
        List<String> list = new ArrayList<>();
        for(int i =0;i<res.size();i++){
            Cart c = res.get(i);
            Product p = realm.where(Product.class).equalTo("id",c.getProd()).findFirst();
            list.add(c.getCount()+" * "+p.getName());
        }
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        cartList.setAdapter(adapter);
        cartList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });


//        int id = getIntent().getExtras().getInt("order");
//        order = realm.where(Order.class).equalTo("id", id).findFirst();
//        cartList = findViewById(R.id.cart_list);
//        List<String> items = new ArrayList<>();
//        for (int i = 0; i < products.size(); i++) {
//            items.add(products.get(i).getName());
//        }
//        HashMap<String, Integer> frequencyMap = new HashMap<String, Integer>();
//        for (String a : items) {
//            if (frequencyMap.containsKey(a)) {
//                frequencyMap.put(a, frequencyMap.get(a) + 1);
//            } else {
//                frequencyMap.put(a, 1);
//            }
//        }
//        items = new ArrayList<>();
//        for (String a : frequencyMap.keySet()) {
//            items.add(a + frequencyMap.get(a));
//        }
//        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
//        cartList.setAdapter(adapter);
//        Set<String> distinct = new HashSet<>(items);
//        for (int i = 0; i < distinct.size(); i++) {
//            Collections.frequency(distinct, distinct[i]);
//
//        }
    }

}
