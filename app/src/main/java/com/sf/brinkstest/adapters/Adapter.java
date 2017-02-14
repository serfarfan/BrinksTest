package com.sf.brinkstest.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sf.brinkstest.R;
import com.sf.brinkstest.models.App;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by sf on 2/13/17.
 */

public class Adapter extends BaseAdapter {

    private final Context context;
    private List<App> apps;
    private LayoutInflater layoutInflater;


    public Adapter(Context context, List<App> apps){
        this.apps = apps;
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return apps.size();
    }

    @Override
    public Object getItem(int i) {
        return apps.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        // create a ViewHolder reference
        ViewHolder holder;

        //check to see if the reused view is null or not, if is not null then reuse it
        if (view == null) {
            holder = new ViewHolder();
            view = layoutInflater.inflate(R.layout.list_row, null);
            holder.textName = (TextView) view.findViewById(R.id.name);
            holder.textCategoria = (TextView) view.findViewById(R.id.category);
            holder.textPrecio = (TextView) view.findViewById(R.id.cost);
            holder.img = (ImageView)view.findViewById(R.id.image);
            // the setTag is used to store the data within this view
            view.setTag(holder);
        } else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)view.getTag();
        }

        holder.textName.setText(apps.get(i).getName());
        holder.textCategoria.setText(apps.get(i).getCategory());
        holder.textPrecio.setText(apps.get(i).getCost());
        Picasso.with(this.context).load(apps.get(i).getUrlImage()).into(holder.img);
        return view;

    }


    public class ViewHolder{
        public TextView textName;
        public TextView textCategoria;
        public TextView textPrecio;
        public ImageView img;
    }
}
