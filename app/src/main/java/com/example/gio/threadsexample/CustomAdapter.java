package com.example.gio.threadsexample;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

 class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>{
    private ArrayList<Country> countryArray;

    public CustomAdapter(ArrayList<Country> countryArray) {
        this.countryArray = countryArray;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_country,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(countryArray.get(position).getName());
        holder.alpha2.setText(countryArray.get(position).getAlpha2());
        holder.alpha3.setText(countryArray.get(position).getAlpha3());
    }

    @Override
    public int getItemCount() {
        return countryArray.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView alpha2;
        TextView alpha3;
        public ViewHolder(View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.name);
            alpha2=(TextView)itemView.findViewById(R.id.alpha2);
            alpha3=(TextView)itemView.findViewById(R.id.alpha3);
        }
    }
}
