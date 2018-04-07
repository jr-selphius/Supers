package com.marvel.jr.supers;

public class TestCustomApplication extends CustomApplication {

    private String baseUrl;

    @Override
    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String url) {
        baseUrl = url;
    }
}
