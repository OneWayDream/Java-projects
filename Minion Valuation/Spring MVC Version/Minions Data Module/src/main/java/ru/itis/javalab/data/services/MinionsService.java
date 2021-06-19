package ru.itis.javalab.data.services;

import ru.itis.javalab.data.dto.MinionDto;

public interface MinionsService extends CrudService<MinionDto, Long> {

    MinionDto findMinionByName(String minionName);

}
