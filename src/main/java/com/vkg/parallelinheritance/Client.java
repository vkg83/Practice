package com.vkg.parallelinheritance;

import com.vkg.parallelinheritance.data.DataProvider;
import com.vkg.parallelinheritance.data.SourceProperties;
import com.vkg.parallelinheritance.data.props.OracleProperties;
import com.vkg.parallelinheritance.data.props.RestProperties;
import com.vkg.parallelinheritance.data.providers.OracleProvider;
import com.vkg.parallelinheritance.data.providers.RestProvider;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vishnu on 9/7/2020.
 */
public class Client {
    public static void main(String[] args) {
        Map<Class<? extends SourceProperties>, DataProvider<? extends SourceProperties>> providers = new HashMap<>();
        providers.put(RestProperties.class, new RestProvider());
        providers.put(OracleProperties.class, new OracleProvider());
    }
}
