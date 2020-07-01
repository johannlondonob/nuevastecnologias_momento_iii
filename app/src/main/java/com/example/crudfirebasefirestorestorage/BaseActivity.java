package com.example.crudfirebasefirestorestorage;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.crudfirebasefirestorestorage.adapter.VehicleAdapter;
import com.example.crudfirebasefirestorestorage.connection.FirebaseConnection;
import com.example.crudfirebasefirestorestorage.model.VehicleModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class BaseActivity extends AppCompatActivity {

    protected VehicleModel model;
    protected ArrayList<VehicleModel> list;
    protected VehicleAdapter adapter;
    protected FirebaseFirestore db;
    protected FirebaseAuth auth;
    protected FirebaseStorage firebaseStorage;
    protected Query query;
    protected CollectionReference collectionReference;
    protected StorageReference storageReference;

    protected final String COLLECTION_NAME = "vehicles";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
    }

    protected void init() {
        model = new VehicleModel();
        db = FirebaseConnection.ConnectionFirestore();
        auth = FirebaseConnection.ConnectionAuth();
        firebaseStorage = FirebaseConnection.ConnectionStorage();
        collectionReference = db.collection(COLLECTION_NAME);
    }

    protected void makeSimpleToast(String msg, int duration) {
        Toast.makeText(this, msg, duration).show();
    }

    protected void makeSimpleAlertDialog(String title, String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(msg);
        builder.setTitle(title);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    protected void goToList() {
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }

    protected void goToCreate() {
        Intent intent = new Intent(this, CreateActivity.class);
        startActivity(intent);
    }

    protected void goToEdit() {
        Intent intent = new Intent(this, BaseActivity.class);
        startActivity(intent);
    }

    protected void goToSearch() {
        Intent intent = new Intent(this, BaseActivity.class);
        startActivity(intent);
    }

    protected void goToDetail(VehicleModel model) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("model", model);
        startActivity(intent);
    }
}