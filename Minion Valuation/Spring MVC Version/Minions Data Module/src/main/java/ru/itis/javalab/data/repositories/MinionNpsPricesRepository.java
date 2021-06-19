package ru.itis.javalab.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.javalab.data.models.NpsItem;

import java.util.List;
import java.util.Optional;

public interface MinionNpsPricesRepository extends JpaRepository<NpsItem, Long> {

    List<NpsItem> findAllByIsDeletedIsNull();

    Optional<NpsItem> findByName(String name);

}
