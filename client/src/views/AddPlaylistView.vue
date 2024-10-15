<template>
  <div id="addPlaylist">
    <h1>Add A New Playlist</h1>
    <div id="playlistFlex">
      <playlist-list />
      <form id="playlistForm" action="">
        <h2>New Playlist Details</h2>
        <label for="playlistName"> Playlist Name: </label>
        <input
          type="text"
          id="playlistName"
          placeholder="Playlist Name"
          required
        />
        <label for="playlistDescription"> Playlist Description: </label>
        <input
          type="text"
          id="playlistDescription"
          placeholder="Playlist Description"
        />
        <div><button type="submit">Add Playlist</button></div>
      </form>
      <about-true-tender id="about"/>
    </div>
  </div>
</template>

<script>
  import PlaylistList from '../components/PlaylistList.vue';
  import { resourceService } from '../services/resourceService';
  import AboutTrueTender from '../components/AboutTrueTender.vue';
export default {
  components: {PlaylistList, AboutTrueTender},
  created() {
    this.isLoading = true;
    resourceService.getPlaylists().then( (response) => {
      this.$store.commit("SET_PLAYLIST_NAMES", response.data)
    });
    this.isLoading = false;
  },
  data() {
    return {
      playlist: {
        playlistName: "",
        playlistDescription: ""
      },
    };
  },
  

}
</script>

<style scoped>

h1{
    color: #ffefd3;
    font-size: 30pt;
    text-shadow: 3px 3px 3px black;
    text-align: center;
    border-radius: 30px;
  }

#playlistFlex {
  display: grid;
  grid-template-columns: 1fr 1fr;
  grid-template-rows: max-content;
  grid-template-areas: 
  "changePlaylist playlistForm"
  "about about";
}

#about {
  margin-bottom: 35px;
}

#playlistForm {
  display: flex;
    flex-direction: column;
    background: #ffefd3;
    width: auto;
    border-radius: 30px;
    border: 5px solid #adb6c4;
    align-items: center;
}

input{
    width: 300px;
    margin-top: 5px;
    margin-bottom: 5px;
  }

  button {
  list-style: none;
  background-color: #adb6c4;
  border-radius: 5px;
  text-align: center;
  box-shadow: 3px 3px 3px #294c60;
  margin-bottom: 10px;
  margin-right: 10px;
  width: 150px;
  }

  @media only screen and (max-width: 425px) {
  #playlistFlex {
    display: flex;
    flex-direction: column;
    width: 730px;
    margin-top: 10px;
    padding-bottom: 20px;
  }
  h1 {
    width: 730px;
  }
  }
</style>