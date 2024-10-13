package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Playlist;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPlaylistDao implements PlaylistDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcPlaylistDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Playlist getPlaylistByID(int playlist_Id) {
        Playlist playlist = null;
        String sql = "SELECT * FROM playlist WHERE playlist_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, playlist_Id);
            if (results.next()) {
                playlist = mapRowToPlaylist(results);
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return playlist;
    }

    @Override
    public List<Playlist> getPlaylists(){
        List<Playlist> playlists = new ArrayList<>();
        String sql = "SELECT * FROM playlist ORDER BY playlist_name";

        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()){
                Playlist playlist = mapRowToPlaylist(results);
                playlists.add(playlist);
            }
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Can't connect to database", e);
        }
        return playlists;
    }

    @Override
    public Playlist getPlaylistByPlaylistName(String playlistName){
        if (playlistName == null){
            playlistName = "";
        }
        Playlist playlist = null;
        String sql = "SELECT * FROM playlist WHERE playlist_name = ?";

        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, playlistName);
            if (results.next()){
                playlist = mapRowToPlaylist(results);
            }
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Can't connect to database", e);
        }
        return playlist;
    }
    @Override
    public void addSongToPlaylist(int playlist_id, int song_id){
        String sql = "INSERT INTO playlist_song (playlist_id, song_id) VALUES (?,?);";

        try {
            jdbcTemplate.update(sql, playlist_id, song_id);
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Can't connect to database", e);
        }
    }

    @Override
    public Playlist createPlaylist(Playlist playlist){
        Playlist playlist1 = null;

        String sql = "INSERT INTO playlist (playlist_name, playlist_description)" + " VALUES (?, ?) RETURNING playlist_id;";
        try{
            Integer playlistID = jdbcTemplate.queryForObject(sql, int.class, playlist.getPlaylistName(),playlist.getPlaylistDescription());
            playlist1 = getPlaylistByID(playlistID);
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Can't connect to database", e);
        }
        return playlist1;
    }

    @Override
    public Playlist updatePlaylistByID(int playlistID, Playlist playlist) {
        Playlist updatedPlaylist = null;

        String sql = "UPDATE playlist SET playlist_name = ?, playlist_description = ? WHERE playlist_ID = ?";

        try {
            int count = jdbcTemplate.update(sql, playlist.getPlaylistName(), playlist.getPlaylistDescription(), playlistID);
            if(count == 0){
                throw new DaoException("Zero rows affected, at least 1 expected");
            }
            updatedPlaylist = getPlaylistByID(playlist.getPlaylistID());
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("can't connect to the database", e);
        }
        return updatedPlaylist;
    }

    @Override
    public void deletePlaylist(int playlist_id){
        jdbcTemplate.update("DELETE FROM playlist WHERE playlist_id = ?", playlist_id);
    }


    private Playlist mapRowToPlaylist(SqlRowSet results) {
        Playlist playlist = new Playlist();
        playlist.setPlaylistID(results.getInt("playlist_id"));
        playlist.setPlaylistName(results.getString("playlist_name"));
        playlist.setPlaylistDescription(results.getString("playlist_description"));

        return playlist;
    }
}

