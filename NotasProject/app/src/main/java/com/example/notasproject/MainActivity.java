package com.example.notasproject;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private NotasViewModel notasViewModel;

    ActivityResultLauncher<Intent> activityResultLauncherAgregarNota;
    ActivityResultLauncher<Intent> activityResultLauncherEditarNota;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        registerActivityAgregarNota();
        registerActivityEditarNota();

        RecyclerView recyclerView = findViewById(R.id.RecyclerView);

        recyclerView.setLayoutManager( new LinearLayoutManager(this));

        NotasAdapter adapter = new NotasAdapter();
        recyclerView.setAdapter(adapter);

        notasViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication())
                .create(NotasViewModel.class);

        notasViewModel.getAllNotas().observe(this, new Observer<List<Notas>>() {
            @Override
            public void onChanged(List<Notas> notas) {

                // Aquí se implementan las actualizaciones del Recycler View


                adapter.setNotas(notas);



            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT){
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                notasViewModel.delete(adapter.getNotas(viewHolder.getAdapterPosition()));
                Toast.makeText(getApplicationContext(), "Nota Eliminada", Toast.LENGTH_SHORT).show();

            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new NotasAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Notas notas) {

                Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
                intent.putExtra("id",notas.getId());
                intent.putExtra("title",notas.getTitulo());
                intent.putExtra("descripcion",notas.getDescripcion());

                activityResultLauncherEditarNota.launch(intent);



            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_nuevo,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.top_menu:

                Intent intent = new Intent(MainActivity.this, AgregarNotasActivity.class);
                //startActivityForResult(intent,1);

                activityResultLauncherAgregarNota.launch(intent);

                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }


    public void registerActivityEditarNota(){

        activityResultLauncherEditarNota = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {

                        int resultCode = result.getResultCode();
                        Intent data= result.getData();



                        if (resultCode == RESULT_OK && data != null){

                            String title = data.getStringExtra("titleNew");
                            String descripcion = data.getStringExtra("descripcionNew");
                            int id = data.getIntExtra("notaId", -1);

                            Notas notas = new Notas(title, descripcion);
                            notas.setId(id);
                            notasViewModel.update(notas);


                        }



                    }
                });


    }

    public void registerActivityAgregarNota(){

        activityResultLauncherAgregarNota = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {

                        int codigoResultado = result.getResultCode();
                        Intent data = result.getData();

                        if (codigoResultado == RESULT_OK && data != null){

                            String title = data.getStringExtra("tituloNota");
                            String descripcion = data.getStringExtra("descripcionNota");

                            Notas notas = new Notas(title,descripcion);
                            notasViewModel.insert(notas);



                        }

                    }
                });
    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }*/
}