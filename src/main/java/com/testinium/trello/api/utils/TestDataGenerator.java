package com.testinium.trello.api.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class TestDataGenerator {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    public static String generateUniqueBoardName() {
        return "Test Board " + getCurrentTimeStamp() + " " + getRandomSuffix();
    }
    
    public static String generateBoardDescription() {
        return "This is an automated test board created at " + getCurrentTimeStamp();
    }
    
    public static String generateUniqueCardName() {
        return "Test Card " + getCurrentTimeStamp() + " " + getRandomSuffix();
    }
    
    public static String generateCardDescription() {
        return "This is an automated test card created at " + getCurrentTimeStamp();
    }
    
    public static String generateUpdatedCardName(String originalName) {
        return "Updated: " + originalName;
    }
    
    public static String generateUpdatedCardDescription(String originalDescription) {
        return originalDescription + " - Updated at " + getCurrentTimeStamp();
    }
    
    private static String getCurrentTimeStamp() {
        LocalDateTime now = LocalDateTime.now();
        return now.format(formatter);
    }
    
    private static String getRandomSuffix() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
} 