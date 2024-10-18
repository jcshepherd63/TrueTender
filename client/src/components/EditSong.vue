<template>
  <div id="editSong">
    <h1>Edit Current Song</h1>
    <div id="songFlex">
      <form id="songForm" v-on:submit.prevent="updateSong">
        <h2>Song Details</h2>
        <label for="songName"> Song Name: </label>
        <input type="text" id="songName" v-model="song.songName" placeholder="Song Name" required />
        <label for="songArtist"> Song Artist: </label>
        <input type="text" id="songArtist" v-model="song.songArtist" placeholder="Artist/Band" />
        <label for="songUrl"> Song Youtube URL: </label>
        <input type="text" id="songUrl" v-model="song.songUrl" placeholder="Youtube URL" />
        <div><button type="submit">Update Song</button></div>
      </form>
    </div>
  </div>
</template>

<script>
import { resourceService } from '../services/resourceService';
export default {
  data() {
    return {
      song: {
        songName: "",
        songArtist: "",
        songUrl: ""
      },
    };
  },

  methods: {
    created() {
    const songId = this.$route.params.songID;
    resourceService.getSongById(songId)
        .then(response => {
            this.song = response.data;
        })
      },
    updateSong() {

      const songID = this.$route.params.songID;
      console.log("Updating song:", this.song);
      resourceService
        .updateSong(songID, this.song)
        .then((response) => {
          if(response.status ==200){
            this.$router.push({
              name: "home"
            })
          }
        })
    }
  }
};
</script>

<style scoped>
h1 {
  color: #ffefd3;
  font-size: 30pt;
  text-shadow: 3px 3px 3px black;
  text-align: center;
  border-radius: 30px;
}

#songForm {
  display: flex;
  flex-direction: column;
  background: #ffefd3;
  width: auto;
  border-radius: 30px;
  border: 5px solid #adb6c4;
  align-items: center;
}

input {
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
    #songForm {
      justify-content: center;
      width: 700px;
    }
    h1 {
      width: 700px;
    }
  }
</style>