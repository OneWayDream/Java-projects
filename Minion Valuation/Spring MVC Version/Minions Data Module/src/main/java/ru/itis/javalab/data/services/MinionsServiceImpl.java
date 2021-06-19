package ru.itis.javalab.data.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalab.data.dto.MinionDto;
import ru.itis.javalab.data.dto.UpgradeDto;
import ru.itis.javalab.data.exceptions.EntityNotExistsException;
import ru.itis.javalab.data.exceptions.EntityNotFoundException;
import ru.itis.javalab.data.models.Minion;
import ru.itis.javalab.data.models.Upgrade;
import ru.itis.javalab.data.repositories.MinionUpgradesRepository;
import ru.itis.javalab.data.repositories.MinionsRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MinionsServiceImpl implements MinionsService {

    @Autowired
    protected MinionsRepository minionsRepository;

    @Autowired
    protected MinionUpgradesRepository upgradesRepository;

    @Override
    public List<MinionDto> findAll() {
        List<MinionDto> result = MinionDto.from(minionsRepository.findAllByIsDeletedIsNull());
        List<UpgradeDto> extra = UpgradeDto.from(upgradesRepository.findAllByIsUniversalIsTrue());
        for (MinionDto minionDto : result){
            minionDto.getSupportedUpgrades().addAll(extra);
        }
        return result;
    }

    @Override
    public MinionDto add(MinionDto minionDto) {
        Minion newMinion = Minion.builder()
                .name(minionDto.getName())
                .timeBetweenActions(minionDto.getTimeBetweenActions())
                .supportedUpgrades(minionDto.getSupportedUpgrades().stream()
                                  .map(upgradeDto -> upgradesRepository.findByName(upgradeDto.getName())
                                          .orElseThrow(() -> new EntityNotExistsException("Here is no upgrade with name "
                                          + upgradeDto.getName())))
                                  .collect(Collectors.toList()))
                .build();
        minionsRepository.save(newMinion);
        return MinionDto.from(newMinion);
    }

    @Override
    public MinionDto findById(Long id) {
        MinionDto result = MinionDto.from(minionsRepository.findById(id).orElseThrow(EntityNotFoundException::new));
        List<UpgradeDto> extra = UpgradeDto.from(upgradesRepository.findAllByIsUniversalIsTrue());
        result.getSupportedUpgrades().addAll(extra);
        return result;
    }

    @Override
    public void delete(MinionDto minionDto) {
        Minion minionForDelete = minionsRepository.findByName(minionDto.getName())
                .filter(minion -> minion.getIsDeleted()==null)
                .orElseThrow(() -> new EntityNotExistsException("Here is no minion with name "
                        + minionDto.getName()));
        minionForDelete.setIsDeleted(true);
        minionsRepository.save(minionForDelete);
    }

    @Override
    public MinionDto update(MinionDto minionDto) {
        Minion minionForUpdate = minionsRepository.findByName(minionDto.getName())
                .filter(minion -> minion.getIsDeleted()==null)
                .orElseThrow(() -> new EntityNotExistsException("Here is no minion with name "
                        + minionDto.getName()));

        minionForUpdate.setSupportedUpgrades(minionDto.getSupportedUpgrades().stream()
                .map(upgradeDto -> upgradesRepository.findByName(upgradeDto.getName())
                        .orElseThrow(() -> new EntityNotExistsException("Here is no upgrade with name "
                                + upgradeDto.getName())))
                .collect(Collectors.toList()));
        minionForUpdate.setTimeBetweenActions(minionDto.getTimeBetweenActions());

        minionsRepository.save(minionForUpdate);
        return MinionDto.from(minionForUpdate);
    }

    @Override
    public MinionDto findMinionByName(String minionName) {
        MinionDto result = MinionDto.from(minionsRepository.findByName(minionName)
                .filter(minion -> minion.getIsDeleted()==null)
                .orElseThrow(EntityNotFoundException::new));
        List<UpgradeDto> extra = UpgradeDto.from(upgradesRepository.findAllByIsUniversalIsTrue());
        result.getSupportedUpgrades().addAll(extra);
        return result;
    }
}
