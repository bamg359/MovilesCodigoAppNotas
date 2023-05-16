package com.example.notasproject;


import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database( entities = {Notas.class}, version = 1)
public abstract class NotasDB extends RoomDatabase {


    private static NotasDB instance;

    public abstract NotasDao notasDao();

    public static synchronized NotasDB getInstance(Context context) {

        if (instance == null){

            instance = Room.databaseBuilder(context.getApplicationContext()
                    ,NotasDB.class, "notas_db")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }

        return instance;
    }


    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db){

            super.onCreate(db);

            //new PopulateBbAsynTask(instance).execute();

            NotasDao notasDao = instance.notasDao();

            ExecutorService executorService = Executors.newSingleThreadExecutor();

            executorService.execute(new Runnable() {
                @Override
                public void run() {


                    notasDao.insert(new Notas("Titulo 1 ", "Descripcion 1"));
                    notasDao.insert(new Notas("Titulo 2 ", "Descripcion 2"));
                    notasDao.insert(new Notas("Titulo 3 ", "Descripcion 3"));
                    notasDao.insert(new Notas("Titulo 4 ", "Descripcion 4"));
                    notasDao.insert(new Notas("Titulo 5 ", "Descripcion 5"));


                }
            });


        }


    };


    /*private static class PopulateBbAsynTask extends AsyncTask<Void, Void,Void>{

        private NotasDao notasDao;

        private PopulateBbAsynTask( NotasDB database){

            notasDao = database.notasDao();
        }



        @Override
        protected Void doInBackground(Void... voids) {


            notasDao.insert(new Notas("Titulo 1 ", "Descripcion 1"));
            notasDao.insert(new Notas("Titulo 2 ", "Descripcion 2"));
            notasDao.insert(new Notas("Titulo 3 ", "Descripcion 3"));
            notasDao.insert(new Notas("Titulo 4 ", "Descripcion 4"));
            notasDao.insert(new Notas("Titulo 5 ", "Descripcion 5"));


            return null;
        }
    }*/



}
