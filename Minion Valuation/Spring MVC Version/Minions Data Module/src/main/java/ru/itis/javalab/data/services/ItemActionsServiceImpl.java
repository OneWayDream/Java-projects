package ru.itis.javalab.data.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalab.data.dto.ItemActionDto;
import ru.itis.javalab.data.dto.UpgradeDto;
import ru.itis.javalab.data.exceptions.EntityNotExistsException;
import ru.itis.javalab.data.exceptions.EntityNotFoundException;
import ru.itis.javalab.data.models.ItemAction;
import ru.itis.javalab.data.models.Upgrade;
import ru.itis.javalab.data.repositories.MinionItemProcessingRepository;
import ru.itis.javalab.data.repositories.MinionUpgradesRepository;

import java.util.List;

@Service
public class ItemActionsServiceImpl implements ItemActionsService {

    @Autowired
    protected MinionItemProcessingRepository itemProcessingRepository;

    @Autowired
    protected MinionUpgradesRepository minionUpgradesRepository;

    @Override
    public List<ItemActionDto> findAll() {
        return ItemActionDto.from(itemProcessingRepository.findAllByIsDeletedIsNull());
    }

    @Override
    public ItemActionDto add(ItemActionDto itemActionDto) {
        ItemAction newItemAction = ItemAction.builder()
                .itemName(itemActionDto.getItemName())
                .resultName(itemActionDto.getResultName())
                .inAmount(itemActionDto.getInAmount())
                .outAmount(itemActionDto.getOutAmount())
                .upgrade(minionUpgradesRepository.findByName(itemActionDto.getUpgradeDto().getName())
                        .orElseThrow(() -> new EntityNotExistsException("Here is no upgrade with name "
                                + itemActionDto.getUpgradeDto().getName())))
                .build();
        itemProcessingRepository.save(newItemAction);
        return ItemActionDto.from(newItemAction);
    }

    @Override
    public void delete(ItemActionDto itemActionDto) {
        ItemAction itemForDelete = itemProcessingRepository.findById(itemActionDto.getId())
                .filter(item -> item.getIsDeleted()==null)
                .orElseThrow(() -> new EntityNotExistsException("Here is no item action with id "
                        + itemActionDto.getId()));
        itemForDelete.setIsDeleted(true);
        itemProcessingRepository.save(itemForDelete);
    }

    @Override
    public ItemActionDto update(ItemActionDto itemActionDto) {
        ItemAction itemActionForUpdate = itemProcessingRepository.findById(itemActionDto.getId())
                .filter(item -> item.getIsDeleted()==null)
                .orElseThrow(() -> new EntityNotExistsException("Here is no item action with id "
                        + itemActionDto.getId()));
        itemActionForUpdate.setItemName(itemActionDto.getItemName());
        itemActionForUpdate.setResultName(itemActionDto.getResultName());
        itemActionForUpdate.setInAmount(itemActionDto.getInAmount());
        itemActionForUpdate.setOutAmount(itemActionDto.getOutAmount());
        itemActionForUpdate.setUpgrade(minionUpgradesRepository.findByName(itemActionDto.getUpgradeDto().getName())
                .orElseThrow(() -> new EntityNotExistsException("Here is no upgrade with name "
                        + itemActionDto.getUpgradeDto().getName())));
        itemProcessingRepository.save(itemActionForUpdate);
        return ItemActionDto.from(itemActionForUpdate);
    }

    @Override
    public ItemActionDto findById(Long id) {
        return ItemActionDto.from(itemProcessingRepository.findById(id)
                .filter(item -> item.getIsDeleted()==null)
                .orElseThrow(EntityNotFoundException::new));
    }

    @Override
    public List<ItemActionDto> findAllByUpgrade(UpgradeDto upgradeDto) {
        Upgrade upgrade = minionUpgradesRepository.findByName(upgradeDto.getName())
                .orElseThrow(() -> new EntityNotExistsException("Here is no upgrade with name "
                        + upgradeDto.getName()));
        return ItemActionDto.from(itemProcessingRepository.findAllByIsDeletedIsNullAndUpgrade(upgrade));
    }
}
