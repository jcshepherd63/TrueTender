package com.techelevator.dao;

import com.techelevator.model.Song;

import java.time.LocalDate;
import java.util.List;

public interface SongDao {

    Song getSongByID(int song_Id);

    List<Song> getSongs();

    Song getSongBySongName(String songName);

    Song getSongByArtist(String songArtist);

    Song getSongByAddDate(LocalDate addDate);

    List<Song> getSongByPlaylistID(int playlist_Id);

    Song createSong(Song song);

    Song updateSongByID(Song song, int song_id);

    void deleteSong(int id);
}
