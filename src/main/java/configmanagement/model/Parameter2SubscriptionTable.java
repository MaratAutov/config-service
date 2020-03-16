package configmanagement.model;

import org.jooq.TableField;
import org.jooq.impl.CustomTable;
import org.jooq.impl.SQLDataType;

import static org.jooq.impl.DSL.name;

public class Parameter2SubscriptionTable extends CustomTable<Parameter2SubscriptionRecord> {
    public static final Parameter2SubscriptionTable INSTANCE = new Parameter2SubscriptionTable();

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
}
