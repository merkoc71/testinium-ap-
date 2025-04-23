package com.testinium.trello.api.config;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Log4j2
public class ConfigManager {
    private static final Properties properties = new Properties();
    private static ConfigManager instance;

    private ConfigManager() {
        loadProperties();
    }

    public static ConfigManager getInstance() {
        if (instance == null) {
            instance = new ConfigManager();
        }
        return instance;
    }

    private void loadProperties() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (inputStream != null) {
                properties.load(inputStream);
                log.info("Configuration properties loaded successfully");
            } else {
                log.error("Unable to find config.properties file");
                throw new RuntimeException("Unable to find config.properties file");
            }
        } catch (IOException e) {
            log.error("Error loading properties: {}", e.getMessage(), e);
            throw new RuntimeException("Error loading properties", e);
        }
    }

    public String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            log.warn("Property '{}' not found in configuration", key);
        }
        return value;
    }

    public String getTrelloApiBaseUrl() {
        return getProperty("trello.api.baseUrl");
    }

    public String getTrelloApiKey() {
        return getProperty("trello.api.key");
    }

    public String getTrelloApiToken() {
        return getProperty("trello.api.token");
    }
} 