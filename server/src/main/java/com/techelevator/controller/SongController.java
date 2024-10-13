package com.techelevator.controller;


import com.techelevator.dao.SongDao;
import com.techelevator.model.Song;
import com.techelevator.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/songs")
public class SongController {
    private final SongDao songDao;

    public SongController(SongDao songDao) {
        this.songDao = songDao;
    }

    @GetMapping(path = "/{song_id}")
    public Song getSong(@PathVariable int song_id) {
        return songDao.getSongByID(song_id);
    }

    @GetMapping
    public List<Song> getAllSongs() {
        return songDao.getSongs();
    }

    @GetMapping(path = "/songname/{song_name}")
    public Song getSongByName (@PathVariable String song_name){
        return songDao.getSongBySongName(song_name);
    }

    @GetMapping(path = "/artist/{artist_name}")
    public Song getSongByArtist (@PathVariable String artist_name){
        return songDao.getSongByArtist(artist_name);
    }
    @GetMapping(path = "/addDate/{added_date}")
    public Song getSongByDate (@PathVariable LocalDate added_date){
        return songDao.getSongByAddDate(added_date);
    }


   @GetMapping(path = "/PlaylistID/{playlist_id}")
    public List<Song> getSongsByPlaylist(@PathVariable int playlist_id){
        return songDao.getSongByPlaylistID(playlist_id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(value = HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public Song addSong(@RequestBody Song newSong) {
        return songDao.createSong(newSong);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(path = "/{song_id}")
    public Song updateSong (@PathVariable int song_id, @RequestBody Song song){
        return songDao.updateSongByID(song, song_id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/{song_id}")
    public void deleteSong (@PathVariable int song_id){
        songDao.deleteSong(song_id);
    }
}
