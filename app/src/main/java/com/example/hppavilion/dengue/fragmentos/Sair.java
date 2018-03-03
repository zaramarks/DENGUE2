package com.example.hppavilion.dengue.fragmentos;


import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hppavilion.dengue.BancoDeDados;
import com.example.hppavilion.dengue.MenuDeEntrada;
import com.example.hppavilion.dengue.NavigationActivity;
import com.example.hppavilion.dengue.R;

/**
 * Created by zazah on 24/06/2016.
 */
public class Sair extends Fragment  {
    public static Fragment fa;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sair, container, false);
        NavigationActivity.sv.setVisibility(View.INVISIBLE);
            fa = this;
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setTitle("Logout");
        alertDialog.setMessage("If you get out, you will be logged out of your account");
        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                BancoDeDados bd = new BancoDeDados(getActivity());
                SQLiteDatabase banco = bd.getReadableDatabase();

                banco.execSQL ("UPDATE lingua SET emaill = null");
                banco.execSQL ("UPDATE lingua SET senhaa = null");
                Intent myIntent = new Intent(((Dialog) dialog).getContext(), MenuDeEntrada.class);
                myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(myIntent);

                return;
            }
        });
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent myIntent = new Intent(((Dialog) dialog).getContext(), NavigationActivity.class);
                myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(myIntent);

            } });

        alertDialog.show();

        return v;
    }

    public interface OnFragmentInteractionListener {
    }

    }





