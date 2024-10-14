<template>
  <section id="current-songs">
    <h2>Current Songs</h2>
    <ul id="currentSongs">
        <li v-for="song in playlist" :key="song.songName">
            {{ song.songName }}
        </li>
    </ul>
    <div id="addSongDiv">
        <router-link id="addSong" v-bind:to=" {name: 'addSong'}">
            <button>Add Song</button>
        </router-link>
    </div>
  </section>
</template>

<script>
export default {
    computed: {
        playlist() {
            return this.$store.state.playlist;
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

#currentSong ul{
  list-style: none;
  text-align: center;
}

#currentSongs li {
  background-color: #adb6c4;
  border-radius: 30px;
  margin-bottom: 10px;
  justify-content: center;
  align-items: center;
  display: flex;
  margin-right: 40px;
}

#addSongDiv{
    display: flex;
    justify-content: flex-end;
}

#addSong button {
  list-style: none;
  background-color: #adb6c4;
  border-radius: 30px;
  text-align: center;
  box-shadow: 3px 3px 3px #294c60;
  margin-bottom: 10px;
  margin-right: 10px;
}
</style>