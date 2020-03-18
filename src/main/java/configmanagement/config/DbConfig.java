package configmanagement.config;

import static org.jooq.impl.DSL.name;

import com.zaxxer.hikari.HikariDataSource;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.stream.Stream;
import javax.sql.DataSource;
import org.jooq.ConnectionProvider;
import org.jooq.Field;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.RecordContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultDSLContext;
import org.jooq.impl.DefaultRecordListener;
import org.jooq.impl.DefaultRecordListenerProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DbConfig {
    @Value("${db.driver.name}")
    private String driverClassName;
    @Value("${db.url}")
    private String jdbcUrl;
    @Value("${db.user.name}")
    private String userName;
    @Value("${db.user.password}")
    private String password;
    @Value("${hibernate.dialect}")
    private String hibernateDialect;
    @Value("${db.user.schema}")
    private String dbSchema;

    @Bean
    public DataSourceProperties dataSourceProperties() throws Exception {
        DataSourceProperties properties = new DataSourceProperties();
        properties.setUsername(userName);
        properties.setSchema(Collections.singletonList(dbSchema));
        properties.setPassword(password);
        properties.setName("pool-config-service");
        properties.setDriverClassName(driverClassName);
        properties.afterPropertiesSet();
        return properties;
    }

    @Bean(name = "transactionManager")
    public TransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public TransactionAwareDataSourceProxy transactionAwareDataSource(DataSource dataSource) {
        return new TransactionAwareDataSourceProxy(dataSource);
    }

    @Bean
    public DataSourceConnectionProvider connectionProvider(TransactionAwareDataSourceProxy transactionAwareDataSource) {
        return new DataSourceConnectionProvider(transactionAwareDataSource);
    }

    @Bean(name = "dslContext")
    public DefaultDSLContext dslContext(ConnectionProvider connectionProvider) {
        final DefaultDSLContext dsl = new DefaultDSLContext(connectionProvider, SQLDialect.POSTGRES);
        dsl.configuration().set(new DefaultRecordListenerProvider(new AuditRecordListener()));
        dsl.configuration().settings().setReturnIdentityOnUpdatableRecord(Boolean.TRUE);
        dsl.configuration().settings().setReturnAllOnUpdatableRecord(Boolean.TRUE);
        return dsl;
    }

    /**
     * Служебный класс, обновляющий поля аудита
     */
    @SuppressWarnings("unchecked")
    private static class AuditRecordListener extends DefaultRecordListener {
        @Override
        public void insertStart(RecordContext ctx) {
            final Record record = ctx.record();
            Stream.of("CREATE_DATE", "UPDATE_DATE")
                    .forEach(s -> updateField(record, s));
        }

        @Override
        public void updateStart(RecordContext ctx) {
            final Record record = ctx.record();
            updateField(record, "UPDATE_DATE");
        }

        private void updateField(Record record, String s) {
            Name name = name(s);
            int i = record.indexOf(name);
            if (i != -1) {
                record.set((Field<Timestamp>) record.field(i),
                        new java.sql.Timestamp(System.currentTimeMillis()));
            }
        }
    }
}
