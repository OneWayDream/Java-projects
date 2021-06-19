function upgrade1_image(){

    $(".upgrade1-choose").bind('change focus', function(){
        let variableX = $(this).val();
        $(".upgrade1-image").attr('src', current_path.getAttribute('current_path').split(';jsessionid=')[0] + "/static/images/upgrades/" + variableX + ".png");
    });

};