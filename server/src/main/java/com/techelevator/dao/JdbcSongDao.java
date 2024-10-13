package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Song;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcSongDao implements SongDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcSongDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Song getSongByID(int song_Id) {
        Song song = null;
        String sql = "SELECT * FROM song WHERE song_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, song_Id);
            if (results.next()) {
                song = mapRowToSong(results);
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return song;
    }

    @Override
    public List<Song> getSongs(){
        List<Song> songs = new ArrayList<>();
        String sql = "SELECT * FROM song ORDER BY song_name";

        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()){
                Song song = mapRowToSong(results);
                songs.add(song);
            }
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Can't connect to database", e);
        }
        return songs;
    }

    @Override
    public Song getSongBySongName(String songName){
        if (songName == null){
            songName = "";
        }
        Song song = null;
        String sql = "SELECT * FROM song WHERE song_name = ?";

        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, songName);
            if (results.next()){
                song = mapRowToSong(results);
            }
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Can't connect to database", e);
        }
        return song;
    }

    @Override
    public Song getSongByArtist(String songArtist){
        if (songArtist == null){
            songArtist = "";
        }
        Song song = null;
        String sql = "SELECT * FROM song WHERE song_artist = ?";

        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, songArtist);
            if (results.next()){
                song = mapRowToSong(results);
            }
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Can't connect to database", e);
        }
        return song;
    }

    @Override
    public Song getSongByAddDate(LocalDate addDate) {
        Song song = null;
        String sql = "SELECT * FROM song WHERE added_date = ?";

        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, addDate);
            if (results.next()){
                song = mapRowToSong(results);
            }
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Can't connect to database", e);
        }
        return song;
    }

    @Override
    public List<Song> getSongByPlaylistID(int playlist_id){
        List<Song> songByPlaylist = new ArrayList<>();
        String sql = "SELECT * FROM song WHERE song_id IN (SELECT song_id FROM playlist_song WHERE playlist_id = ?);";
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, playlist_id);
            while (results.next()){
                Song song = mapRowToSong(results);
                songByPlaylist.add(song);
            }
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Can't connect to database", e);
        }
        return songByPlaylist;
    }

    @Override
    public Song createSong(Song song){
        Song songToAdd = null;

        String sql = "INSERT INTO song (song_name, song_artist, added_date)" + " VALUES (?, ?, ?) RETURNING song_id;";
        try {
            Integer songID = jdbcTemplate.queryForObject(sql, int.class, song.getSongName(),song.getSongArtist(), song.getAddedDate());
            songToAdd = getSongByID(songID);
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Can't connect to database", e);
        }
        return songToAdd;
    }

    @Override
    public Song updateSongByID(Song song, int song_id){
        Song updatedSong = null;

        String sql = "UPDATE song SET song_name = ?, song_artist = ?, added_date = ? WHERE song_id = ?";

        try {
            int rowsAffect = jdbcTemplate.update(sql, song.getSongName(), song.getSongArtist(), song.getAddedDate(),song_id);
            if (rowsAffect == 0){
                throw new DaoException("Zero rows affected, at least 1 expected");
            }
            updatedSong = getSongByID(song.getSongID());
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Can't connect to the database", e);
        }
        return updatedSong;
    }

    @Override
    public void deleteSong(int song_id){
        jdbcTemplate.update("DELETE FROM song WHERE song_id = ?", song_id);
    }

    private Song mapRowToSong(SqlRowSet results) {
        Song song = new Song();
        song.setSongID(results.getInt("song_id"));
        song.setSongName(results.getString("song_name"));
        song.setSongArtist(results.getString("song_artist"));
        song.setAddedDate(results.getDate("added_date").toLocalDate());

        return song;
    }
}
