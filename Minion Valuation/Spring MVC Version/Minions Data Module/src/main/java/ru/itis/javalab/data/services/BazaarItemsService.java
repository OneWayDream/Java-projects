package ru.itis.javalab.data.services;

import ru.itis.javalab.data.dto.BazaarItemDto;


public interface BazaarItemsService extends CrudService<BazaarItemDto, Long> {

    BazaarItemDto findItemByName(String itemName);

}
