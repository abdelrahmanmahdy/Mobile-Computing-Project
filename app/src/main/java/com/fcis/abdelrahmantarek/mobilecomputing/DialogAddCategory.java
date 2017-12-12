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

import com.fcis.abdelrahmantarek.mobilecomputing.beans.Category;

import io.realm.Realm;

/**
 * Created by abdelrahmantarek on 12/11/17.
 */

public class DialogAddCategory extends DialogFragment {

    private EditText id, name;
    private Button add;

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.cat_add:
                    Log.d("Cat", "onClick: ");
                    addAction();
                    break;
            }
            dismiss();
        }
    };

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        id = view.findViewById(R.id.cat_id);
        name = view.findViewById(R.id.cat_name);
        add = view.findViewById(R.id.cat_add);
        add.setOnClickListener(mOnClickListener);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_add_category, container, false);
    }

    private void addAction() {
        final Category category = new Category() ;
        category.setId(Long.parseLong(id.getText().toString()));
        category.setName(name.getText().toString());
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

    public DialogAddCategory() {
        Log.d("Cat", "DialogAddCategory: ");
    }
}
