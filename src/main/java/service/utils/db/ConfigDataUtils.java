package service.utils.db;

import org.jooq.CreateTableColumnStep;
import org.jooq.DSLContext;
import org.jooq.impl.DefaultDSLContext;
import org.jooq.impl.SQLDataType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static org.jooq.impl.DSL.exists;
import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.name;
import static org.jooq.impl.DSL.table;
import static org.jooq.impl.DSL.upper;

@Component
public class ConfigDataUtils {

    private final DSLContext dslContext;

    @Autowired
    public ConfigDataUtils(@Qualifier("default") DSLContext context) {
        this.dslContext = context;
    }

    @Transactional
    public void initTables() {
        // создать таблицу параметров
        CreateTableColumnStep createTableStmt = dslContext.createTableIfNotExists(name("PARAMETERS"))
                .column("ID", SQLDataType.INTEGER.identity(true))
                .column("VERSION", SQLDataType.BIGINT.defaultValue(1L))
                .column("CREATE_DATE", SQLDataType.TIMESTAMP.nullable(false))
                .column("UPDATE_DATE", SQLDataType.TIMESTAMP.nullable(false))
                .column("OBSOLETE", SQLDataType.BOOLEAN)
                .column("NAME", SQLDataType.VARCHAR(512))
                .column("DESCRIPTION", SQLDataType.VARCHAR(1024))
                .column("VALUE", SQLDataType.VARCHAR(1024));

        createTableStmt.execute();

        createTableStmt = dslContext.createTableIfNotExists(name("PARAMETERS"))
                .column("ID", SQLDataType.INTEGER.identity(true))
                .column("VERSION", SQLDataType.BIGINT.defaultValue(0L))
                .column("CREATE_DATE", SQLDataType.TIMESTAMP.nullable(false))
                .column("UPDATE_DATE", SQLDataType.TIMESTAMP.nullable(false))
                .column("OBSOLETE", SQLDataType.BOOLEAN)
                .column("NAME", SQLDataType.VARCHAR(512))
                .column("DESCRIPTION", SQLDataType.VARCHAR(1024));

        createTableStmt.execute();
    }

    private boolean checkIfTableExists(String schemaName, String tableName) {

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

}
