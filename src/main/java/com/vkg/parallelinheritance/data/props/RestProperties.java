package com.vkg.parallelinheritance.data.props;

import com.vkg.parallelinheritance.data.SourceProperties;

/**
 * Created by Vishnu on 9/7/2020.
 */
public class RestProperties implements SourceProperties {
    private final String url;


    public RestProperties(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
