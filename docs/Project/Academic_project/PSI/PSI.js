//console.log("Script load");

function openTab(tabId) {
    document.querySelectorAll(".tab-content").forEach(tab => {
        tab.classList.remove("active");
    });

    document.querySelectorAll(".tab-button").forEach(tab => {
        tab.classList.remove("active");
    });

    document.getElementById(tabId).classList.add("active");

    event.target.classList.add("active");
}

function msg_dev() {
    alert('Elément en cours de développement');
}