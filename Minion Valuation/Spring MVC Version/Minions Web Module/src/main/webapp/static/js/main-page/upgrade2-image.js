function upgrade2_image(){

    $(".upgrade2-choose").bind('change focus', function(){
        let variableX = $(this).val();
        $(".upgrade2-image").attr('src', current_path.getAttribute('current_path').split(';jsessionid=')[0] + "/static/images/upgrades/" + variableX + ".png");
    });

};