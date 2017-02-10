package com.example.hppavilion.dengue.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.hppavilion.dengue.NavigationActivity;
import com.example.hppavilion.dengue.R;

import java.util.ArrayList;

public class TodoCursorAdapter extends ArrayAdapter<User> {
    public TodoCursorAdapter(Context context, ArrayList<User> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        // Get the data item for this position
        User user = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.display_product_row, parent, false);
        }
        TextView nome = (TextView) view.findViewById(R.id.NomeList);
        TextView doenca = (TextView) view.findViewById(R.id.DoencaList);
        TextView endereco = (TextView) view.findViewById(R.id.EnderecoList);

        // Extraccion de las propiedades del cursor


        nome.setText(user.name);
        doenca.setText(user.doencame);
        endereco.setText(user.addname);
        return view;
    }




}
