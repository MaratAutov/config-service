package configmanagement.services;

import configmanagement.domain.Parameter;
import configmanagement.domain.Subscription;
import configmanagement.mapper.Mapper;
import configmanagement.model.Parameter2SubscriptionRecord;
import configmanagement.model.Parameter2SubscriptionTable;
import configmanagement.model.ParameterRecord;
import configmanagement.model.ParameterTable;
import configmanagement.model.SubscriptionTable;
import configmanagement.model.utils.DataUtils;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.NonNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.simpleflatmapper.jdbc.JdbcMapper;
import org.simpleflatmapper.jdbc.JdbcMapperFactory;
import org.simpleflatmapper.tuple.Tuple2;
import org.simpleflatmapper.util.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParameterService extends AbstractBaseService {

    private static final Logger log = LogManager.getLogger(ParameterService.class);

    @Autowired
    public ParameterService(DataUtils dataUtils, Mapper mapper) {
        super(dataUtils, mapper);
    }

    /**
     * Добавить данные параметра
     *
     * @param parameter Параметр
     * @return добавленные данные
     */
    public Parameter addParameter(@NonNull Parameter parameter) {
        if (parameter.getId() != null) {
            throw new IllegalArgumentException("The 'parameter' has been stored already. Use updateParameter method.");
        }

        final ParameterRecord record = getMapper().toModel(parameter, ParameterRecord.class);
        return getMapper().toDomain(getDataUtils().store(record), Parameter.class);
    }

    /**
     * Получить список всех параметров
     *
     * @return
     */
    public List<Parameter> getParameters() {

        JdbcMapper<Tuple2<ParameterRecord, List<Subscription>>> jdbcMapper =
                JdbcMapperFactory
                        .newInstance()
                        .addKeys("id")
                        .newMapper(new TypeReference<Tuple2<ParameterRecord, List<Subscription>>>() {
                        });

        try (ResultSet rs = getDataUtils()
                .getDslContext()
                .select()
                .from(ParameterTable.INSTANCE)
                .leftJoin(Parameter2SubscriptionTable.INSTANCE)
                .on(Parameter2SubscriptionTable.INSTANCE.PARAMETER_ID.eq(ParameterTable.INSTANCE.ID))
                .leftJoin(SubscriptionTable.INSTANCE)
                .on(SubscriptionTable.INSTANCE.ID.eq(Parameter2SubscriptionTable.INSTANCE.SUBSCRIPTION_ID))
                .fetchResultSet()
        ) {
            Stream<Tuple2<ParameterRecord, List<Subscription>>> stream = jdbcMapper.stream(rs);
            List<Parameter> parameters =
                    stream.map(tuple2 -> {
                        final Parameter parameter = getMapper().toDomain(tuple2.first(), Parameter.class);
                        tuple2.second().forEach(e -> parameter.getSubscriptions().add(e));
                        return parameter;
                    })
                            .collect(Collectors.toList());
            return parameters;
        } catch (SQLException e) {
            log.error("Exception has occured during execute sql.", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Получить параметр по идентификатору
     *
     * @param id идентификатор
     * @return найденный параметр
     */
    public Parameter getParameterById(@NonNull Integer id) {
        ParameterRecord record = getDataUtils().getRecordById(ParameterTable.INSTANCE, ParameterTable.INSTANCE.ID, id);
        return getMapper().toDomain(record, Parameter.class);
    }

    /**
     * Обновить данные параметра
     *
     * @param parameter Параметр
     * @return обновленные данные
     */
    public Parameter updateParameter(@NonNull Parameter parameter) {
        if (parameter.getId() == null) {
            throw new IllegalArgumentException("The 'parameter' has not been stored yet. Use addParameter method.");
        }

        final ParameterRecord record = getMapper().toModel(parameter, ParameterRecord.class);
        return getMapper().toDomain(getDataUtils().store(record), Parameter.class);
    }

    /**
     * Удалить параметр по идентификатору
     *
     * @param id идентификатор параметра
     * @return
     */
    public Boolean deleteParameterById(@NonNull Integer id) {
        return getDataUtils().deleteRecordById(ParameterTable.INSTANCE, ParameterTable.INSTANCE.ID, id);
    }

    /**
     * @param id
     * @param subscription
     */
    public void addSubscription2Parameter(@NonNull Integer id, @NonNull Subscription subscription) {
        if (!getDataUtils().isRecordExists(ParameterTable.INSTANCE, ParameterTable.INSTANCE.ID, id)) {
            throw new NullPointerException("The parameter by id (" + id + ") has not found.");
        }
        if (subscription.getId() == null) {
            throw new IllegalArgumentException("The 'subscription' has not been stored.");
        }

        Parameter2SubscriptionRecord record = Parameter2SubscriptionTable.INSTANCE.newRecord();
        record.setParameterId(id);
        record.setSubscriptionId(subscription.getId());
        getDataUtils().store(record);
    }
}
