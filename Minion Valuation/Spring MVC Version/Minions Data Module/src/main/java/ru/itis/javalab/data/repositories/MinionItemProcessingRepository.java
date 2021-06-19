package ru.itis.javalab.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.javalab.data.models.ItemAction;
import ru.itis.javalab.data.models.Upgrade;

import java.util.List;

public interface MinionItemProcessingRepository extends JpaRepository<ItemAction, Long> {

    List<ItemAction> findAllByIsDeletedIsNull();

    List<ItemAction> findAllByIsDeletedIsNullAndUpgrade(Upgrade upgrade);

}
