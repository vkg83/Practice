package com.vkg.pactice.smc.ml;

public class Case implements MappingLogic {
    MappingLogic condition;
    MappingLogic output;
    @Override
    public void validate(DataType expectedType) {
        condition.validate(DataType.BOOLEAN);
        output.validate(expectedType);
    }
}
