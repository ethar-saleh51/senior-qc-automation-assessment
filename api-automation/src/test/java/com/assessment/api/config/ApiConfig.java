package com.assessment.api.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ApiConfig {
    private ApiConfig() {
    }

    public static String getBaseUrl() {
        Properties properties = new Properties();
        try (InputStream input = ApiConfig.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new IllegalStateException("config.properties was not found on the test classpath");
            }
            properties.load(input);
        } catch (IOException exception) {
            throw new IllegalStateException("Could not load config.properties", exception);
        }

        String baseUrl = System.getProperty("base.url", properties.getProperty("base.url"));
        if (baseUrl == null || baseUrl.isBlank()) {
            throw new IllegalStateException("base.url must be configured and must not be blank");
        }
        return baseUrl.trim();
    }
}
