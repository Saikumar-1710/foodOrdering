const form = document.querySelector("form");

form.addEventListener("submit", function (e) {

    const username = document
        .getElementById("username")
        .value
        .trim();

    const password = document
        .getElementById("password")
        .value
        .trim();

    if (username === "" || password === "") {

        e.preventDefault();

        alert("Username and Password are required.");

    }

});

const forgotLink = document.querySelector('.forgot-link');
const forgotModal = document.getElementById('forgotModal');
const closeForgot = document.getElementById('closeForgot');
const forgotForm = document.getElementById('forgotForm');
const forgotMessage = document.getElementById('forgotMessage');

if (forgotLink) {
    forgotLink.addEventListener('click', function (e) {
        e.preventDefault();
        forgotModal.style.display = 'flex';
        forgotMessage.textContent = '';
    });
}

if (closeForgot) {
    closeForgot.addEventListener('click', function () {
        forgotModal.style.display = 'none';
    });
}

if (forgotModal) {
    forgotModal.addEventListener('click', function (e) {
        if (e.target === forgotModal) {
            forgotModal.style.display = 'none';
        }
    });
}

if (forgotForm) {
    forgotForm.addEventListener('submit', function (e) {
        e.preventDefault();
        forgotMessage.textContent = 'Password reset link sent successfully!';
        setTimeout(function () {
            forgotModal.style.display = 'none';
            forgotForm.reset();
            forgotMessage.textContent = '';
        }, 1800);
    });
}