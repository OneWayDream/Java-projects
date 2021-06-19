function fuel_image(){

    $(".fuel-choose").bind('change focus', function(){
        let variableX = $(this).val();
        $(".fuel-image").attr('src', current_path.getAttribute('current_path') + "/static/images/fuels/" + variableX + ".png");
    });

};