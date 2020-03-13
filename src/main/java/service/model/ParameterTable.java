package service.model;

import static org.jooq.impl.DSL.name;

import java.sql.Timestamp;
import org.jooq.TableField;
import org.jooq.impl.CustomTable;
import org.jooq.impl.SQLDataType;

public class ParameterTable extends CustomTable<ParameterRecord> {
    public static final ParameterTable PARAMETERS = new ParameterTable();

    public final TableField<ParameterRecord, Integer> ID = createField(name("ID"), SQLDataType.INTEGER.identity(true));
    public final TableField<ParameterRecord, Integer> VERSION = createField(name("VERSION"),
            SQLDataType.INTEGER.defaultValue(1));
    public final TableField<ParameterRecord, Timestamp> CREATE_DATE = createField(name("CREATE_DATE"),
            SQLDataType.TIMESTAMP.nullable(false));
    public final TableField<ParameterRecord, Timestamp> UPDATE_DATE = createField(name("UPDATE_DATE"),
            SQLDataType.TIMESTAMP.nullable(false));
    public final TableField<ParameterRecord, Boolean> OBSOLETE = createField(name("OBSOLETE"),
            SQLDataType.BOOLEAN);
    public final TableField<ParameterRecord, String> NAME = createField(name("NAME"),
            SQLDataType.VARCHAR(512));
    public final TableField<ParameterRecord, String> DESCRIPTION = createField(name("DESCRIPTION"),
            SQLDataType.VARCHAR(1024));
    public final TableField<ParameterRecord, String> VALUE = createField(name("VALUE"),
            SQLDataType.VARCHAR(1024));

    protected ParameterTable() {
        super(name("PARAMETERS"));
    }

    @Override
    public Class<? extends ParameterRecord> getRecordType() {
        return ParameterRecord.class;
    }
}
