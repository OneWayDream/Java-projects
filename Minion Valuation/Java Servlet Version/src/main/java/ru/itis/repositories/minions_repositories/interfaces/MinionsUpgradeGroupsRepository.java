package ru.itis.repositories.minions_repositories.interfaces;

import javafx.util.Pair;
import ru.itis.repositories.CrudRepository;

import java.util.List;

public interface MinionsUpgradeGroupsRepository extends CrudRepository<Pair<String, String>> {



    List<String> getUpgradesMyMinionName(String minion_name);
}
