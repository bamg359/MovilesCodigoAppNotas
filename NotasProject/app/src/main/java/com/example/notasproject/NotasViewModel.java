package com.example.notasproject;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NotasViewModel  extends AndroidViewModel {

    private NotasRepositorio repositorio;
    private LiveData<List<Notas>> notas;


    public NotasViewModel(@NonNull Application application) {
        super(application);

        repositorio = new NotasRepositorio(application);
        notas = repositorio.getAllNotas();

    }

    public void insert(Notas notas){

        repositorio.insert(notas);
    }

    public void update(Notas notas){

        repositorio.update(notas);
    }

    public void delete(Notas notas){

        repositorio.delete(notas);
    }


    public LiveData<List<Notas>> getAllNotas() {
        return notas;
    }
}
