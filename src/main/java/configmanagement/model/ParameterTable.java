package configmanagement.model;

import configmanagement.domain.Parameter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.jooq.Constraint;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.CustomTable;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;

import static org.jooq.impl.DSL.name;

public class ParameterTable extends CustomTable<ParameterRecord> {
    public static final ParameterTable INSTANCE = new ParameterTable();
    public static final String PK_NAME = "PK_PARAMETERS";

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

    @Override
    public UniqueKey<ParameterRecord> getPrimaryKey() {
        return new UniqueKey<ParameterRecord>() {
            @Override
            public List<ForeignKey<?, ParameterRecord>> getReferences() {
                return new ArrayList<>();
            }

            @Override
            public boolean isPrimary() {
                return true;
            }

            @Override
            public String getName() {
                return PK_NAME;
            }

            @Override
            public Table<ParameterRecord> getTable() {
                return INSTANCE;
            }

            @Override
            public List<TableField<ParameterRecord, ?>> getFields() {
                return Collections.singletonList(INSTANCE.ID);
            }

            @Override
            public TableField<ParameterRecord, ?>[] getFieldsArray() {
                return new TableField[]{INSTANCE.ID};
            }

            @Override
            public Constraint constraint() {
                return DSL.constraint(PK_NAME);
            }
        };
    }

    @Override
    public Identity<ParameterRecord, Integer> getIdentity() {
        return new Identity<ParameterRecord, Integer>() {
            @Override
            public Table<ParameterRecord> getTable() {
                return INSTANCE;
            }

            @Override
            public TableField<ParameterRecord, Integer> getField() {
                return INSTANCE.ID;
            }
        };
    }
}
