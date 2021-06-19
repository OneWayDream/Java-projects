package ru.itis.javalab.data.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import ru.itis.javalab.data.converters.TimeBetweenActionsConverter;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "minions", indexes = {
        @Index(name = "id_index", columnList = "id"),
        @Index(name = "name_index", columnList = "name")
})
public class Minion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "name", unique = true)
    protected String name;

    @Column(name = "times_between_actions")
    @Convert(converter = TimeBetweenActionsConverter.class)
    protected List<Double> timeBetweenActions;

    @ManyToMany(cascade = {CascadeType.ALL}
//    , fetch = FetchType.EAGER
    )
    //@Fetch(FetchMode.JOIN)
    @JoinTable(
            name = "minion_upgrade_group",
            joinColumns = {@JoinColumn(name = "minion_id")},
            inverseJoinColumns = {@JoinColumn(name = "upgrade_id")}
    )
    @Column(name = "supported_upgrades")
    protected List<Upgrade> supportedUpgrades;

    protected Boolean isDeleted;

}
