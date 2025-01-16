package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

public class DogsAdapter extends BaseAdapter {

    private List<Dog> dogs=null;
    private Context ctx;
    private int resLayout;

    public DogsAdapter(List<Dog> dogs, Context ctx, int resLayout) {
        this.dogs = dogs;
        this.ctx = ctx;
        this.resLayout = resLayout;
    }

    @Override
    public int getCount() {
        return dogs.size();
    }

    @Override
    public Object getItem(int position) {
        return dogs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View v = inflater.inflate(resLayout,parent,false);


        TextView numeTV = v.findViewById(R.id.numeData);
        TextView rasaTV = v.findViewById(R.id.rasaData);
        TextView varstaTV = v.findViewById(R.id.varstaData);
        TextView kgTV = v.findViewById(R.id.kgData);
        TextView adTV = v.findViewById(R.id.adoptabilData);



        Dog dog = (Dog)getItem(position);

        numeTV.setText(dog.getNume());
        rasaTV.setText(dog.getRasa());
        varstaTV.setText(dog.getVarsta());
        kgTV.setText(String.valueOf(dog.getGreutate()));
        adTV.setText(dog.isAdoptabil() ? "Yes" : "No");

        return v;

    }
}
