package ru.itis.javalab.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itis.javalab.data.models.Minion;

import java.util.List;
import java.util.Optional;

public interface MinionsRepository extends JpaRepository<Minion, Long> {

    @Query(value = "SELECT minion FROM Minion minion JOIN FETCH minion.supportedUpgrades WHERE minion.isDeleted=NULL")
    List<Minion> findAllByIsDeletedIsNull();

//    @Query(nativeQuery =true, value =
//                "(SELECT minions.id, minions.name, minions.isdeleted, minions.times_between_actions," +
//                     "upgrades.id, upgrades.name, upgrades.type, upgrades.isdeleted, upgrades.is_universal," +
//                     "upgrades.execution_order, upgrades.extra_item_name, upgrades.extra_item_chance," +
//                     "upgrades.extra_item_amount " +
//                "FROM minions " +
//                     "JOIN minion_upgrade_group mug " +
//                        "ON minions.id = mug.minion_id " +
//                     "JOIN upgrades on mug.upgrade_id = upgrades.id " +
//                "WHERE minions.isdeleted IS NULL) " +
//            "UNION " +
//                "(SELECT minions.id, minions.name, minions.isdeleted, minions.times_between_actions, " +
//                     "upgrades.id, upgrades.name, upgrades.type, upgrades.isdeleted, upgrades.is_universal, " +
//                     "upgrades.execution_order, upgrades.extra_item_name, upgrades.extra_item_chance, " +
//                     "upgrades.extra_item_amount " +
//                "FROM minions " +
//                    "JOIN upgrades ON upgrades.is_universal is true AND minions.isdeleted IS NULL);")
//    List<Minion> customFindAll();

    @Query(value = "SELECT minion FROM Minion minion JOIN FETCH minion.supportedUpgrades WHERE minion.name=:name")
    Optional<Minion> findByName(@Param("name") String name);

}
