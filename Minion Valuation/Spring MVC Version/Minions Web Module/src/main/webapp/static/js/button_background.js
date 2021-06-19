function button_background(){
    let counter = 0;

    let buttonClass = "button-panel";
    let backgrounds = [];
    let buttonExchangeId = 'background-button-exchange';
    let cookieAge = 60*60*24*365;

    let buttons = document.getElementsByClassName(buttonClass);

    for (let i = 0; i< buttons.length;i++){
        backgrounds[i]="url('" + buttons[i].dataset.image + "')";
    }


    for (let i=0; i<buttons.length; i++){
        buttons[i].onclick = function () {
            document.body.style.backgroundImage = backgrounds[i];
            setCookie('Background', backgrounds[i], {'max-age': cookieAge});
            counter = i;
        };
    }

    $('#' + buttonExchangeId).on('click', function () {
        counter = (counter+1)%buttons.length;
        document.body.style.backgroundImage = backgrounds[counter];
        setCookie('Background', backgrounds[counter], {'max-age': cookieAge});
    });
}