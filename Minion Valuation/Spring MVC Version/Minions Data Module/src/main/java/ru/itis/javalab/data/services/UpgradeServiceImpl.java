package ru.itis.javalab.data.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalab.data.dto.UpgradeDto;
import ru.itis.javalab.data.exceptions.EntityNotExistsException;
import ru.itis.javalab.data.exceptions.EntityNotFoundException;
import ru.itis.javalab.data.models.Upgrade;
import ru.itis.javalab.data.repositories.MinionUpgradesRepository;

import java.util.List;

@Service
public class UpgradeServiceImpl implements UpgradeService {

    @Autowired
    protected MinionUpgradesRepository upgradesRepository;

    @Override
    public List<UpgradeDto> findAll() {
        return UpgradeDto.from(upgradesRepository.findAllByIsDeletedIsNull());
    }

    @Override
    public UpgradeDto add(UpgradeDto upgradeDto) {
        Upgrade newUpgrade = Upgrade.builder()
                .name(upgradeDto.getName())
                .type(upgradeDto.getType())
                .order(upgradeDto.getOrder())
                .extraItemName(upgradeDto.getExtraItemName())
                .extraItemChance(upgradeDto.getExtraItemChance())
                .extraItemAmount(upgradeDto.getExtraItemAmount())
                .isUniversal(upgradeDto.getIsUniversal())
                .build();
        upgradesRepository.save(newUpgrade);
        return UpgradeDto.from(newUpgrade);
    }

    @Override
    public UpgradeDto findById(Long id) {
        return UpgradeDto.from(upgradesRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    @Override
    public void delete(UpgradeDto upgradeDto) {
        Upgrade upgradeForDelete = upgradesRepository.findByName(upgradeDto.getName())
                .filter(upgrade -> upgrade.getIsDeleted()==null)
                .orElseThrow(() -> new EntityNotExistsException("Here is no upgrade with name "
                        + upgradeDto.getName()));
        upgradeForDelete.setIsDeleted(true);
        upgradesRepository.save(upgradeForDelete);
    }

    @Override
    public UpgradeDto update(UpgradeDto upgradeDto) {
        Upgrade upgradeForUpdate = upgradesRepository.findByName(upgradeDto.getName())
                .filter(upgrade -> upgrade.getIsDeleted()==null)
                .orElseThrow(() -> new EntityNotExistsException("Here is no upgrade with name "
                        + upgradeDto.getName()));
        upgradesRepository.save(UpgradeDto.to(upgradeDto, upgradeForUpdate));
        return UpgradeDto.from(upgradeForUpdate);
    }

    @Override
    public UpgradeDto findUpgradeByName(String upgradeName) {
        return UpgradeDto.from(upgradesRepository.findByName(upgradeName)
                .filter(upgrade -> upgrade.getIsDeleted()==null)
                .orElseThrow(EntityNotFoundException::new));
    }
}
