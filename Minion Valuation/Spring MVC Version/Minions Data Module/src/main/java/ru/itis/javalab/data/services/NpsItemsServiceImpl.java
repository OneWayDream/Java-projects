package ru.itis.javalab.data.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalab.data.dto.NpsItemDto;
import ru.itis.javalab.data.exceptions.EntityNotExistsException;
import ru.itis.javalab.data.exceptions.EntityNotFoundException;
import ru.itis.javalab.data.models.NpsItem;
import ru.itis.javalab.data.repositories.MinionNpsPricesRepository;

import java.util.List;

@Service
public class NpsItemsServiceImpl implements NpsItemsService {

    @Autowired
    protected MinionNpsPricesRepository npsPricesRepository;

    @Override
    public List<NpsItemDto> findAll() {
        return NpsItemDto.from(npsPricesRepository.findAllByIsDeletedIsNull());
    }

    @Override
    public NpsItemDto add(NpsItemDto itemDto) {
        NpsItem newItem = NpsItem.builder()
                .name(itemDto.getName())
                .sellPrice(itemDto.getSellPrice())
                .build();
        npsPricesRepository.save(newItem);
        return NpsItemDto.from(newItem);
    }

    @Override
    public NpsItemDto findById(Long id) {
        return NpsItemDto.from(npsPricesRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    @Override
    public void delete(NpsItemDto npsItemDto) {
        NpsItem itemForDelete = npsPricesRepository.findByName(npsItemDto.getName())
                .filter(item -> item.getIsDeleted()==null)
                .orElseThrow(() -> new EntityNotExistsException("Here is no nps item with name "
                        + npsItemDto.getName()));
        itemForDelete.setIsDeleted(true);
        npsPricesRepository.save(itemForDelete);
    }

    @Override
    public NpsItemDto update(NpsItemDto itemDto) {
        NpsItem itemForUpdate = npsPricesRepository.findByName(itemDto.getName())
                .filter(item -> item.getIsDeleted()==null)
                .orElseThrow(() -> new EntityNotExistsException("Here is no nps item with name "
                        + itemDto.getName()));
        npsPricesRepository.save(NpsItemDto.to(itemDto, itemForUpdate));
        return NpsItemDto.from(itemForUpdate);
    }

    @Override
    public NpsItemDto findItemByName(String itemName) {
        return NpsItemDto.from(npsPricesRepository.findByName(itemName)
                .filter(item -> item.getIsDeleted()==null)
                .orElseThrow(EntityNotFoundException::new));
    }

}
