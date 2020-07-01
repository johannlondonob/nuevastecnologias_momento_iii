package com.example.crudfirebasefirestorestorage.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.crudfirebasefirestorestorage.R;
import com.example.crudfirebasefirestorestorage.model.VehicleModel;

import java.util.ArrayList;

public class VehicleAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<VehicleModel> listModel;

    public VehicleAdapter(Context context, ArrayList<VehicleModel> listModel) {
        this.context = context;
        this.listModel = listModel;
    }

    @Override
    public int getCount() {
        return listModel.size();
    }

    @Override
    public VehicleModel getItem(int position) {
        return listModel.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(this.context);
            convertView = inflater.inflate(R.layout.vehicle_list_item, parent, false);
        }

        TextView modelo = convertView.findViewById(R.id.textViewModelo);
        TextView marca = convertView.findViewById(R.id.textViewMarca);
        TextView categoria = convertView.findViewById(R.id.textViewCategoria);

        modelo.setText(getItem(position).getModelo());
        marca.setText(getItem(position).getMarca());
        categoria.setText(getItem(position).getCategoria());
        return convertView;
    }
}
