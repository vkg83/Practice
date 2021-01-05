package com.vkg.parallelinheritance.data;

import java.util.Collection;

public interface DataProvider<P> {
    Collection<Data> fetchData(P properties);
}
