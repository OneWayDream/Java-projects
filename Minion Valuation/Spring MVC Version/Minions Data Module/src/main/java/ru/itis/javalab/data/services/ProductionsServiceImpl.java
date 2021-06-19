package ru.itis.javalab.data.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalab.data.dto.MinionDto;
import ru.itis.javalab.data.dto.NpsItemDto;
import ru.itis.javalab.data.dto.ProductionDto;
import ru.itis.javalab.data.exceptions.EntityNotExistsException;
import ru.itis.javalab.data.exceptions.EntityNotFoundException;
import ru.itis.javalab.data.models.Minion;
import ru.itis.javalab.data.models.NpsItem;
import ru.itis.javalab.data.models.Production;
import ru.itis.javalab.data.repositories.MinionNpsPricesRepository;
import ru.itis.javalab.data.repositories.MinionProductionsRepository;
import ru.itis.javalab.data.repositories.MinionsRepository;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class ProductionsServiceImpl implements ProductionsService {

    @Autowired
    protected MinionProductionsRepository productionsRepository;
    @Autowired
    protected MinionsRepository minionsRepository;

    @Override
    public List<ProductionDto> findAll() {
        return ProductionDto.from(productionsRepository.findAllByIsDeletedIsNull());
    }

    @Override
    public ProductionDto add(ProductionDto productionDto) {
        Production newProduction = Production.builder()
                .itemName(productionDto.getItemName())
                .minion(minionsRepository.findByName(productionDto.getMinionName())
                        .orElseThrow(() -> new EntityNotExistsException("Here is no minion with name "
                                + productionDto.getMinionName())))
                .amount(productionDto.getAmount())
                .chance(productionDto.getChance())
                .build();
        productionsRepository.save(newProduction);
        return ProductionDto.from(newProduction);
    }

    @Override
    public void delete(ProductionDto productionDto) {
        Production productionForDelete = productionsRepository.findById(productionDto.getId())
                .filter(item -> item.getIsDeleted()==null)
                .orElseThrow(() -> new EntityNotExistsException("Here is no production with id "
                        + productionDto.getId()));
        productionForDelete.setIsDeleted(true);
        productionsRepository.save(productionForDelete);
    }

    @Override
    public ProductionDto update(ProductionDto productionDto) {
        Production productionForUpdate = productionsRepository.findById(productionDto.getId())
                .filter(production -> production.getIsDeleted()==null)
                .orElseThrow(() -> new EntityNotExistsException("Here is no fuel with id "
                        + productionDto.getId()));
        productionForUpdate.setItemName(productionDto.getItemName());
        productionForUpdate.setMinion(minionsRepository.findByName(productionDto.getMinionName())
                .orElseThrow(() -> new EntityNotExistsException("Here is no minion with name "
                        + productionDto.getMinionName())));
        productionForUpdate.setChance(productionDto.getChance());
        productionForUpdate.setAmount(productionDto.getAmount());
        productionsRepository.save(productionForUpdate);
        return ProductionDto.from(productionForUpdate);
    }

    @Override
    public ProductionDto findById(Long id) {
        return ProductionDto.from(productionsRepository.findById(id)
                .filter(production -> production.getIsDeleted()==null)
                .orElseThrow(EntityNotFoundException::new));
    }

    @Override
    public List<ProductionDto> findAllByMinion(MinionDto minionDto) {
        Minion minion = minionsRepository.findByName(minionDto.getName())
                .orElseThrow(() -> new EntityNotExistsException("Here is no minion with name "
                        + minionDto.getName()));
        return ProductionDto.from(productionsRepository.findAllByMinionAndIsDeletedIsNull(minion));
    }
}
