package com.techelevator.model;

import java.util.List;

public class Playlist {

    private int playlistID;
    private String playlistName;
    private String playlistDescription;

    public Playlist(int playlistID, String playlistName, String playlistDescription) {
        this.playlistID = playlistID;
        this.playlistName = playlistName;
        this.playlistDescription = playlistDescription;
    }

    public Playlist() {
    }

    public int getPlaylistID() {
        return playlistID;
    }

    public void setPlaylistID(int playlistID) {
        this.playlistID = playlistID;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public void setPlaylistDescription(String playlistDescription) {
        this.playlistDescription = playlistDescription;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public String getPlaylistDescription() {
        return playlistDescription;
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "playlistName='" + playlistName + '\'' +
                ", playlistDescription='" + playlistDescription + '\'' +
                '}';
    }
}
