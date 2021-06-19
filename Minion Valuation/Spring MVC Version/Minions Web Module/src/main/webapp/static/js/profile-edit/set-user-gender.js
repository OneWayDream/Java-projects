function set_user_gender() {
    let gender = $('#user_data').attr("gender");
    $('#' + gender).attr('selected', 'selected');

}