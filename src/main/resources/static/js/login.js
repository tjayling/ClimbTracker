"use strict";

function init() {}

function toggleSignUp() {
  document.title = "sign up";
  document.getElementById("login-button").style.display = "none";
  document.getElementById("signup-button").style.display = "block";
  document.getElementById("signup-container").style.display = "block";
}

function toggleLogIn() {
  document.title = "log in";
  document.getElementById("signup-button").style.display = "none";
  document.getElementById("signup-container").style.display = "none";
  document.getElementById("login-button").style.display = "block";
}

async function logIn() {
  let username = document.getElementById(`username-input`).value;
  let password = document.getElementById(`password-input`).value;
  let tryAgain = false;
  tryAgain = username ? false : appendError(`please enter a username`);
  if (tryAgain) return;
  tryAgain = password ? false : appendError(`please enter a password`);
  if (tryAgain) return;
  let response = await fetch(
    `http://localhost:8080/user/readBy/username:${username}`
  );

  if (response.status !== 200) console.error(`status: ${response.status}`);
  let data = await response.json();
  console.log(data.length);
  tryAgain = data.length == 0 ? appendError(`incorrect username`) : false;
  if (tryAgain) return;
  data[0][`password`] === password
    ? initSession(data[0])
    : appendError(`incorrect password`);
}

function appendError(error) {
  let div = document.getElementById(`error-div`);
  let p = document
    .createElement(`p`)
    .appendChild(document.createTextNode(error));
  div.hasChildNodes()
    ? div.replaceChild(p, div.firstChild)
    : div.appendChild(p);
  return true;
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
  return false;
}

(function () {
  document.addEventListener("DOMContentLoaded", init);
})();
