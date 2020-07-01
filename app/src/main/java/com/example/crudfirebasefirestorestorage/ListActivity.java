package com.example.crudfirebasefirestorestorage;

import android.os.Bundle;

import com.example.crudfirebasefirestorestorage.adapter.VehicleAdapter;
import com.example.crudfirebasefirestorestorage.model.VehicleModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ListActivity extends BaseActivity {

    private FloatingActionButton newVehicle;
    private ListView listVehicles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        super.init();
        init();

        newVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToCreate();
            }
        });

        listVehicles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                model = list.get(position);
                goToDetail(model);
            }
        });
    }

    protected void init() {
        newVehicle = findViewById(R.id.fabNewVehicle);
        listVehicles = findViewById(R.id.lvListVehicles);
    }

    protected void getVehicles() {
        if (collectionReference != null) {
            collectionReference.get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                if (task.getResult() != null) {
                                    list = new ArrayList<>();
                                    for (QueryDocumentSnapshot snapshot : task.getResult()) {
                                        model = snapshot.toObject(VehicleModel.class);
                                        list.add(model);
                                    }

                                    if (list.size() > 0) {
                                        showListVehicles(list);
                                    } else {
                                        makeSimpleAlertDialog("Message", "Nothing to show");
                                    }
                                } else {
                                    makeSimpleAlertDialog("Error in query", "Vehicles not found");
                                }
                            } else {
                                makeSimpleAlertDialog("Error in query", task.getException().getMessage());
                            }
                        }
                    });
        } else {
            makeSimpleAlertDialog("Error in connection", "Not connection to the database");
        }
    }

    private void showListVehicles(ArrayList<VehicleModel> list) {
        adapter = new VehicleAdapter(this, list);
        listVehicles.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getVehicles();
    }
}