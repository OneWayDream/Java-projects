function set_image() {
    let user_background = select_values.getAttribute("user_background");
    if (user_background!==''){
        document.body.style.backgroundImage = user_background;
    }
}