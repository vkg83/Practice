package com.vkg.parallelinheritance.data.providers;

import com.vkg.parallelinheritance.data.Data;
import com.vkg.parallelinheritance.data.DataProvider;
import com.vkg.parallelinheritance.data.props.OracleProperties;
import com.vkg.parallelinheritance.data.props.RestProperties;

import java.util.Collection;

public class OracleProvider implements DataProvider<OracleProperties> {
    @Override
    public Collection<Data> fetchData(OracleProperties properties) {
        return null;
    }
}
