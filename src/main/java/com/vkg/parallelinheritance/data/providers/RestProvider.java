package com.vkg.parallelinheritance.data.providers;

import com.vkg.parallelinheritance.data.Data;
import com.vkg.parallelinheritance.data.DataProvider;
import com.vkg.parallelinheritance.data.props.RestProperties;

import java.util.Collection;

public class RestProvider implements DataProvider<RestProperties> {
    @Override
    public Collection<Data> fetchData(RestProperties properties) {
        return null;
    }
}
