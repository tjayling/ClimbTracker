"use strict";

function setCookie(name, value, days) {
  let expires = "";
  if (days) {
    let date = new Date();
    date.setTime(date.getTime() + days * 24 * 60 * 60 * 1000);
    expires = "expires=" + date.toUTCString();
  }
  document.cookie = name + "=" + value + ";" + expires + ";path=/";
}

function getCookie(name) {
  const cookiesJson = {};
  let cookies = document.cookie
    .replace(/\s+/g, "")
    .split(";");
  cookies
    .map((cookie) => cookie.split(`=`))
    .forEach(([key, value]) => (cookiesJson[key] = value));
  return cookiesJson[name];
}

function deleteCookie(name) {
  document.cookie = name + "=; Path=/; Expires=Thu, 01 Jan 1970 00:00:01 GMT;";
}
