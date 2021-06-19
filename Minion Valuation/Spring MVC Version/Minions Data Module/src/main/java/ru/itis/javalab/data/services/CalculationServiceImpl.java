package ru.itis.javalab.data.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalab.data.dto.*;
import ru.itis.javalab.data.utils.MinionsProfitCalculator;

@Service
public class CalculationServiceImpl implements CalculationService {

    @Autowired
    protected MinionsService minionsService;

    @Autowired
    protected FuelService fuelService;

    @Autowired
    protected UpgradeService upgradeService;

    @Autowired
    protected MinionsProfitCalculator minionsProfitCalculator;

    @Override
    public CalculationResult handleCalculationForm(CalculateForm calculateForm) {
        MinionDto minionDto = null;
        if ((calculateForm.getMinionName()!=null)&&(!calculateForm.getMinionName().equals(""))){
            minionDto = minionsService.findMinionByName(calculateForm.getMinionName());
        }
        FuelDto fuelDto = null;
        if ((calculateForm.getFuelName()!=null)&&(!calculateForm.getFuelName().equals(""))){
            fuelDto = fuelService.findFuelByName(calculateForm.getFuelName());
        }
        UpgradeDto firstUpgrade = null;
        if ((calculateForm.getFirstUpgradeName()!=null)&&(!calculateForm.getFirstUpgradeName().equals(""))){
            firstUpgrade = upgradeService.findUpgradeByName(calculateForm.getFirstUpgradeName());
        }
        UpgradeDto secondUpgrade = null;
        if ((calculateForm.getSecondUpgradeName()!=null)&&(!calculateForm.getSecondUpgradeName().equals(""))){
            secondUpgrade = upgradeService.findUpgradeByName(calculateForm.getSecondUpgradeName());
        }

        return minionsProfitCalculator.calculate(
                minionDto,
                calculateForm.getMinionTier(),
                fuelDto,
                firstUpgrade,
                secondUpgrade,
                false
        );
    }
}
