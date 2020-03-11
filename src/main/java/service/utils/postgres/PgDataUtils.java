package service.utils.postgres;

import core.intf.IDataUtils;
import org.jooq.CreateTableColumnStep;
import org.jooq.DSLContext;
import org.jooq.impl.SQLDataType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import static org.jooq.impl.DSL.exists;
import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.name;
import static org.jooq.impl.DSL.table;
import static org.jooq.impl.DSL.upper;

@Component("postgresql")
public class PgDataUtils implements IDataUtils {
    @Qualifier("postgresDSL")
    private final DSLContext dslContext;

    @Autowired
    public PgDataUtils(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public boolean checkIfTableExists(String schemaName, String tableName) {

        Object result = dslContext.select(
                field(exists(
                        dslContext.selectOne()
                                .from(table("pg_tables"))
                                .where(upper(field(name("schemaname"), String.class))
                                        .eq(schemaName.toUpperCase())
                                        .and(upper(field(name("tablename"), String.class))
                                                .eq(tableName.toUpperCase())))

                )))
                .fetchAny(0);
        return Boolean.TRUE.equals(result);
    }

    @Override
    public void createTable(String schemaName, String tableName) {
        CreateTableColumnStep createTableStmt = dslContext.createTable(tableName)
                .column("ID", SQLDataType.INTEGER.identity(true))
                .column("OID", SQLDataType.VARCHAR(512))
                .column("VERSION", SQLDataType.BIGINT.defaultValue(0L))
                .column("CREATE_DATE", SQLDataType.DATE.nullable(false))
                .column("CREATE_USER_ID", SQLDataType.BIGINT)
                .column("UPDATE_DATE", SQLDataType.DATE.nullable(false))
                .column("UPDATE_USER_ID", SQLDataType.BIGINT)
                .column("OBSOLETE", SQLDataType.BOOLEAN)
                .column("TEXT_REPRESENTATION", SQLDataType.VARCHAR(512))
                .column("PARENT_ID", SQLDataType.BIGINT)
                .column("IS_GROUP", SQLDataType.BOOLEAN);

        createTableStmt.execute();
    }

}
