package ru.itis.javalab.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.javalab.data.models.BazaarItem;

import java.util.List;
import java.util.Optional;

public interface MinionBazaarPricesRepository extends JpaRepository<BazaarItem, Long> {

    List<BazaarItem> findAllByIsDeletedIsNull();

    Optional<BazaarItem> findByName(String name);
}
