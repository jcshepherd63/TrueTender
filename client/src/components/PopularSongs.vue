<template>
  <section id="popular-songs">
    <h2>Recent Popular Songs</h2>
    <ul id="popularSongs"></ul>
  </section>
</template>

<script>
export default {};
</script>

<style>
#popular-songs {
  grid-area: findNew;
  display: flex;
  justify-content: center;
  background-color: #ffefd3;
  border: 5px solid #adb6c4;
  border-radius: 30px;
  margin-top: 5px;
  margin-right: 5px;
}

#popularSongs li {
  list-style: none;
  border-radius: 30px;
  padding: 10px;
  margin-right: 5px;
  margin-left: 5px;
}
</style>