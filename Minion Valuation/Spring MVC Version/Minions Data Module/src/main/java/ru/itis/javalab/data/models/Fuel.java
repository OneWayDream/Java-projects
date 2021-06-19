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
@Table(name = "fuels", indexes = {
        @Index(name = "id_index", columnList = "id"),
        @Index(name = "name_index", columnList = "name")
})
public class Fuel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "name", unique = true)
    protected String name;

    @Column(name = "speed_boost")
    protected Double speedBoost;

    @Enumerated(value = EnumType.STRING)
    protected Type type;

    protected Boolean isDeleted;

    public enum Type{
        PERCENT, MULTIPLY
    }

}
