const dropdownMenu = document.getElementById("dropdown-menu");
const dropdownContent = document.getElementById("dropdown-content");

dropdownMenu.addEventListener('click', function() {
    const contentDisplay = getComputedStyle(dropdownContent, null).display;

    if(contentDisplay == "none") {
        dropdownContent.style.display = "block";
        dropdownMenu.style.backgroundColor = "rgba(255, 255, 255, 0.2)";
        return;
    }

    dropdownMenu.style.backgroundColor = "";
    dropdownContent.style.display = "none";
 });