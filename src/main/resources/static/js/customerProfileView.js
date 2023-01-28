"use strict";

let isUserEditingProfile = false;

function enableAllInputsInProfileForm(event) {

    event.preventDefault();

    if (isUserEditingProfile) return;

    const elements = document.querySelectorAll("#profile-form input");

    for (let i = 0, len = elements.length; i < len; ++i) {

        elements[i].disabled = false;
    }

    isUserEditingProfile = true;
}

function disableAllInputsInProfileForm(event) {

    event.preventDefault();

    if (!isUserEditingProfile) return;

    const elements = document.querySelectorAll("#profile-form input");

    for (let i = 0, len = elements.length; i < len; ++i) {

        elements[i].disabled = true;
    }

    isUserEditingProfile = false;
}

document.getElementById("edit-profile-btn")
    .addEventListener(
        "click",
        (event) => enableAllInputsInProfileForm(event));

document.getElementById("stop-edit-profile-btn")
    .addEventListener(
        "click",
        (event) => disableAllInputsInProfileForm(event));