import axios from "axios";

const resourceService = {
getPlaylists(){
    return axios.get('/songs')
  },
  getPlaylistGenres(){
    return axios.get('/playlists')
  },
  // getRecentPopularSongs(){
  //   return recentPopularSongs;
  // }
};



export { resourceService };
