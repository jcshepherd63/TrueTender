import axios from "axios";

const resourceService = {
getPlaylist(){
    return axios.get('/songs')
  },
  getPlaylists(){
    return axios.get('/playlists')
  },
  addSong(song){
    return axios.post('/songs', song)
  },
  addPlaylist(playlist){
    return axios.post('/playlists', playlist)
  },
  deletePlaylist(playlist_id){
    return axios.delete(`/playlists/${playlist_id}`)
  },
  deleteSong(song_id){
    return axios.delete(`/songs/${song_id}`)
  },
  updateSong(songID, song){
    return axios.put(`/songs/${songID}`, song)
  },
  getSongById(songID) {
    return axios.get(`/songs/${songID}`);
  },
  updatePlaylist(playlistID, playlist){
    return axios.put(`/playlists/${playlistID}`, playlist)
  },
  getPlaylistById(playlistID) {
    return axios.get(`/playlists/${playlistID}`);
  },
};



export { resourceService };
