package com.carlostorres.ibericajava.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.carlostorres.ibericajava.data.local.entity.Notes;

import java.util.List;

@Dao
public interface NotesDao {
    @Query( "SELECT * from notes_table ORDER BY timeStamp DESC")
    LiveData<List<Notes>> allNotes();

    @Insert
    void insertNote(Notes notes);

    @Update
    void updateNote(Notes notes);

    @Delete
    void deleteNote(Notes notes);
}
