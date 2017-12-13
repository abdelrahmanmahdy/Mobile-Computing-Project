package com.fcis.abdelrahmantarek.mobilecomputing;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.fcis.abdelrahmantarek.mobilecomputing.beans.Category;
import com.fcis.abdelrahmantarek.mobilecomputing.beans.Order;
import com.fcis.abdelrahmantarek.mobilecomputing.beans.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Realm realm;
    ListView catList, prodList;
    List<String> prods;
    ListAdapter prodsAdapter;
    DrawerLayout drawer;
    Order order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Items");
        setSupportActionBar(toolbar);
        realm = Realm.getDefaultInstance();


        order = new Order();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm mRealm) {
                mRealm.copyToRealmOrUpdate(order);
            }
        });
        realm.close();


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        realm = Realm.getDefaultInstance();
        catList = findViewById(R.id.categories_list);

        prodList = findViewById(R.id.products_list);

        RealmResults<Product> products = realm.where(Product.class).findAll();
        prods = new ArrayList<>();
        for (
                int i = 0; i < products.size(); i++)

        {
            prods.add(products.get(i).getName());
        }

        prodsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, prods);
        prodList.setAdapter(prodsAdapter);

        final RealmResults<Category> results = realm.where(Category.class).findAll();
        Log.d("Cat", "onCreate: " + results.size());
        List<String> res = new ArrayList<>();
        res.add("ALL");
        for (
                int i = 0; i < results.size(); i++)

        {
            Log.d("Cat", "onCreate: " + results.get(i).getName());
            res.add(results.get(i).getName());
        }

        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, res);
        catList.setAdapter(adapter);

        catList.setOnItemClickListener(new AdapterView.OnItemClickListener()

        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                prods = new ArrayList<>();
                RealmResults<Product> byCat;
                if (position == 0) {
                    byCat = realm.where(Product.class).findAll();
                } else {
                    byCat = realm.where(Product.class).equalTo("catId", results.get(position - 1).getId()).findAll();
                }
                for (int i = 0; i < byCat.size(); i++) {
                    prods.add(byCat.get(i).getName());
                }
                prodsAdapter = new ArrayAdapter<>(HomeActivity.this, android.R.layout.simple_list_item_1, prods);
                prodList.setAdapter(prodsAdapter);
                drawer.closeDrawer(GravityCompat.START);
            }
        });

        prodList.setOnItemClickListener(new AdapterView.OnItemClickListener()

        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle b = new Bundle();
                b.putString("product", prods.get(position));
                b.putLong("order", order.getId());
                AddToCartDialog dialog = new AddToCartDialog();
                dialog.setArguments(b);
                dialog.show(getFragmentManager(), "AddToCart");
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.cart) {
            Intent intent = new Intent(this, CartActivity.class);
            intent.putExtra("order", order.getId());
            startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
