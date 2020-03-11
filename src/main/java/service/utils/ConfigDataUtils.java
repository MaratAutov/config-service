package service.utils;

import core.intf.IDataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConfigDataUtils {
    @Value("${db.user.schema}")
    private String schemaName;

    @Qualifier("${db.name}")
    private final IDataUtils dataUtils;

    @Autowired
    public ConfigDataUtils(IDataUtils dataUtils) {
        this.dataUtils = dataUtils;
    }
}
