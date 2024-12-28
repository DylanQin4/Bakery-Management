/** @type {import('tailwindcss').Config} */

module.exports = {
    content: [
        "../main/resources/templates/**/*.{html,js}",
        "../main/resources/static/**/*.{html,js}",
        "./node_modules/flowbite/**/*.js",
        // "./src/**/*.html",
        // "./src/**/*.js",
        // "../src/main/resources/templates/**/*.{html,jsp}",
        // "../src/main/resources/static/js/**/*.{js,ts}"
    ],
    theme: {
        extend: {},
    },
    plugins: [
        require('flowbite/plugin')
    ]
}
