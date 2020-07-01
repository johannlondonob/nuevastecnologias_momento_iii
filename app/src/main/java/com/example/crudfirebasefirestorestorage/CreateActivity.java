package com.example.crudfirebasefirestorestorage;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;

import com.example.crudfirebasefirestorestorage.model.VehicleModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;

public class CreateActivity extends BaseActivity {

    Button buttonGoBack, buttonClear, buttonSave;
    ImageView ivFoto;
    EditText etModelo, etMarca, etNumeroRuedas, etCategoria;
    Switch swEstado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        super.init();
        init();

        buttonGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToList();
            }
        });

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clear();
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String modelo, marca, numeroRuedas, categoria;
                boolean estado;

                modelo = etModelo.getText().toString();
                marca = etMarca.getText().toString();
                numeroRuedas = etNumeroRuedas.getText().toString();
                categoria = etCategoria.getText().toString();
                estado = swEstado.isChecked();

                if (modelo.isEmpty() || marca.isEmpty() || numeroRuedas.isEmpty() || categoria.isEmpty()) {
                    makeSimpleAlertDialog("Campos vacíos encontrados", "Diligencia todos los campos.");
                } else {
                    model = new VehicleModel();
                    model.setModelo(modelo);
                    model.setMarca(marca);
                    model.setNumeroRuedas(numeroRuedas);
                    model.setCategoria(categoria);
                    model.setActivo(estado);

                    save(model);
                }

            }
        });
    }

    protected void save(VehicleModel model) {
        if (collectionReference != null){
            collectionReference.add(model)
                    .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            if (task.isSuccessful()) {
                                if (task.getResult() != null) {
                                    makeSimpleToast("Saved data", 2);
                                } else {
                                    makeSimpleAlertDialog("Error to save", "Data not saved");
                                }
                            } else {
                                makeSimpleAlertDialog("Error to save", task.getException().getMessage());
                            }
                        }
                    });
        } else {
            makeSimpleAlertDialog("Error to connect", "Not database connection");
        }
    }

    protected void init() {
        buttonGoBack = findViewById(R.id.buttonGoBack);
        buttonClear = findViewById(R.id.buttonClear);
        buttonSave = findViewById(R.id.buttonSave);
        etModelo = findViewById(R.id.editTextModelo);
        etMarca = findViewById(R.id.editTextMarca);
        etNumeroRuedas = findViewById(R.id.editTextNumeroRuedas);
        etCategoria = findViewById(R.id.editTextCategoria);
        ivFoto = findViewById(R.id.imageViewFoto);
        swEstado = findViewById(R.id.switchEstado);
    }

    protected void clear() {
        etModelo.setText("");
        etMarca.setText("");
        etNumeroRuedas.setText("");
        etCategoria.setText("");
        ivFoto.setImageResource(R.drawable.ic_launcher_foreground);

        etModelo.requestFocus();
    }
}