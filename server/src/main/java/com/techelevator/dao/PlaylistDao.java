package com.techelevator.dao;

import com.techelevator.model.Playlist;
import com.techelevator.model.Song;

import java.util.List;

public interface PlaylistDao {

    Playlist getPlaylistByID(int playlist_Id);

    List<Playlist> getPlaylists();

    Playlist getPlaylistByPlaylistName(String playlistName);
    void addSongToPlaylist(int playlist_id, int song_id);

    Playlist createPlaylist(Playlist addedPlaylist);

    Playlist updatePlaylistByID(int playlistID, Playlist playlist);

    void deletePlaylist(int id);
}
