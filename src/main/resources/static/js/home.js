"use strict";

let id, username, firstName, lastName;
let selectedClimbs = [];
let allClimbs = [];

async function init() {
  id = getCookie(`id`);
  username = getCookie(`username`);
  firstName = getCookie(`first-name`);
  lastName = getCookie(`last-name`);
  document
    .getElementById(`username-header`)
    .appendChild(document.createTextNode(`you are logged in as ${username}`));
  await readAll();
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

function testData() {
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
    {
      user: { id: 1 },
      route: { id: 4 },
      timeTaken: `61`,
      completedClimb: `true`,
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
    response.json().then((data) => {
      readAll();
    });
  });
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
      let append = true;
      let tableData = document.createElement(`td`);
      switch (d) {
        case `id`:
          append = false;
          break;
        case `user`:
          append = false;
          break;
        case `route`:
          tableData.appendChild(
            document.createTextNode(
              `${data[i][d][`name`]} (${data[i][d][`grade`]})`
            )
          );
          break;
        case `timeTaken`:
          tableData.appendChild(document.createTextNode(`${data[i][d]}`));
          break;
        case `completedClimb`:
          let image = document.createElement(`img`);
          let src = data[i][d] ? `../img/tick.png` : `../img/cross.png`;
          image.src = src;
          tableData.appendChild(image);
          break;
      }
      if (append) tableRow.appendChild(tableData);
    }
    newTableBody.appendChild(tableRow);
  }

  let oldTableBody = document.getElementById(`climb-table-body`);
  oldTableBody.parentNode.replaceChild(newTableBody, oldTableBody);
}

(function () {
  document.addEventListener("DOMContentLoaded", init);
})();
