package com.carlostorres.ibericajava.data.local.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes_table")
public class Notes {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String description;
    private Long timeStamp;

    public Notes(String title, String description, Long timeStamp) {
        this.title = title;
        this.description = description;
        this.timeStamp = timeStamp;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }
}
