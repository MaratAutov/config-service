package configmanagement.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.jooq.Constraint;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.CustomTable;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;

import static java.util.Arrays.asList;
import static org.jooq.impl.DSL.name;

public class Parameter2SubscriptionTable extends CustomTable<Parameter2SubscriptionRecord> {
    public static final Parameter2SubscriptionTable INSTANCE = new Parameter2SubscriptionTable();
    public static final String PK_NAME = "PK_PARAMETERS_SUBSCRIPTIONS";
    public final TableField<Parameter2SubscriptionRecord, Integer> PARAMETER_ID = createField(name("PARAMETER_ID"),
            SQLDataType.INTEGER);
    public final TableField<Parameter2SubscriptionRecord, Integer> SUBSCRIPTION_ID = createField(
            name("SUBSCRIPTION_ID"),
            SQLDataType.INTEGER);

    protected Parameter2SubscriptionTable() {
        super(name("REF_PARAMETERS_SUBSCRIPTIONS"));
    }

    @Override
    public Class<? extends Parameter2SubscriptionRecord> getRecordType() {
        return Parameter2SubscriptionRecord.class;
    }

    @Override
    public UniqueKey<Parameter2SubscriptionRecord> getPrimaryKey() {
        return new UniqueKey<Parameter2SubscriptionRecord>() {
            @Override
            public List<ForeignKey<?, Parameter2SubscriptionRecord>> getReferences() {
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
            public Table<Parameter2SubscriptionRecord> getTable() {
                return INSTANCE;
            }

            @Override
            public List<TableField<Parameter2SubscriptionRecord, ?>> getFields() {
                return asList(INSTANCE.PARAMETER_ID, INSTANCE.SUBSCRIPTION_ID);
            }

            @Override
            public TableField<Parameter2SubscriptionRecord, ?>[] getFieldsArray() {
                return new TableField[]{INSTANCE.PARAMETER_ID, INSTANCE.SUBSCRIPTION_ID};
            }

            @Override
            public Constraint constraint() {
                return DSL.constraint(PK_NAME);
            }
        };
    }

    public Collection<? extends Field<?>> getColumns() {
        return asList(PARAMETER_ID, SUBSCRIPTION_ID);
    }
}
