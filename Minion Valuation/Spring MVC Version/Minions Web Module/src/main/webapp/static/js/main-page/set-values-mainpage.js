function set_values_mainpage() {
    let minion = select_values.getAttribute("minion_value");
    let tier = select_values.getAttribute("tier_value");
    let fuel = select_values.getAttribute("fuel_value");
    let upgrade1 = select_values.getAttribute("upgrade1_value");
    let upgrade2 = select_values.getAttribute("upgrade2_value");


    if (minion!==null){
        document.getElementById('minion').value = minion;
        document.getElementById('minion').dispatchEvent(new Event('change'));
    }
    if (tier!==null){
        document.getElementById('tier').getElementsByTagName('option')[tier].selected = 'selected';
    }
    if (fuel!==null){
        document.getElementById('fuel').value = fuel;
        document.getElementById('fuel').dispatchEvent(new Event('change'));
    }
    if (upgrade1!==null){
        document.getElementById('upgrade1').value = upgrade1;
        document.getElementById('upgrade1').dispatchEvent(new Event('change'));
    }
    if (upgrade2!==null){
        document.getElementById('upgrade2').value = upgrade2;
        document.getElementById('upgrade2').dispatchEvent(new Event('change'));
    }
};