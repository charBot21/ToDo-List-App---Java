package com.carlostorres.ibericajava.data.local.databas;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.carlostorres.ibericajava.data.local.dao.NotesDao;
import com.carlostorres.ibericajava.data.local.entity.Notes;

@Database(entities = {Notes.class}, version = 1)
public abstract class NotesRoomDatabase extends RoomDatabase {

    private static NotesRoomDatabase instance;

    public abstract NotesDao notesDao();

    public static synchronized NotesRoomDatabase getInstance(Context context) {
        if ( instance == null ) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NotesRoomDatabase.class, "notes_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }


    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };


    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private NotesDao notesDao;

        private PopulateDbAsyncTask(NotesRoomDatabase db) {
            notesDao = db.notesDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            notesDao.insertNote(new Notes("Note prueba", "Prueba de app", 30012021L));
            return null;
        }
    }
}
