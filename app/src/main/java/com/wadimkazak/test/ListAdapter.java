package com.wadimkazak.test;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Wadim on 10.02.2018.
 */

public class ListAdapter extends RecyclerView.Adapter<ListHolder> {
    List<Man> listOfMans;
    Context context;

    public ListAdapter(List<Man> listOfMans, Context context) {
        this.listOfMans = listOfMans;
        this.context = context;
    }

    @Override
    public ListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        return new ListHolder(layoutInflater, parent);
    }

    @Override
    public void onBindViewHolder(ListHolder holder, int position) {
        holder.number.setText("Count of limbs "+listOfMans.get(position).getCountOfLimbs());
        holder.name.setText(listOfMans.get(position).getName());
        holder.surname.setText(listOfMans.get(position).getSurname());
        holder.tel.setText(listOfMans.get(position).getTel());
        holder.pass.setText(listOfMans.get(position).getPass());

    }

    @Override
    public int getItemCount() {
        return listOfMans.size();
    }
}

