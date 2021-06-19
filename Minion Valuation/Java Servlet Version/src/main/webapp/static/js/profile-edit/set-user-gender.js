function set_user_gender() {
    let gender = select_values.getAttribute("gender");
    let index;
    if (gender==="Male"){
        index=0;
    } else if (gender==="Female"){
        index=1;
    } else {
        index=2;
    }
    document.getElementById('new_gender').getElementsByTagName("option")[index].selected="selected";

}