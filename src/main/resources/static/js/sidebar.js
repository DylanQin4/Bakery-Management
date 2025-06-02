document.addEventListener("DOMContentLoaded", function () {
    const currentPath = window.location.pathname;
    const sidebarLinks = document.querySelectorAll("#logo-sidebar a");

    sidebarLinks.forEach(link => {
        if (link.getAttribute("href") === currentPath) {
            link.classList.add("bg-gray-200", "dark:bg-gray-700");
        }
    });

    const dropdownLinks = document.querySelectorAll("#dropdown-ressources a");
    dropdownLinks.forEach(link => {
        if (currentPath.startsWith('/ressources')) {
            document.getElementById('dropdown-ressources').classList.add("block");
            document.getElementById('dropdown-ressources').classList.remove("hidden");
        }
    });

    const dropdownTiersLinks = document.querySelectorAll("#dropdown-tiers a");
    dropdownTiersLinks.forEach(link => {
        if (currentPath.startsWith('/tiers')) {
            document.getElementById('dropdown-tiers').classList.add("block");
            document.getElementById('dropdown-tiers').classList.remove("hidden");
        }
    });
});
