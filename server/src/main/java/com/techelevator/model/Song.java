package com.techelevator.model;

import java.time.LocalDate;

public class Song {
    private String songName;
    private int songID;
    private String songArtist;
    private LocalDate addedDate;

    public String getSongArtist() {
        return songArtist;
    }

    public void setSongArtist(String songArtist) {
        this.songArtist = songArtist;
    }

    public Song(int songID, String songName, String songArtist , LocalDate addedDate){
        this.songID = songID;
        this.songName = songName;
        this.addedDate = addedDate;
        this.songArtist = songArtist;
    }

    public Song() {

    }


    public String getSongName() {
        return songName;
    }
    public int getSongID() {
        return songID;
    }

    public LocalDate getAddedDate() {
        return addedDate;
    }
    public void setSongName(String songName) {
        this.songName = songName;
    }
    public void setSongID(int songID) {
        this.songID = songID;
    }
    public void setAddedDate(LocalDate addedDate) {
        this.addedDate = addedDate;
    }


    @Override
    public String toString() {
        return "Song{" +
                "songName='" + songName + '\'' +
                ", songID=" + songID +
                ", addedDate=" + addedDate +
                '}';
    }
}
