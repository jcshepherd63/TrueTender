<template>
  <div id="addSong">
    <h1>Add A New Song</h1>
    <div id="songFlex">
      <form id="songForm" action="">
        <h2>New Song Details</h2>
        <label for="songName"> Song Name: </label>
        <input type="text" id="songName" placeholder="Song Name" required />
        <label for="songArtist"> Song Artist: </label>
        <input type="text" id="songArtist" placeholder="Artist/Band" />
        <div><button type="submit">Add Song</button></div>
      </form>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      song: {
        songName: "",
        songArtist: "",
      },
    };
  },
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
</style>