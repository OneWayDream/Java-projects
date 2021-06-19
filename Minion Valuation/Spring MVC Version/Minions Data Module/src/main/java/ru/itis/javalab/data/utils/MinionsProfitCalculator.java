package ru.itis.javalab.data.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.javalab.data.dto.*;
import ru.itis.javalab.data.models.BazaarItem;
import ru.itis.javalab.data.models.Fuel;
import ru.itis.javalab.data.models.Upgrade;
import ru.itis.javalab.data.services.BazaarItemsService;
import ru.itis.javalab.data.services.ItemActionsService;
import ru.itis.javalab.data.services.NpsItemsService;
import ru.itis.javalab.data.services.ProductionsService;

import java.util.ArrayList;
import java.util.List;

@Component
public class MinionsProfitCalculator {

    @Autowired
    protected ProductionsService productionsService;

    @Autowired
    protected ItemActionsService itemActionsService;

    @Autowired
    protected BazaarItemsService bazaarItemsService;

    @Autowired
    protected NpsItemsService npsItemsService;

    public CalculationResult calculate(MinionDto minion, Integer tier, FuelDto fuel, UpgradeDto firstUpgrade, UpgradeDto secondUpgrade,
                                       Boolean onlyNps){
        //Check data
        if (minion==null){
            return CalculationResult.builder()
                    .isSuccess(false)
                    .errorMessage("No minion")
                    .build();
        }
        if ((firstUpgrade!=null)&&(!minion.getSupportedUpgrades().contains(firstUpgrade))){
            return CalculationResult.builder()
                    .isSuccess(false)
                    .errorMessage("Bad first upgrade")
                    .build();
        }
        if ((secondUpgrade != null)&&(!minion.getSupportedUpgrades().contains(secondUpgrade))){
            return CalculationResult.builder()
                    .isSuccess(false)
                    .errorMessage("Bad second upgrade")
                    .build();
        }
        if ((tier>12)||(tier<1)){
            return CalculationResult.builder()
                    .isSuccess(false)
                    .errorMessage("Bad tier")
                    .build();
        }

        //handle null entities
        if (fuel == null){
            fuel = FuelDto.builder().build();
        }
        if (firstUpgrade == null){
            firstUpgrade = UpgradeDto.builder().build();
        }
        if (secondUpgrade == null){
            secondUpgrade = UpgradeDto.builder().build();
        }

        //calculate speed boost
        int speedBoost = 0;
        if (Fuel.Type.PERCENT.equals(fuel.getType())){
            speedBoost += fuel.getSpeedBoost();
        }
        if (Upgrade.Type.SPEED_BOOST.equals(firstUpgrade.getType())){
            speedBoost+=firstUpgrade.getSpeedBoost();
        }
        if (Upgrade.Type.SPEED_BOOST.equals(secondUpgrade.getType())){
            speedBoost+=secondUpgrade.getSpeedBoost();
        }

        //calculate production amount
        double timeOfAction = (minion.getTimeBetweenActions().get(tier-1)) / (1 + (double) speedBoost / 100);
        double productAmount = (60 * 60 * 24) / timeOfAction;

        //use multiple boost
        if (Fuel.Type.MULTIPLY.equals(fuel.getType())){
            productAmount *= (fuel.getSpeedBoost()/100);
        }

        //get minion productions
        List<ProductionDto> productions = productionsService.findAllByMinion(minion);

        //create minion result list
        List<ItemAmount> minionProduction = new ArrayList<>();

        //count default productions
        for (ProductionDto production : productions) {
            minionProduction.add(new ItemAmount(production.getItemName(),
                    productAmount * production.getChance() * production.getAmount() / 100));
        }

        //use extra items upgrades
        if (Upgrade.Type.EXTRA_ITEM.equals(firstUpgrade.getType())){
            minionProduction.add(new ItemAmount(firstUpgrade.getExtraItemName(),
                            productAmount * firstUpgrade.getExtraItemChance() * firstUpgrade.getExtraItemAmount() / 100));
        }
        if (Upgrade.Type.EXTRA_ITEM.equals(secondUpgrade.getType())){
            minionProduction.add(new ItemAmount(secondUpgrade.getExtraItemName(),
                    productAmount * secondUpgrade.getExtraItemChance() * secondUpgrade.getExtraItemAmount() / 100));
        }

        //use processing upgrades
        if ((Upgrade.Type.PROCESSING.equals(firstUpgrade.getType()))&&(Upgrade.Type.PROCESSING.equals(secondUpgrade.getType()))){
            if (firstUpgrade.getOrder() > secondUpgrade.getOrder()){
                handleProcessingUpgrade(minionProduction, firstUpgrade);
                handleProcessingUpgrade(minionProduction, secondUpgrade);
            } else {
                handleProcessingUpgrade(minionProduction, secondUpgrade);
                handleProcessingUpgrade(minionProduction, firstUpgrade);
            }
        } else if (Upgrade.Type.PROCESSING.equals(firstUpgrade.getType())){
            handleProcessingUpgrade(minionProduction, firstUpgrade);
        } else if (Upgrade.Type.PROCESSING.equals(secondUpgrade.getType())){
            handleProcessingUpgrade(minionProduction, secondUpgrade);
        }

        //count profit
        List<BazaarItemDto> bazaarItems = bazaarItemsService.findAll();
        List<NpsItemDto> npsItems = npsItemsService.findAll();
        double profit = 0;
        for (ItemAmount itemAmount : minionProduction){
            BazaarItemDto bazaarItemDto = bazaarItems.stream()
                    .filter(itemDto -> itemDto.getName().equals(itemAmount.getName()))
                    .findAny()
                    .orElse(null);
            if ((bazaarItemDto == null)||(onlyNps)){
                NpsItemDto npsItemDto = npsItems.stream()
                        .filter(itemDto -> itemDto.getName().equals(itemAmount.getName()))
                        .findAny()
                        .orElse(null);
                if (npsItemDto != null){
                    profit += itemAmount.getAmount() * npsItemDto.getSellPrice();
                }
            } else {
                profit += itemAmount.getAmount() * bazaarItemDto.getSellPrice();
            }
        }

        CalculationResult calculationResult = CalculationResult.builder()
                .isSuccess(true)
                .result(profit)
                .build();
        if ((firstUpgrade.equals(secondUpgrade))
                &&((Upgrade.Type.PROCESSING.equals(firstUpgrade.getType()))
                    ||(Upgrade.Type.PROCESSING.equals(firstUpgrade.getType())))){
            calculationResult.setWarningMessage("Useless upgrades");
        }
        return calculationResult;
    }

    @Data
    @AllArgsConstructor
    protected class ItemAmount{
        protected String name;
        protected Double amount;
    }

    protected void handleProcessingUpgrade(List<ItemAmount> productions, UpgradeDto upgrade){
        List<ItemActionDto> itemActions = itemActionsService.findAllByUpgrade(upgrade);
        for (ItemAmount itemAmount : productions){
            ItemActionDto itemActionDto = itemActions.stream()
                    .filter(itemAction -> itemAction.getItemName().equals(itemAmount.getName()))
                    .findAny()
                    .orElse(null);
            if (itemActionDto != null){
                itemAmount.setName(itemActionDto.getResultName());
                itemAmount.setAmount(itemAmount.getAmount() * itemActionDto.getOutAmount()/itemActionDto.getInAmount());
            }
        }
    }

}
