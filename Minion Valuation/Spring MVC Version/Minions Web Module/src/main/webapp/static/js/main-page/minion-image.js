function minion_image(){

    $(".minion-choose").bind('change focus', function(){
        let variableX = $(this).val();
        if (variableX!=null){
            $(".minion-image").attr('src', current_path.getAttribute('current_path').split(';jsessionid=')[0] + "/static/images/minions_pose/" + variableX + "_Pose.png");

        }
    });

};