package com.example.crudfirebasefirestorestorage;

import android.os.Bundle;

import com.example.crudfirebasefirestorestorage.model.VehicleModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

public class DetailActivity extends BaseActivity {

    private FloatingActionButton fabGoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        super.init();
        init();

        model = (VehicleModel) getIntent().getSerializableExtra("model");

        if (model != null) {
            makeSimpleAlertDialog("Info", "Vehículo de marca " + model.getMarca());
            Bundle bundle = new Bundle();
            bundle.putSerializable("model", model);
            VehicleDataFragment.receptData(bundle);
        } else {
            makeSimpleAlertDialog("Error", "Not data");
        }

        fabGoList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToList();
            }
        });
    }

    protected void init() {
        fabGoList = findViewById(R.id.fabGoList);
    }
}