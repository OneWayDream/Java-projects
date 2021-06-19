package ru.itis.persistence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.persistence.annotations.*;

@Entity
@Table(name = "generated_table")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account {

    @Id
    protected Long id;

    @Column(name = "name", unique = true)
    protected String name;

    protected Integer money;

    @Transient
    protected short transientField;

}
