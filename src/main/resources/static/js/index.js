(function () {
  getCookie(`id`) && getCookie(`username`)
    ? (window.location.href = "./home.html")
    : (window.location.href = "./login.html");
})();
