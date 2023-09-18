package com.daniel.letris.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "statistics")
public class Statistics implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "games_played")
    private int games_played = 0;

    @ColumnInfo(name = "words_number")
    private int words_number = 0;

    @ColumnInfo(name = "letter_storage")
    private String letter_storage;

    public int getGames_played() {
        return games_played;
    }

    public void setGames_played(int games_played) {
        this.games_played = games_played;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWords_number() {
        return words_number;
    }

    public void setWords_number(int words_number) {
        this.words_number = words_number;
    }

    public String getLetter_storage() {
        return letter_storage;
    }

    public void setLetter_storage(String letter_storage) {
        this.letter_storage = letter_storage;
    }
}
