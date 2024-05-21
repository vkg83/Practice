package com.vkg.pactice.smc;

public interface TokenCollector {
    void error(int position);

    void name(String name, int position);

    void logicEnd(int position);

    void keyword(String keyword, String name, int position);
}
