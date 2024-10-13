<template>

  <div id="music-app">
    <header id="header">
    <img src="./assets/TrueTender.jpg" alt="TrueTender" />
    <h1>TrueTender</h1>
    <nav>
      <router-link v-bind:to="{ name: 'home' }">
        <button>Home</button>
      </router-link>

      <router-link v-bind:to="{ name: 'logout' }" v-if="this.$store.state.token">
        <button>Logout</button>
      </router-link>

      <router-link v-bind:to="{ name: 'login' }" v-else>
        <button>Login</button>
      </router-link>
    </nav>
  </header>
    <main>
      <router-view />
    </main>
    <footer>Copyright &copy; 2024 All Rights Reserved.</footer>
  </div>
</template>

<style>
html {
  background-color: #001b2e;
}
/* HEADER ---------------------------- */
header {
  background-color: #ffefd3;
  border: 5px solid #adb6c4;
  border-radius: 30px;
  height: 165px;
  width: 98vw;
  /* position: fixed; */
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 20px;
  text-align: center;
}

h1 {
  color: #294c60;
  font-family: fantasy;
  font-size: 40pt;
  text-shadow: 3px 3px 3px #adb6c4;
  border: 5px solid #adb6c4;
  padding: 10px;
  box-shadow: 3px 3px 3px #294c60;
}

/* Logo ------------------------------ */
header img {
  width: 150px;
  height: 150px;
  border-radius: 50%;
  align-items: center;
  box-shadow: 3px 3px 3px #adb6c4;
}

/* Header Nav Bar List --------------- */
nav button {
  list-style: none;
  background-color: #adb6c4;
  border-radius: 5px;
  text-align: center;
  box-shadow: 3px 3px 3px #294c60;
  margin-bottom: 10px;
  margin-right: 10px;
}

body {
  background-color: #001b2e;
}

/* BODY ------------------------------ */
h2 {
  font-size: 20px;
  text-decoration: underline;
  font-family: fantasy;
  text-align: center;
  color: #294c60;
  text-shadow: 3px 3px 3px #adb6c4;
}

/* Footer ----------------------- */
footer {
  height: 25px;
  position: fixed;
  bottom: 20px;
  margin-top: 50px;
  font-size: 12pt;
  color: white;
  font-style: italic;
}
/* Footer ----------------------- */

@media only screen and (max-width: 425px) {
  html {
    background-color: black;
  }

  header {
    height: auto;
    padding: 10px;
  }

  h1 {
    font-size: 24pt;
  }

  nav ul li {
    width: 100%;
    font-size: 12pt;
  }

  main {
    display: flex;
    flex-direction: column;
    padding: 10px;
  }
}
</style>