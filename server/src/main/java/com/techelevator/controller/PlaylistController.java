package com.techelevator.controller;

import com.techelevator.dao.PlaylistDao;
import com.techelevator.dao.SongDao;
import com.techelevator.model.Playlist;
import com.techelevator.model.Song;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.lang.module.ResolutionException;
import java.util.List;

@RestController
@RequestMapping("/playlists")
public class PlaylistController {
    private final PlaylistDao playlistDao;

    private final SongDao songDao;

    public PlaylistController(PlaylistDao playlistDao, SongDao songDao) {
        this.playlistDao = playlistDao;
        this.songDao = songDao;
    }
    @GetMapping
    public List<Playlist> getAllPlaylists(){
        return playlistDao.getPlaylists();
    }

    @GetMapping(path = "/{playlist_id}")
    public Playlist getPlaylistById(@PathVariable int playlist_id){
        return playlistDao.getPlaylistByID(playlist_id);
    }

    @GetMapping(path = "/playlistname/{playlist_name}")
    public Playlist getPlayListByName (@PathVariable String playlist_name){
        return playlistDao.getPlaylistByPlaylistName(playlist_name);
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @RequestMapping(path = "/{playlist_id}/songs/{song_id}", method = RequestMethod.POST)
    public void addSongToPlaylist(@PathVariable int playlist_id, @PathVariable int song_id){
        Playlist playlist = playlistDao.getPlaylistByID(playlist_id);
        Song song = songDao.getSongByID(song_id);
        if (playlist == null || song == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Playlist or Song not found.");
        }
        playlistDao.addSongToPlaylist(playlist_id, song_id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/{playlist_id}")
    public void deletePlaylist (@PathVariable int playlist_id){
        playlistDao.deletePlaylist(playlist_id);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(value = HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public Playlist addPlaylist (@RequestBody Playlist newPlaylist){
        return playlistDao.createPlaylist(newPlaylist);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(path = "/{playlist_id}")
    public Playlist updatePlaylist (@PathVariable int playlist_id, @RequestBody Playlist playlist){
        return playlistDao.updatePlaylistByID(playlist_id, playlist);
    }
}
