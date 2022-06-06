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
  readAll();
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

function readAll() {
  console.log("Read All");

  fetch(`http://localhost:8080/climb/readAll`)
    .then((response) => {
      if (response.status !== 200) {
        console.error(`status: ${response.status}`);
        return;
      }
      response.json().then((data) => {
        addListItems(data);
        //   /*global*/ datalists = generateDatalists(data);
        //   changeDatalist(activeDatalist);
        //   showOrHideRead(data);
      });
    })
    .catch((error) => console.error(`Request failed: ${error}`));
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
    tableRow.appendChild(checkBox);
    console.log(data[i]);
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
            document.createTextNode(`${data[i][d][`grade`]}`)
          );
          break;
        case `timeTaken`:
          tableData.appendChild(document.createTextNode(`${data[i][d]}`));
          break;
        case `completedClimb`:
          let image = document.createElement(`img`);
          let src = data[i][d] ? `../img/tick.png` : `../img/cross.png`;
          image.src = src;
          if (data[i][d]) {
            console.log("Boolean");
          }
          //   tableData.appendChild(document.createTextNode(`${data[i][d]}`));
          tableData.appendChild(image);
          break;
      }
      if (append) {
        tableRow.appendChild(tableData);
      }

      //   let tableData = document.createElement(`td`);
      //   tableData.appendChild(document.createTextNode(`${data[i][d]}`));
      //   tableRow.appendChild(tableData);
    }

    newTableBody.appendChild(tableRow);
  }

  let oldTableBody = document.getElementById(`climb-table-body`);
  oldTableBody.parentNode.replaceChild(newTableBody, oldTableBody);
}

(function () {
  document.addEventListener("DOMContentLoaded", init);
})();
