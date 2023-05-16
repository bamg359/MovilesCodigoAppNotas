package com.example.notasproject;


import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NotasRepositorio {

    private NotasDao notasDao;
    private LiveData<List<Notas>> notas;

    ExecutorService executors = Executors.newSingleThreadExecutor();

    public NotasRepositorio (Application application) {

        NotasDB database  = NotasDB.getInstance(application);
        notasDao = database.notasDao();
        notas = notasDao.getAllNotas();


    }



    public void insert(Notas notas){

        //new InsertNotasAsyncTask(notasDao).execute(notas);

        executors.execute(new Runnable() {
            @Override
            public void run() {
                notasDao.insert(notas);
            }
        });

    }

    public void update(Notas notas){

        //new UpdateNotasAsyncTask(notasDao).execute(notas);

        executors.execute(new Runnable() {
            @Override
            public void run() {
                notasDao.update(notas);
            }
        });







    }

    public void delete(Notas notas){

        //new DeleteNotasAsyncTask(notasDao).execute(notas);


        executors.execute(new Runnable() {
            @Override
            public void run() {
                notasDao.delete(notas);
            }
        });

    }

    public LiveData<List<Notas>> getAllNotas() {
        return notas;
    }


    /*private static class InsertNotasAsyncTask extends AsyncTask<Notas, Void, Void>{

        private NotasDao notasDao;


        private InsertNotasAsyncTask(NotasDao notasDao){

            this.notasDao = notasDao;



        }



        @Override
        protected Void doInBackground(Notas... notas) {

            notasDao.insert(notas[0]);

            return null;
        }
    }

    private static class UpdateNotasAsyncTask extends AsyncTask<Notas, Void, Void>{

        private NotasDao notasDao;


        private UpdateNotasAsyncTask(NotasDao notasDao){

            this.notasDao = notasDao;



        }



        @Override
        protected Void doInBackground(Notas... notas) {

            notasDao.update(notas[0]);

            return null;
        }
    }

    private static class DeleteNotasAsyncTask extends AsyncTask<Notas, Void, Void>{

        private NotasDao notasDao;


        private DeleteNotasAsyncTask(NotasDao notasDao){

            this.notasDao = notasDao;

        }



        @Override
        protected Void doInBackground(Notas... notas) {

            notasDao.delete(notas[0]);

            return null;
        }
    }*/


}
