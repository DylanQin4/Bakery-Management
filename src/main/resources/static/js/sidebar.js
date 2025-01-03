document.addEventListener("DOMContentLoaded", function () {
    const currentPath = window.location.pathname;
    const sidebarLinks = document.querySelectorAll("#logo-sidebar a");

    sidebarLinks.forEach(link => {
        if (link.getAttribute("href") === currentPath) {
            link.classList.add("bg-gray-200", "dark:bg-gray-700");
        }
    });

    const dropdownLinks = document.querySelectorAll("#dropdown-example a");
    dropdownLinks.forEach(link => {
        if (currentPath.startsWith('/ressources')) {
            document.getElementById('dropdown-example').classList.add("block");
            document.getElementById('dropdown-example').classList.remove("hidden");
        }
    });
});
