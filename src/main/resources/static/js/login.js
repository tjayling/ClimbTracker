"use strict";

function initLogin() {}

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

  // check for any empty inputs
  let tryAgain = username
    ? removeError(`username`)
    : appendError(`username`, `please enter a username`);
  if (!tryAgain)
    tryAgain = password
      ? removeError(`password`)
      : appendError(`password`, `please enter a password`);
  // if there were any missing inputs, break of out the function to prevent fetch errors
  if (tryAgain) return;

  let response = await fetch(
    `http://localhost:8080/user/readBy/username:${username}`
  );

  if (response.status !== 200) console.error(`status: ${response.status}`);
  let data = await response.json();
  console.log(data.length);
  tryAgain =
    data.length > 0
      ? removeError(`username`)
      : appendError(`username`, `incorrect username`);
  if (tryAgain) return;
  data[0][`password`] === password
    ? initSession(data[0])
    : appendError(`password`, `incorrect password`);
}

async function signUp() {
  let firstName = document.getElementById(`first-name-input`).value;
  let lastName = document.getElementById(`last-name-input`).value;
  let username = document.getElementById(`username-input`).value;
  let password = document.getElementById(`password-input`).value;

  // check for any empty inputs - variables will be falsey if left empty
  let tryAgain = firstName
    ? removeError(`first-name`) /* returns false */
    : appendError(
        `first-name`,
        `please enter your first name`
      ); /* returns true */
  if (!tryAgain)
    tryAgain = lastName
      ? removeError(`last-name`)
      : appendError(`last-name`, `please enter your last name`);
  if (!tryAgain)
    tryAgain = username
      ? removeError(`username`)
      : appendError(`username`, `please enter a username`);
  if (!tryAgain)
    tryAgain = password
      ? removeError(`password`)
      : appendError(`password`, `please enter a password`);
  // if there were any missing inputs, break of out the function to prevent fetch errors
  if (tryAgain) return;

  // fetch data
  let response = await fetch(`http://localhost:8080/user/create`, {
    method: `post`,
    headers: { "Content-type": "application/json" },
    body: JSON.stringify({
      firstName: firstName,
      lastName: lastName,
      username: username,
      password: password,
    }),
  });
  if (response.status !== 201) console.error(`status: ${response.status}`);
  await response.json();
  logIn();
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
  document.addEventListener("DOMContentLoaded", initLogin);
})();
