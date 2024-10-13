<template>
  <section id="current-songs">
    <h2>Current Songs</h2>
    <ul id="currentSongs">
        <li v-for="playlist in playlist" :key="playlist.songName">
            {{ playlist.songName }}, {{ playlist.artist }}
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
#current-songs {
  grid-area: currentSongs;
  background-color: #ffefd3;
  border: 5px solid #adb6c4;
  border-radius: 30px;
  margin-top: 5px;
}
</style>