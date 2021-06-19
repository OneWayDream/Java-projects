function fuel_image(){

    $(".fuel-choose").bind('change focus', function(){
        let variableX = $(this).val();
        $(".fuel-image").attr('src', current_path.getAttribute('current_path').split(';jsessionid=')[0] + "/static/images/fuels/" + variableX + ".png");
    });

};