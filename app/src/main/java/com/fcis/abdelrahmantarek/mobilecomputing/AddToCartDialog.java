package com.fcis.abdelrahmantarek.mobilecomputing;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.fcis.abdelrahmantarek.mobilecomputing.beans.Cart;
import com.fcis.abdelrahmantarek.mobilecomputing.beans.Category;
import com.fcis.abdelrahmantarek.mobilecomputing.beans.Order;
import com.fcis.abdelrahmantarek.mobilecomputing.beans.Product;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by abdelrahmantarek on 12/13/17.
 */

public class AddToCartDialog extends DialogFragment {
    EditText quantity;
    Button confirm;
    String name;
    Product product;
    long id;
    Realm realm;

    View.OnClickListener mOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            final Order category = new Order() ;
//            category.setId(Long.parseLong(id.getText().toString()));
//            category.setName(name.getText().toString());
//            Realm realm = Realm.getDefaultInstance();
//            realm.executeTransactionAsync(new Realm.Transaction() {
//                @Override
//                public void execute(Realm mRealm) {
//                    mRealm.copyToRealmOrUpdate(category);
//                }
//            });
//            Log.d("Cat", "addAction: " + name.getText().toString());
//            realm.close();
            Cart r = realm.where(Cart.class)
                    .equalTo("order",id)
                    .equalTo("prod",product.getId())
                    .findFirst();
            if(r==null) {
                final Cart c = new Cart();
                c.setOrder(id);
                c.setProd(product.getId());
                c.setCount(Integer.parseInt(quantity.getText().toString()));
                realm.executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        realm.copyToRealmOrUpdate(c);
                    }
                });
            }
            else{
                r.setCount(r.getCount()+Integer.parseInt(quantity.getText().toString()));
            }
            dismiss();
        }
    };

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle b = getArguments();
        quantity = view.findViewById(R.id.prod_quantity_et);
        confirm = view.findViewById(R.id.confirm_quantity_btn);
        confirm.setOnClickListener(mOnClick);
        name = b.getString("product");
        id = b.getLong("order");
        product = realm.where(Product.class).equalTo("name", name).findFirst();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_to_cart, container, false);

    }

    public AddToCartDialog() {
        realm = Realm.getDefaultInstance();
    }
}
