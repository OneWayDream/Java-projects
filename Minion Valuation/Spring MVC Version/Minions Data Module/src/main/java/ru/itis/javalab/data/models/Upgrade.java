package ru.itis.javalab.data.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "upgrades", indexes = {
        @Index(name = "id_index", columnList = "id"),
        @Index(name = "name_index", columnList = "name")
})
public class Upgrade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(unique = true)
    protected String name;

    @Enumerated(value = EnumType.STRING)
    protected Type type;

    @Column(name = "execution_order")
    protected Integer order;

    @Column(name = "extra_item_name")
    protected String extraItemName;

    @Column(name = "extra_item_chance")
    protected Integer extraItemChance;

    @Column(name = "extra_item_amount")
    protected Integer extraItemAmount;

    @Column(name = "speed_boost")
    protected Integer speedBoost;

    protected Boolean isDeleted;

    public enum Type{
        SPEED_BOOST, EXTRA_ITEM, PROCESSING
    }

    @Column(name = "is_universal")
    protected Boolean isUniversal;

}
