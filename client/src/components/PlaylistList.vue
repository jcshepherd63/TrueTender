<template>
  <section id="playlists">
    <h2>Current Playlists</h2>
    <ul id="playlist-names">
        <li v-for="playlist in playlists" v-bind:key="playlist.playlistName">
            {{ playlist.playlistName }}
        </li>
    </ul>
  </section>
</template>

<script>
export default {
    computed: {
        playlists() {
            return this.$store.state.playlists;
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

#playlists ul{
  list-style: none;
  text-align: center;
}

#playlists li {
  background-color: #adb6c4;
  border-radius: 30px;
  margin-bottom: 10px;
  justify-content: center;
  align-items: center;
  display: flex;
  margin-right: 40px;
}
</style>