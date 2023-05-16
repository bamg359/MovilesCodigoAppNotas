package com.example.notasproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText title;
    EditText descripcion;
    Button cancelar;
    Button  guardar;
    int notaId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Editar Nota");
        setContentView(R.layout.activity_update);

        title = findViewById(R.id.editTextTituloUpdate);
        descripcion =  findViewById(R.id.editTextDescripcionUpdate);
        cancelar =  findViewById(R.id.buttonCancelarUpdate);
        guardar = findViewById(R.id.buttonGuardarUpdate);

        getData();

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(), "Se ha editado la nota", Toast.LENGTH_SHORT).show();
                finish();

            }
        });


        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editarNota();

            }
        });


    }

    private void editarNota() {


        String titleNew = title.getText().toString();
        String descripcionNew= descripcion.getText().toString();
        Intent intent = new Intent();
        intent.putExtra("titleNew", titleNew);
        intent.putExtra("descripcionNew",descripcionNew);
        if( notaId != -1){

            intent.putExtra("notaId", notaId);
            setResult(RESULT_OK, intent);
            finish();

        }



    }

    public void getData(){

        Intent i= getIntent();
        notaId = i.getIntExtra("id", -1);
        String notastitle = i.getStringExtra("title");
        String notasdescripcion = i.getStringExtra("descripcion");

        title.setText(notastitle);
        descripcion.setText(notasdescripcion);



    }

}