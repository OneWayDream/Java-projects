function calculate_ajax() {
    $('#calculate').click( function () {
            let minion = $('#minion').val();
            let tier = $('#tier').val();
            let fuel = $('#fuel').val();
            if (fuel==='SELECT'){
                fuel='';
            }
            let firstUpgrade = $('#upgrade1').val();
            if (firstUpgrade==='SELECT'){
                firstUpgrade='';
            }
            let secondUpgrade = $('#upgrade2').val();
            if (secondUpgrade==='SELECT'){
                secondUpgrade='';
            }
            let errorPanel = $('#errorPanel');
            let warningPanel = $('#warningPanel');
            let resultPanel = $('#resultPanel');

            $.ajax({
                contentType: "application/json; charset=UTF-8",
                type: "POST",
                // url: "http://localhost:8081/minion-data/calculate",
                url: "https://fierce-reef-89213-data.herokuapp.com/calculate",
                data: JSON.stringify({
                    "minionName":minion,
                    "minionTier":tier,
                    "fuelName":fuel,
                    "firstUpgradeName":firstUpgrade,
                    "secondUpgradeName":secondUpgrade
                }),
                success: function (data) {
                    if (data['isSuccess']===true){
                        // errorPanel.css('display', null);
                        errorPanel.hide();
                        if (data['warningMessage']!=null){
                            warningPanel.css('display', 'block');
                            warningPanel.html(getWarningLocalization(data['warningMessage']));
                        }
                        resultPanel.css('display', 'block');
                        resultPanel.html(getResultLocalization(data['result']));
                    } else {
                        // resultPanel.css('display', null);
                        // warningPanel.css('display', null);
                        resultPanel.hide();
                        warningPanel.hide();
                        errorPanel.css('display', 'block');
                        errorPanel.html(getErrorLocalization(data['errorMessage']));
                    }
                },
                error: function () {
                    // resultPanel.css('display', null);
                    // warningPanel.css('display', null);
                    resultPanel.hide();
                    warningPanel.hide();
                    errorPanel.css('display', 'block');
                    errorPanel.html(getUnexpectedErrorMessage());
                }
            })
        }
    );
}

function getUnexpectedErrorMessage() {
    let userLocale = getCookie('localeInfo');
    if (userLocale==='ru'){
        return 'Что-то пошло не так'
    } else {
        return 'Something went wrong'
    }
}

function getResultLocalization(result) {
    let userLocale = getCookie('localeInfo');
    if (userLocale==='ru'){
        return 'Результат: ' + parseInt(result) + ' монет в день';
    } else {
        return "Result: " +  parseInt(result) + " coins per 24 hours.";
    }
}

function getWarningLocalization(message) {
    let userLocale = getCookie('localeInfo');
    if (userLocale==='ru'){
        return 'Данная конфигурация улучшений не оптимальна: одно из них работать не будет';
    } else {
        return "This configuration of improvements is not optimal: one of them will not work";
    }
}

function getErrorLocalization(message) {
    let userLocale = getCookie('localeInfo');
    switch (message) {
        case "No minion":
            if (userLocale==='ru'){
                return 'Выбор миньона обязателен!';
            } else {
                return "Choosing a minion is a must!";
            }
        case "Bad first upgrade":
            if (userLocale==='ru'){
                return 'Улучшение ' + getReadableString($('#upgrade1').val()) + ' не может быть использовано для этого миньона';
            } else {
                return 'Improvement ' + getReadableString($('#upgrade1').val()) + ' cannot be used for this minion';
            }
        case "Bad second upgrade":
            if (userLocale==='ru'){
                return 'Улучшение ' + getReadableString($('#upgrade2').val()) + ' не может быть использовано для этого миньона';
            } else {
                return 'Improvement ' + getReadableString($('#upgrade2').val()) + ' cannot be used for this minion';
            }
        case "Bad tier":
            if (userLocale==='ru'){
                return 'Выбран неверный уровень миньона';
            } else {
                return 'Wrong minion level selected';
            }
        default:
            if (userLocale==='ru'){
                return 'Что-то пошло не так'
            } else {
                return 'Something went wrong'
            }
    }
}

function getReadableString(str) {
    if (str.includes("_")){
        result = str.split('_').map(str => str.toLowerCase()).join(' ');
    } else {
        result = str.toLowerCase();
    }
    return result.replace(new RegExp(result.charAt(0) + ""), (result.charAt(0) + "").toUpperCase()).trim();
}