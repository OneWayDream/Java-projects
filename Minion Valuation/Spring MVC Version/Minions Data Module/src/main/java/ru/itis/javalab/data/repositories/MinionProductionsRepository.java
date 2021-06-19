package ru.itis.javalab.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.javalab.data.models.Minion;
import ru.itis.javalab.data.models.Production;

import java.util.List;

public interface MinionProductionsRepository extends JpaRepository<Production, Long> {

    List<Production> findAllByIsDeletedIsNull();

    List<Production> findAllByMinionAndIsDeletedIsNull(Minion minion);

}
