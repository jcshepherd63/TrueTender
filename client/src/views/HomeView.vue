<template>
  <div id="home">
    <playlist-list />
    <!-- <popular-songs /> -->
    <current-songs />
    <about-true-tender />
  </div>
</template>

<script>
import PlaylistList from "../components/PlaylistList.vue";
import currentSongs from "../components/CurrentSongs.vue";
import AboutTrueTender from "../components/AboutTrueTender.vue"
import { resourceService} from "../services/resourceService.js";

export default {
  components: { PlaylistList, currentSongs, AboutTrueTender},
  created() {
    this.isLoading = true;
    resourceService.getPlaylists().then( (response) => {
      this.$store.commit("SET_CURRENT_PLAYLIST_NAME", response.data)
    });
    resourceService.getPlaylist().then( (response) => {
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
    "changePlaylist currentSongs currentSongs"
    /* "findNew findNew findNew" */
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

@media only screen and (max-width: 425px) {
  #home {
  display: flex;
  flex-direction: column;
  width: 700px;
  }
}
</style>