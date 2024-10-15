<template>
  <div id="register">
    <form v-on:submit.prevent="register">
      <h1>Create Account</h1>
      <div id="fields">
        <label for="username">Username</label>
        <input
          type="text"
          id="username"
          placeholder="Username"
          v-model="user.username"
          required
          autofocus
        />
        <label for="name">Name</label>
        <input
          type="text"
          id="name"
          placeholder="Name"
          v-model="user.name"
          required
        />
        <label for="password">Password</label>
        <input
          type="password"
          id="password"
          placeholder="Password"
          v-model="user.password"
          required
        />
        <label for="confirmPassword">Confirm password</label>
        <input
          type="password"
          id="confirmPassword"
          placeholder="Confirm Password"
          v-model="user.confirmPassword"
          required
        />

        <label for="address">Address</label>
        <input
          type="text"
          id="address"
          placeholder="Address"
          v-model="user.address"
        />

        <label for="city">City</label>
        <input type="text" id="city" placeholder="City" v-model="user.city" />

        <label for="state">State</label>
        <input
          type="text"
          id="state"
          placeholder="State"
          v-model="user.stateCode"
          maxlength="2"
          required
        />

        <label for="zip">ZIP</label>
        <input
          type="number"
          id="zip"
          placeholder="ZIP"
          v-model="user.zip"
          required
          minlength="5"
          maxlength="5"
        />
        <div></div>
        <div>
          <button type="submit">Create Account</button>
        </div>
      </div>
      <div id="loginLink">
        <hr />
          Have an account?
        <router-link v-bind:to="{ name: 'login' }">
          <button>Sign in! </button> 
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
        name: "",
        password: "",
        confirmPassword: "",
        address: "",
        city: "",
        stateCode: "",
        zip: "",
        role: "user",
      },
    };
  },
  methods: {
    error(msg) {
      alert(msg);
    },
    success(msg) {
      alert(msg);
    },
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.error("Password & Confirm Password do not match");
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.success("Thank you for registering, please sign in.");
              this.$router.push({
                path: "/login",
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            if (!response) {
              this.error(error);
            } else if (response.status === 400) {
              if (response.data.errors) {
                // Show the validation errors
                let msg = "Validation error: ";
                for (let err of response.data.errors) {
                  msg += `'${err.field}':${err.defaultMessage}. `;
                }
                this.error(msg);
              } else {
                this.error(response.data.message);
              }
            } else {
              this.error(response.data.message);
            }
          });
      }
    },
  },
};
</script>

<style scoped>
  #register {
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

  #loginLink {
    color: #ffefd3;
  }

  @media only screen and (max-width: 425px) {
    #register {
      width: auto;
      justify-content: center;
      width: 730px;

    }
  }

</style>
