package ru.itis.javalab.data.services;

import ru.itis.javalab.data.dto.ItemActionDto;
import ru.itis.javalab.data.dto.UpgradeDto;

import java.util.List;


public interface ItemActionsService extends CrudService<ItemActionDto, Long> {
    List<ItemActionDto> findAllByUpgrade(UpgradeDto upgradeDto);
}
