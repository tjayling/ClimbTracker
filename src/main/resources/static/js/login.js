"use strict";

function init() {}

function toggleSignUp() {
  document.title="sign up";
  document.getElementById("login-button").style.display = "none";
  document.getElementById("signup-button").style.display = "block";
  document.getElementById("signup-container").style.display = "block";
}

function toggleLogIn() {
  document.title="log in";
  document.getElementById("signup-button").style.display = "none";
  document.getElementById("signup-container").style.display = "none";
  document.getElementById("login-button").style.display = "block";
}

function logIn() {
  let username = document.getElementById(`username-input`).value;
  let password = document.getElementById(`password-input`).value;
  fetch(`http://localhost:8080/user/readBy/username:${username}`).then(
    (response) => {
      if (response.status !== 200) {
        console.error(`status: ${response.status}`);
        return;
      }
      response.json().then((data) => {
        data[0][`password`] === password
          ? initSession(data[0])
          : passwordIncorrect();
      });
    }
  );
}

function signUp() {
  let firstName = document.getElementById(`first-name-input`).value;
  let lastName = document.getElementById(`last-name-input`).value;
  let username = document.getElementById(`username-input`).value;
  let password = document.getElementById(`password-input`).value;

  fetch(`http://localhost:8080/user/create`, {
    method: `post`,
    headers: { "Content-type": "application/json" },
    body: JSON.stringify({
      firstName: firstName,
      lastName: lastName,
      username: username,
      password: password,
    }),
  }).then((response) => {
    if (response.status !== 201) {
      console.error(`status: ${response.status}`);
      return;
    }
    response.json().then((data) => {
      logIn();
    });
  });
}

function initSession(data) {
  setCookie(`id`, `${data[`id`]}`, 7);
  setCookie(`username`, data[`username`], 7);
  setCookie(`first-name`, data[`firstName`], 7);
  setCookie(`last-name`, data[`lastName`], 7);
  window.location.href = "./home.html";
}

(function () {
  document.addEventListener("DOMContentLoaded", init);
})();
