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
@Table(name = "item_nps_price", indexes = {
        @Index(name = "id_index", columnList = "id"),
        @Index(name = "name_index", columnList = "item_name")
})
public class NpsItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "item_name", unique = true)
    protected String name;

    @Column(name = "price")
    protected Double sellPrice;

    protected Boolean isDeleted;

}
