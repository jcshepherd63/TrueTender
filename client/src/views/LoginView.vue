<template>
  <div id="login">
    <form v-on:submit.prevent="login">
      <h1>Please Sign In</h1>
      <div id="fields">
        <label for="username">Username: </label>
        <input 
          type="text"
          id="username"
          placeholder="Username"
          v-model="user.username"
          required
          autofocus
        />
        <label for="password">Password: </label>
        <input 
          type="password"
          id="password"
          placeholder="Password"
          v-model="user.password"
          required
        />
        <div><button type="submit">Sign in</button></div>
      </div>
    <div id="registerLink">
      <hr/>
      Need an account? <router-link v-bind:to="{ name: 'register' }">
        <button>Register!</button> 
      </router-link>
    </div> 
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  data() {
    return {
      user: {
        username: "",
        password: "",
      },
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then((response) => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch((error) => {
          const response = error.response;
          if (!response) {
            alert(error);
          } else if (response.status === 401) {
            alert("Invalid username and password!");
          } else {
            alert(response.message);
          }
        });
    },
  },
};
</script>

<style scoped>
  #login {
    display: flex;
    flex-direction: column;
    align-items: center;
  }

  h1{
    color: #ffefd3;
    font-size: 30pt;
    text-shadow: 3px 3px 3px black;
    text-align: center;
    border-radius: 30px;
  }

  #fields{
    display: flex;
    flex-direction: column;
    background: #ffefd3;
    width: 97vw;
    border-radius: 30px;
    border: 5px solid #adb6c4;
    align-items: center;
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
  label{
    color: #294c60;
    text-align: center;
  }
  input{
    width: 300px;
    margin-top: 5px;
    margin-bottom: 5px;
  }

  #registerLink {
    color: #ffefd3;
  }
</style>
