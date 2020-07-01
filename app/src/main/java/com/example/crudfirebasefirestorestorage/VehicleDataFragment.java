package com.example.crudfirebasefirestorestorage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.crudfirebasefirestorestorage.model.VehicleModel;

public class VehicleDataFragment extends Fragment {

    private static String modelo, marca, numeroRuedas, categoria;
    private static boolean estado;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vehicle_data, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView tvModelo, tvMarca, tvNumeroRuedas, tvCategoria;
        Switch swEstado;

        tvMarca = view.findViewById(R.id.textViewMarca);
        tvModelo = view.findViewById(R.id.textViewModelo);
        tvNumeroRuedas = view.findViewById(R.id.textViewNumeroRuedas);
        tvCategoria = view.findViewById(R.id.textViewCategoria);
        swEstado = view.findViewById(R.id.switchEstadoDetail);

        tvModelo.setText(modelo);
        tvMarca.setText(marca);
        tvNumeroRuedas.setText(numeroRuedas);
        tvCategoria.setText(categoria);
        swEstado.setChecked(estado);

        view.findViewById(R.id.buttonVerImagen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(VehicleDataFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }

    static void receptData(Bundle bundle) {
        VehicleModel model = (VehicleModel) bundle.getSerializable("model");
        if (model != null) {
            modelo = model.getModelo();
            marca = model.getMarca();
            numeroRuedas = model.getNumeroRuedas();
            categoria = model.getCategoria();
            estado = model.isActivo();
        }

    }
}