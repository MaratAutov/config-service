package configmanagement.services;

import configmanagement.domain.Subscriber;
import configmanagement.mapper.Mapper;
import configmanagement.model.SubscriberRecord;
import configmanagement.model.utils.DataUtils;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
/**
 * Базовый сервис
 */
public abstract class AbstractBaseService {
    private final DataUtils dataUtils;
    private final Mapper mapper;

}
