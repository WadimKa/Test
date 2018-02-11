package com.wadimkazak.test;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Wadim on 10.02.2018.
 */

public class ListHolder extends RecyclerView.ViewHolder {
    TextView number, name, surname, tel, pass;

    public ListHolder(LayoutInflater inflater, ViewGroup parent) {
        super(inflater.inflate(R.layout.item_of_list, parent, false));
        number = itemView.findViewById(R.id.tvNumber);
        name = itemView.findViewById(R.id.tvName);
        surname = itemView.findViewById(R.id.tvSurname);
        tel = itemView.findViewById(R.id.tvTel);
        pass = itemView.findViewById(R.id.tvPass);
    }
}
