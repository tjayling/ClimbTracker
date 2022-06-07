"use strict";

let userId, username, firstName, lastName;
let selectedClimbs = [];
let allClimbs = [];

async function initHome() {
  userId = getCookie(`id`);
  username = getCookie(`username`);
  firstName = getCookie(`first-name`);
  lastName = getCookie(`last-name`);
  document
    .getElementById(`username-header`)
    .appendChild(document.createTextNode(`you are logged in as ${username}`));
  await readAll();
  await readAllRoutes();
}

function toggleDeleteAllButton() {
  document.getElementById(`delete-all-button`).style.display =
    allClimbs.length > 0 ? `block` : `none`;
}

function toggleDeleteSelectedButton() {
  getSelectedClimbs();
  document.getElementById(`delete-selected-button`).style.display =
    selectedClimbs.length > 0 ? `block` : `none`;
}

function clearInput() {
  document.getElementById("route-options-input").value = ``;
}

async function checkInputs() {
  let route = document.getElementById(`route-options-input`).value;
  let timeTaken = parseInt(document.getElementById(`time-input`).value);
  let attempts = parseInt(document.getElementById(`attempts-input`).value);
  let nanError = `please enter a number`;
  // route will be falsey if the user hasn't selected a route
  let tryAgain = route
    ? removeError(`route`)
    : appendError(`route`, `please select a route`);
  // time taken will be falsey if user doesn't input an integer
  if (!tryAgain)
    tryAgain = timeTaken ? removeError(`time`) : appendError(`time`, nanError);
  // attempts will be falsey if user doesn't input an integer
  if (!tryAgain)
    tryAgain = attempts
      ? removeError(`attempts`)
      : appendError(`attempts`, nanError);

  if (tryAgain) return;

  let routeId = route.split(`:`)[0];

  await createClimb(routeId, timeTaken, attempts);
  await readAll();
}

async function createClimb(routeId, timeTaken, attempts) {
  let response = await fetch(`http://localhost:8080/climb/create`, {
    method: `post`,
    headers: { "Content-type": "application/json" },
    body: JSON.stringify({
      user: { id: userId },
      route: { id: routeId },
      timeTaken: timeTaken,
      attempts: attempts,
    }),
  });
  if (response.status !== 201) console.error(response.status);
}

function generateRoutesDatalist(data) {
  let routeDatalist = document.createElement(`datalist`);
  routeDatalist.id = `route-options`;
  let routeStrings = data.map(
    (route) => `${route.id}: ${route.name} (${route.grade})`
  );
  for (let str of routeStrings) {
    let routeOption = document.createElement(`option`);
    routeOption.value = str;
    routeOption.appendChild(document.createTextNode(str));
    routeDatalist.appendChild(routeOption);
  }
  document.getElementById(`route-options`).replaceWith(routeDatalist);
}

function getSelectedClimbs() {
  let checkboxes = document.getElementsByClassName(`climb-checkbox`);
  // get a list of active boxes using filter method
  let activeBoxes = [...checkboxes].filter((checkbox) => checkbox.checked);
  // if there are active boxes, we return an array of IDs, otherwise an empty array
  selectedClimbs =
    activeBoxes.length > 0
      ? activeBoxes.map((box) => box.id.split(`-`)[1])
      : [];
}

function logOut() {
  deleteCookie(`id`);
  deleteCookie(`username`);
  deleteCookie(`first-name`);
  deleteCookie(`last-name`);
  window.location.href = "./login.html";
}

async function testData() {
  let testRoutes = [
    { name: `Booooring`, grade: `v1` },
    { name: `The Techy One`, grade: `v2` },
    { name: `Ow My Fingers`, grade: `v3` },
    { name: `The Beast!`, grade: `v4` },
  ];

  let testClimbs = [
    {
      user: { id: 1 },
      route: { id: 1 },
      timeTaken: `31`,
      attempts: 1,
    },
    {
      user: { id: 1 },
      route: { id: 3 },
      timeTaken: `45`,
      attempts: 3,
    },
    {
      user: { id: 1 },
      route: { id: 2 },
      timeTaken: `12`,
      attempts: 9,
    },
    {
      user: { id: 1 },
      route: { id: 4 },
      timeTaken: `61`,
      attempts: 4,
    },
  ];

  let routeResponse = await fetch(`http://localhost:8080/route/createMany`, {
    method: `post`,
    headers: { "Content-type": "application/json" },
    body: JSON.stringify(testRoutes),
  });
  if (routeResponse.status !== 201)
    console.error(`status: ${routeResponse.status}`);

  let climbResponse = await fetch(`http://localhost:8080/climb/createMany`, {
    method: `post`,
    headers: { "Content-type": "application/json" },
    body: JSON.stringify(testClimbs),
  });

  if (climbResponse.status !== 201) console.error(`status: ${response.status}`);

  await routeResponse.json();
  await climbResponse.json();

  readAll();
  readAllRoutes();
}

async function readAll() {
  let response = await fetch(`http://localhost:8080/climb/readAll`);
  if (response.status !== 200) console.error(`status: ${response.status}`);
  let data = await response.json();
  allClimbs = data;
  addListItems(data);
  toggleDeleteAllButton();
  toggleDeleteSelectedButton();
}

async function readAllRoutes() {
  let response = await fetch(`http://localhost:8080/route/readAll`);
  if (response.status !== 200) console.error(`status: ${response.status}`);
  let data = await response.json();
  generateRoutesDatalist(data);
}

async function deleteAll() {
  if (confirm(`Are you sure you want to delete all entries?`)) {
    let ids = allClimbs.map((object) => object.id);
    let response = await fetch(`http://localhost:8080/climb/delete/${ids}`, {
      method: `delete`,
    });
    if (response.status !== 204) console.error(`status: ${response.status}`);
    await readAll();
  }
}

async function deleteSelected() {
  let str = `Are you sure you want to delete entries ${selectedClimbs
    .join(`, `)
    .replace(/, ([^,]*)$/, " and $1")}`;
  if (confirm(str)) {
    let response = await fetch(
      `http://localhost:8080/climb/delete/${selectedClimbs}`,
      { method: `delete` }
    );
    if (response.status !== 204) console.error(`status: ${response.status}`);
    await readAll();
    toggleDeleteSelectedButton();
  }
}

function addListItems(data) {
  let newTableBody = document.createElement(`tbody`);
  newTableBody.id = `climb-table-body`;

  for (let i in data) {
    let tableRow = document.createElement(`tr`);
    tableRow.id = `row${data[i][`id`]}`;
    let checkBox = document.createElement(`input`);
    checkBox.type = `checkbox`;
    checkBox.id = `climb-${data[i][`id`]}`;
    checkBox.classList.add(`climb-checkbox`);
    checkBox.addEventListener("change", () => toggleDeleteSelectedButton());
    tableRow.appendChild(checkBox);
    for (let d in data[i]) {
      let tableData = document.createElement(`td`);
      switch (d) {
        case `route`:
          tableData.appendChild(
            document.createTextNode(
              `${data[i][d][`name`]} (${data[i][d][`grade`]})`
            )
          );
          tableRow.appendChild(tableData);
          break;
        case `timeTaken`:
        case `attempts`:
          tableData.appendChild(document.createTextNode(`${data[i][d]}`));
          tableRow.appendChild(tableData);
          break;
      }
    }
    newTableBody.appendChild(tableRow);
  }

  let oldTableBody = document.getElementById(`climb-table-body`);
  oldTableBody.parentNode.replaceChild(newTableBody, oldTableBody);
}

(function () {
  document.addEventListener("DOMContentLoaded", initHome);
})();
