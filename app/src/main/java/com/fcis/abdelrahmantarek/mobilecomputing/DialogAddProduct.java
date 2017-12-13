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
import com.fcis.abdelrahmantarek.mobilecomputing.beans.Product;
import io.realm.Realm;

/**
 * Created by abdelrahmantarek on 12/12/17.
 */

public class DialogAddProduct extends DialogFragment {

    private EditText id,name,price,catId;
    private Button add;

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.add_item){
                addAction();
            }
            dismiss();
        }
    };

    private void addAction() {
        final Product category = new Product() ;
        category.setName(name.getText().toString());
        category.setPrice(Double.parseDouble(price.getText().toString()));
        category.setCatId(Long.parseLong(catId.getText().toString()));
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm mRealm) {
                mRealm.copyToRealmOrUpdate(category);
            }
        });
        Log.d("Cat", "addAction: " + name.getText().toString());
        realm.close();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        id = view.findViewById(R.id.prod_id);
        name = view.findViewById(R.id.prod_name);
        price = view.findViewById(R.id.prod_price);
        catId = view.findViewById(R.id.cat_id);
        add = view.findViewById(R.id.add_item);
        add.setOnClickListener(mOnClickListener);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_add_product, container, false);
    }

    public DialogAddProduct() {
    }
}
