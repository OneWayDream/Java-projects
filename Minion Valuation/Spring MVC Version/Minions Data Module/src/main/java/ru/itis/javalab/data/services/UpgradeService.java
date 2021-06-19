package ru.itis.javalab.data.services;

import ru.itis.javalab.data.dto.UpgradeDto;

public interface UpgradeService extends CrudService<UpgradeDto, Long> {

    UpgradeDto findUpgradeByName(String upgradeName);

}
