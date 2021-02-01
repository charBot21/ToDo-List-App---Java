package com.carlostorres.ibericajava.data.local.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.carlostorres.ibericajava.data.local.dao.NotesDao;
import com.carlostorres.ibericajava.data.local.databas.NotesRoomDatabase;
import com.carlostorres.ibericajava.data.local.entity.Notes;

import java.util.List;

public class NotesRepository {

    private NotesDao notesDao;
    private LiveData<List<Notes>> allNotes;

    public NotesRepository(Application application) {
        NotesRoomDatabase database = NotesRoomDatabase.getInstance(application);

        notesDao = database.notesDao();
        allNotes = notesDao.allNotes();
    }

    public void insert(Notes notes) {
        new InsertNoteAsyncTask(notesDao).execute(notes);
    }


    public void updateDate(Notes notes) {
        new UpdateNoteAsyncTask(notesDao).execute(notes);
    }


    public void delete(Notes notes) {
        new DeleteNoteAsyncTask(notesDao).execute(notes);
    }

    public LiveData<List<Notes>> getAllNotes() {
        return allNotes;
    }

    private static class InsertNoteAsyncTask extends AsyncTask<Notes, Void, Void> {
        private NotesDao notesDao;

        private InsertNoteAsyncTask(NotesDao notesDao) {
            this.notesDao = notesDao;
        }

        @Override
        protected Void doInBackground(Notes... notes) {
            notesDao.insertNote(notes[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<Notes, Void, Void> {
        private NotesDao notesDao;

        private UpdateNoteAsyncTask(NotesDao notesDao) {
            this.notesDao = notesDao;
        }

        @Override
        protected Void doInBackground(Notes... notes) {
            notesDao.updateNote(notes[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<Notes, Void, Void> {
        private NotesDao notesDao;

        private DeleteNoteAsyncTask(NotesDao notesDao) {
            this.notesDao = notesDao;
        }

        @Override
        protected Void doInBackground(Notes... notes) {
            notesDao.deleteNote(notes[0]);
            return null;
        }
    }
}
