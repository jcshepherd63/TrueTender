<template>
  <section id="playlists">
    <h2>Current Genres</h2>
    <ul id="playlist-names">
      <li
        v-for="playlist in playlists"
        v-bind:key="playlist.playlistName"
        @dblclick="deletePlaylist(playlist.playlistID)"
        :class="{
          selected: playlist.playlistName === $store.state.currentPlaylistName,
        }"
      >
          {{ playlist.playlistName }}
          <button @click="updatePlaylist(playlist.playlistID)">Edit</button>
      </li>
    </ul>
  </section>
</template>

<script>
import { resourceService } from "../services/resourceService";
export default {
  computed: {
    playlists() {
      return this.$store.state.playlists;
    },
  },
  methods: {
    selected(playlistName) {
      this.$store.commit("SET_CURRENT_PLAYLIST_NAME", playlistName);
    },
    isAdmin() {
      return (
        this.$store.state.user &&
        this.$store.state.user.role.includes("ROLE_ADMIN")
      );
    },
    deletePlaylist(playlist_id) {
      console.log(this.$store.state.user);
      if (
        this.isAdmin() &&
        window.confirm("Are you sure you wish to delete this playlist?")
      ) {
        resourceService.deletePlaylist(playlist_id).then((response) => {
          if (response.status == 204) {
            resourceService.getPlaylists().then((response) => {
              this.$store.commit("SET_CURRENT_PLAYLIST_NAME", response.data);
            });
          }
        });
      }
    },
    updatePlaylist(playlistID) {
      const playlistDetails = resourceService.getPlaylistById(playlistID);
      this.$router.push({
        name: "editPlaylist",
        params: { playlistID: playlistID },
      });
    },
  },
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

#playlists ul {
  list-style: none;
  text-align: center;
}

#playlists li {
  display: flex;
  background-color: #adb6c4;
  border-radius: 30px;
  margin-bottom: 10px;
  justify-content: space-between;
  align-items: center;
  margin-right: 40px;
  padding-left: 10px;
}

#playlist-names {
  flex-grow: 1;
}
/* #currentSongs li {
  background-color: #adb6c4;
  border-radius: 30px;
  margin-bottom: 10px;
  justify-content: space-between;
  align-items: center;
  display: flex;
  margin-right: 40px;
  padding-left: 10px;
} */

li button {
  list-style: none;
  background-color: #294c60;
  border-radius: 30px;
  text-align: center;
  margin-top: 1px;
  margin-left: auto;
  color: #ffefd3;
  justify-content: right;
}

</style>