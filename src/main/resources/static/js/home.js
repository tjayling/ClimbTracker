"use strict";

let id, username, firstName, lastName;

function init() {
  id = getCookie(`id`);
  username = getCookie(`username`);
  firstName = getCookie(`first-name`);
  lastName = getCookie(`last-name`);
  console.log("Username " + username);
  document
    .getElementById(`username-header`)
    .appendChild(document.createTextNode(username));
}

function logOut() {
    deleteCookie(`id`)
    deleteCookie(`username`);
    deleteCookie(`first-name`);
    deleteCookie(`last-name`);
    window.location.href = "./login.html";
}

(function () {
  document.addEventListener("DOMContentLoaded", init);
})();
