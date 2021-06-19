function button_background(){
    let counter = 0;

    let buttonClass = "button-panel";
    let backgrounds = [];

        buttons = document.getElementsByClassName(buttonClass);
        for (let i = 0; i< buttons.length;i++){
            backgrounds[i]="url('" + buttons[i].dataset.image + "')";
        }


        for (let i=0; i<buttons.length; i++){
            buttons[i].onclick = function () {
                document.body.style.backgroundImage = backgrounds[i];
                document.getElementById('user_background').value = backgrounds[i];
                counter = i;
            };
        }

        document.getElementById("background-button-exchange").onclick = function() {
            counter = (counter+1)%10;
            document.body.style.backgroundImage = backgrounds[counter];
            document.getElementById('user_background').value = backgrounds[i];
        };
}