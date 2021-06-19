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
@Table(name = "processing", indexes = {
        @Index(name = "id_index", columnList = "id"),
        @Index(name = "upgrade_index", columnList = "upgrade_id")
})
public class ItemAction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "item_name")
    protected String itemName;

    @Column(name = "result_name")
    protected String resultName;

    @Column(name = "in_amount")
    protected Integer inAmount;

    @Column(name = "out_amount")
    protected Integer outAmount;

    @ManyToOne
    @JoinColumn(name = "upgrade_id")
    protected Upgrade upgrade;

    protected Boolean isDeleted;
}
