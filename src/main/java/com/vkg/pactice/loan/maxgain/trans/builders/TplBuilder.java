package com.vkg.pactice.loan.maxgain.trans.builders;

import com.vkg.pactice.loan.maxgain.trans.tpl.TransactionTemplate;

public interface TplBuilder {
    TplBuilder onEach(int day);
    TransactionTemplate build();
}
