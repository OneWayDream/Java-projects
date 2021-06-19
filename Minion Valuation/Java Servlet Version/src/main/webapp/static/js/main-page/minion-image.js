function minion_image(){

    $(".minion-choose").bind('change focus', function(){
        let variableX = $(this).val();
        $(".minion-image").attr('src', current_path.getAttribute('current_path') + "/static/images/minions_pose/" + variableX + "_Pose.png");
    });

};