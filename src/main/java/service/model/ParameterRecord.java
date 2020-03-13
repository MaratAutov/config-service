package service.model;

import org.jooq.impl.CustomRecord;

public class ParameterRecord extends CustomRecord<ParameterRecord> {
    protected ParameterRecord() {
        super(ParameterTable.PARAMETERS);
    }
}