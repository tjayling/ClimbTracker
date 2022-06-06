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
    .appendChild(document.createTextNode(`you are logged in as ${username}`));
}

function logOut() {
  deleteCookie(`id`);
  deleteCookie(`username`);
  deleteCookie(`first-name`);
  deleteCookie(`last-name`);
  window.location.href = "./login.html";
}

function testData() {
  let testRoutes = [
    { grade: `v1` },
    { grade: `v1` },
    { grade: `v3` },
    { grade: `v4` },
  ];

  let testClimbs = [
    {
      user: { id: 1 },
      route: { id: 1 },
      timeTaken: `31`,
      completedClimb: `true`,
    },
    {
      user: { id: 1 },
      route: { id: 3 },
      timeTaken: `45`,
      completedClimb: `true`,
    },
    {
      user: { id: 1 },
      route: { id: 2 },
      timeTaken: `12`,
      completedClimb: `false`,
    },
  ];

  fetch(`http://localhost:8080/route/createMany`, {
    method: `post`,
    headers: { "Content-type": "application/json" },
    body: JSON.stringify(testRoutes),
  }).then((response) => {
    if (response.status !== 201) {
      console.error(`status: ${response.status}`);
      return;
    }
  });

  fetch(`http://localhost:8080/climb/createMany`, {
    method: `post`,
    headers: { "Content-type": "application/json" },
    body: JSON.stringify(testClimbs),
  }).then((response) => {
    if (response.status !== 201) {
      console.error(`status: ${response.status}`);
      return;
    }
    // response.json().then((data) => {
    //   logIn();
    // });
  });
}

(function () {
  document.addEventListener("DOMContentLoaded", init);
})();
