package com.carlostorres.ibericajava.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.carlostorres.ibericajava.data.local.entity.Notes;
import com.carlostorres.ibericajava.data.local.repository.NotesRepository;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {
    private NotesRepository repository;
    private LiveData<List<Notes>> allNotes;

    public NotesViewModel(@NonNull Application application) {
        super(application);
        repository = new NotesRepository(application);
        allNotes = repository.getAllNotes();
    }

    public void insert(Notes notes) {
        repository.insert(notes);
    }

    public void update(Notes notes) {
        repository.updateDate(notes);
    }

    public void delete(Notes notes) {
        repository.delete(notes);
    }

    public LiveData<List<Notes>> getAllNotes() {
        return allNotes;
    }

}
