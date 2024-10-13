<template>
  <section id="playlists">
    <h2>Playlists</h2>
    <ul id="playlists">
        <li v-for="playlistGenre in playlistGenres" v-bind:key="playlistGenre.genre">
            {{ playlistGenre.genre }}
        </li>
    </ul>
  </section>
</template>

<script>
export default {
    computed: {
        playlistGenres() {
            return this.$store.state.playlistGenres;
        }
    }


};
</script>

<style>
#playlists {
  grid-area: changePlaylist;
  background-color: #ffefd3;
  border: 5px solid #adb6c4;
  border-radius: 30px;
  margin-top: 5px;
  margin-right: 5px;
}

#playlists {
  list-style: none;
  text-align: center;
}

#playlists li {
  background-color: lightblue;
  border-radius: 30px;
  margin-bottom: 10px;
}
</style>