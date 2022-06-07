"use strict";

function initError() {
  let errorDivs = document.querySelectorAll(`.error`);
  let img = document.createElement(`img`);
  img.src = `../img/warning.png`;
  img.classList.add(`warning-img`);
  errorDivs.forEach((div) => div.appendChild(img.cloneNode()));
}

function appendError(divId, error) {
  let div = document.getElementById(`${divId}-error`);
  let p = document.createElement(`p`);
  p.appendChild(document.createTextNode(error));
  if (div.childElementCount == 1) div.appendChild(p);
  div.style.display = `block`;
  return true;
}

function removeError(divId) {
  document.getElementById(`${divId}-error`).style.display = `none`;
  return false;
}

(function () {
  document.addEventListener("DOMContentLoaded", initError);
})();
