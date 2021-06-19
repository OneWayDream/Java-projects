package ru.itis.javalab.data.services;

import ru.itis.javalab.data.dto.NpsItemDto;

public interface NpsItemsService extends CrudService<NpsItemDto, Long> {

    NpsItemDto findItemByName(String itemName);

}
