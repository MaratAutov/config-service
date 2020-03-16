package configmanagement.mapper;

import configmanagement.domain.Subscriber;
import configmanagement.model.SubscriberRecord;
import configmanagement.model.SubscriberTable;
import javax.annotation.PostConstruct;
import org.jooq.UpdatableRecord;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    private final ModelMapper mapper;

    @Autowired
    public Mapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @PostConstruct
    private void init() {
        mapper.createTypeMap(Subscriber.class, SubscriberRecord.class)
                .addMappings(m -> {
                    m.using(ctx -> ctx.getSource() == null ? 0 : ctx.getSource())
                            .map(Subscriber::getVersion, SubscriberRecord::setVersion);
                    m.skip(SubscriberRecord::setObsolete);
                })
                .setProvider(provisionRequest -> SubscriberTable.INSTANCE.newRecord())
                .setPostConverter(
                        ctx -> {
                            ctx.getDestination().setObsolete(Boolean.FALSE);
                            return ctx.getDestination();
                        });
        mapper.createTypeMap(SubscriberRecord.class, Subscriber.class);
    }

    public <U extends UpdatableRecord, D> D toDomain(U descriptor, Class<D> domainClass) {
        return descriptor == null ? null : mapper.map(descriptor, domainClass);
    }

    public <U extends UpdatableRecord, D> U toModel(D descriptor, Class<U> modelClass) {
        return descriptor == null ? null : mapper.map(descriptor, modelClass);
    }
}
