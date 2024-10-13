<template>
  <div id="home">
    <playlist-list />
    <popular-songs />
    <current-songs />
    <about-true-tender />
  </div>
</template>

<script>
import PlaylistList from "../components/PlaylistList.vue";
import PopularSongs from "../components/PopularSongs.vue";
import currentSongs from "../components/CurrentSongs.vue";
import AboutTrueTender from "../components/AboutTrueTender.vue"
import { resourceService} from "../services/resourceService.js";

export default {
  components: { PlaylistList, PopularSongs, currentSongs, AboutTrueTender},
  created() {
    this.isLoading = true;
    resourceService.getPlaylistGenres().then( (response) => {
      this.$store.commit("SET_PLAYLIST_GENRES", response.data)
    });
    resourceService.getPlaylists().then( (response) => {
      this.$store.commit('SET_CURRENT_SONGS', response.data)
    });

    this.isLoading = false;
  },
};
</script>

<style>
#home {
  /* margin-top: 175px; */
  display: grid;
  grid-template-columns: 1fr 2fr 1fr;
  grid-template-rows: 3fr 2fr;
  grid-template-areas:
    "changePlaylist currentSongs addNewPlaylist"
    "findNew findNew findNew"
    "about about about";
}

#playlist-list{
  grid-area: changePlaylist;
}

#popular-songs{
  grid-area: findNew;
}

#current-songs{
  grid-area: currentSongs;
}
</style>