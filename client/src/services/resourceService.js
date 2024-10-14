import axios from "axios";

const resourceService = {
getPlaylist(){
    return axios.get('/songs')
  },
  getPlaylists(){
    return axios.get('/playlists')
  },
  // getRecentPopularSongs(){
  //   return recentPopularSongs;
  // }
};



export { resourceService };
