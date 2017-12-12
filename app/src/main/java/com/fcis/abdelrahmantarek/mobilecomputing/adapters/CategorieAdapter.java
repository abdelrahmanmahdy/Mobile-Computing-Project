package com.fcis.abdelrahmantarek.mobilecomputing.adapters;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fcis.abdelrahmantarek.mobilecomputing.HomeActivity;
import com.fcis.abdelrahmantarek.mobilecomputing.R;
import com.fcis.abdelrahmantarek.mobilecomputing.beans.Category;

import io.realm.OrderedRealmCollection;
import io.realm.RealmBaseAdapter;

/**
 * Created by abdelrahmantarek on 12/10/17.
 */

public class CategorieAdapter extends RealmBaseAdapter<Category> {

    private HomeActivity activity;

    private static class ViewHolder{
        TextView catName;
    }

    public CategorieAdapter(HomeActivity activity,@Nullable OrderedRealmCollection<Category> data) {
        super(data);
        this.activity = activity;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.category_row, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.catName = convertView.findViewById(R.id.categorie_name);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (adapterData != null) {
            Category task = adapterData.get(position);
            viewHolder.catName.setText(task.getName());
            viewHolder.catName.setTag(position);
        }

        return convertView;
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int pos = (int) v.getTag();
            if(adapterData!=null){
                Category category = adapterData.get(pos);
                //TODO: set content to this category
            }

        }
    };
}
