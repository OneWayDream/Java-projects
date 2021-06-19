package ru.itis.persistence.core;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TableInfo {

    protected String tableName;
    protected String columnName;
    protected String dataType;

}
