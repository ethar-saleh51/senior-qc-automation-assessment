package com.seniorqcautomation.config;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

public class ConfigReader {
    private final Properties properties = new Properties();

    public ConfigReader() {
        try (InputStream input = ConfigReader.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new IllegalStateException("config.properties was not found on the classpath.");
            }
            properties.load(input);
        } catch (IOException exception) {
            throw new IllegalStateException("Could not load config.properties.", exception);
        }
    }

    public String getBaseUrl() {
        return getRequiredProperty("base.url");
    }

    public String getBrowser() {
        return getRequiredProperty("browser");
    }

    public Duration getExplicitWaitTimeout() {
        return Duration.ofSeconds(Long.parseLong(getRequiredProperty("explicit.wait.seconds")));
    }

    private String getRequiredProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null || value.isBlank()) {
            throw new IllegalStateException("Missing required configuration property: " + key);
        }
        return value.trim();
    }
}
