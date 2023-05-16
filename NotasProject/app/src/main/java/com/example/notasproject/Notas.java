package com.example.notasproject;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity( tableName = "notas_table")
public class Notas {


    @PrimaryKey(autoGenerate = true)
    public int id;

    public String titulo;

    public String descripcion;


    public Notas(String titulo, String descripcion) {
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setId(int id) {
        this.id = id;
    }


}
