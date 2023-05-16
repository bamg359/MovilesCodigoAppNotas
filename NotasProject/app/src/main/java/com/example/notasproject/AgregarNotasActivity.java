package com.example.notasproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AgregarNotasActivity extends AppCompatActivity {

    EditText title;
    EditText descripcion;
    Button cancelar;
    Button  guardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Agregar Nota");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_agregar_notas);

        title = findViewById(R.id.editTextTitulo);
        descripcion =  findViewById(R.id.editTextDescripcion);
        cancelar =  findViewById(R.id.buttonCancelar);
        guardar = findViewById(R.id.buttonGuardar);

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(), "No se ha guardado la nota", Toast.LENGTH_SHORT).show();
                finish();

            }
        });


        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                guardarNota();

            }
        });

    }

    public void guardarNota(){


        String tituloNota = title.getText().toString();
        String descripcionNota= descripcion.getText().toString();


        Intent i = new Intent();
        i.putExtra("tituloNota", tituloNota);
        i.putExtra("descripcionNota", descripcionNota);
        setResult(RESULT_OK,i);
        finish();


    }

}