package com.vkg.pactice.smc.ml;

import java.util.List;

public class Switch implements MappingLogic {
    List<Case> cases;
    MappingLogic defaultOutput;
    @Override
    public void validate(DataType expectedType) {
        for (Case c: cases) {
            c.validate(expectedType);
        }
        defaultOutput.validate(expectedType);
    }
}
