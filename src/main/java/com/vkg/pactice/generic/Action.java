package com.vkg.pactice.generic;

import java.util.List;

public interface Action {
    <T extends Action> List<T> other();
    String getStatus();
}
