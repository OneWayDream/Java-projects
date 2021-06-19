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
@Table(name = "production", indexes = {
        @Index(name = "id_index", columnList = "id")
})
public class Production {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "item_name")
    protected String itemName;

    @ManyToOne
    @JoinColumn(name = "minion_id")
    protected Minion minion;

    protected Integer chance;

    protected Double amount;

    protected Boolean isDeleted;

}
