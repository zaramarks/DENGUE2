package com.example.hppavilion.dengue.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hppavilion.dengue.R;

import java.util.List;

/**
 * Created by HP PAVILION on 04/04/2016.
 */
public class CasoAdapter extends BaseAdapter {
    private Context context;
    private List<ItemSlideMenu> lstItem;



    public  CasoAdapter(Context context,List<ItemSlideMenu> lstItem ) {
        this.context = context;
        this.lstItem = lstItem;
    }



    @Override
    public int getCount() {
        return lstItem.size();
    }

    @Override
    public Object getItem(int position) {
        return lstItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v= View.inflate(context, R.layout.item, null);
        ImageView img = (ImageView)v.findViewById(R.id.item_img);
        TextView tv= (TextView)v.findViewById(R.id.item_tv);

        ItemSlideMenu item = lstItem.get(position);
        img.setImageResource(item.getImgId());
        tv.setText(item.getTitle());


        return v;
    }
}