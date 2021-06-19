package ru.itis.javalab.data.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalab.data.dto.BazaarItemDto;
import ru.itis.javalab.data.exceptions.EntityNotExistsException;
import ru.itis.javalab.data.exceptions.EntityNotFoundException;
import ru.itis.javalab.data.models.BazaarItem;
import ru.itis.javalab.data.repositories.MinionBazaarPricesRepository;

import java.util.List;

@Service
public class BazaarItemsServiceImpl implements BazaarItemsService {

    @Autowired
    protected MinionBazaarPricesRepository bazaarPricesRepository;

    @Override
    public List<BazaarItemDto> findAll() {
        return BazaarItemDto.from(bazaarPricesRepository.findAllByIsDeletedIsNull());
    }

    @Override
    public BazaarItemDto add(BazaarItemDto itemDto) {
        BazaarItem newItem = BazaarItem.builder()
                .name(itemDto.getName())
                .sellPrice(itemDto.getSellPrice())
                .buyPrice(itemDto.getBuyPrice())
                .build();
        bazaarPricesRepository.save(newItem);
        return BazaarItemDto.from(newItem);
    }

    @Override
    public BazaarItemDto findById(Long id) {
        return BazaarItemDto.from(bazaarPricesRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    @Override
    public void delete(BazaarItemDto bazaarItemDto) {
        BazaarItem itemForDelete = bazaarPricesRepository.findByName(bazaarItemDto.getName())
                .filter(item -> item.getIsDeleted()==null)
                .orElseThrow(() -> new EntityNotExistsException("Here is no bazaar item with name "
                        + bazaarItemDto.getName()));
        itemForDelete.setIsDeleted(true);
        bazaarPricesRepository.save(itemForDelete);
    }

    @Override
    public BazaarItemDto update(BazaarItemDto itemDto) {
        BazaarItem itemForUpdate = bazaarPricesRepository.findByName(itemDto.getName())
                .filter(item -> item.getIsDeleted()==null)
                .orElseThrow(() -> new EntityNotExistsException("Here is no bazaar item with name "
                        + itemDto.getName()));
        bazaarPricesRepository.save(BazaarItemDto.to(itemDto, itemForUpdate));
        return BazaarItemDto.from(itemForUpdate);
    }

    @Override
    public BazaarItemDto findItemByName(String itemName) {
        return BazaarItemDto.from(bazaarPricesRepository.findByName(itemName)
                .filter(item -> item.getIsDeleted()==null)
                .orElseThrow(EntityNotFoundException::new));
    }

}
