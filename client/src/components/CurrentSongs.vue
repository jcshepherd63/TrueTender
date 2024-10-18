<template>
  <section id="current-songs">
    <h2>Current Songs</h2>
    <ul id="currentSongs">
      <li id="songActions"
        v-for="song in playlist"
        :key="song.songName"
        @dblclick="deleteSong(song.songID)"
      >
        <!-- <router-link
          v-bind:to="{ name: 'editSong', params: { songID: song.songID } }"
        > -->
          {{ song.songName }} {{ "-- " + song.songArtist }} 
        <!-- </router-link> -->
        <button @click="updateSong(song.songID)">Edit</button>
      </li>
    </ul>
    <div id="addSongDiv">
      <router-link id="addSong" v-bind:to="{ name: 'addSong' }">
        <button>Add Song</button>
      </router-link>
    </div>
  </section>
</template>

<script>
import { resourceService } from "../services/resourceService";

export default {
  computed: {
    playlist() {
      return this.$store.state.playlist;
    },
  },
  methods: {
    selected(songID) {
      this.$store.commit("SET_CURRENT_SONGS", songID);
    },
    isAdmin() {
      return (
        this.$store.state.user &&
        this.$store.state.user.role.includes("ROLE_ADMIN")
      );
    },
    deleteSong(song_id) {
      console.log(this.$store.state.user);
      if (
        // this.isAdmin() &&
        window.confirm("Are you sure you wish to delete this song?")
      ) {
        resourceService.deleteSong(song_id).then((response) => {
          if (response.status == 204) {
            resourceService.getPlaylist().then((response) => {
              this.$store.commit("SET_CURRENT_SONGS", response.data);
            });
          }
        });
      }
    },
    updateSong(songID) {
      const songDetails = resourceService.getSongById(songID);
      this.$router.push({
        name: "editSong",
        params: { songID: songID },
      });
    },
  },
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

#currentSong ul {
  list-style: none;
  text-align: center;
}

#currentSongs li {
  background-color: #adb6c4;
  border-radius: 30px;
  margin-bottom: 10px;
  justify-content: space-between;
  align-items: center;
  display: flex;
  margin-right: 40px;
  padding-left: 10px;
}

#addSongDiv {
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

li button {
  list-style: none;
  background-color: #294c60;
  border-radius: 30px;
  text-align: center;
  margin-top: 1px;
  margin-left: 50px;
  color: #ffefd3;
  justify-content: right;
}

#songActions {
  display: flex;
  flex-direction: row;
}
</style>