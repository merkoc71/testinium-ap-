package com.testinium.trello.api.client;

import com.testinium.trello.api.models.Card;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;

import java.util.HashMap;
import java.util.Map;

@Log4j2
public class CardClient extends BaseClient {
    private static final String CARDS_ENDPOINT = "/cards";
    
    public Card createCard(String name, String description, String listId) {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", name);
        requestBody.put("desc", description);
        requestBody.put("idList", listId);
        
        log.info("Creating card with name: {} on list: {}", name, listId);
        Response response = post(CARDS_ENDPOINT, requestBody);
        response.then().statusCode(200);
        
        Card card = response.as(Card.class);
        log.info("Card created with ID: {}", card.getId());
        return card;
    }
    
    public Card getCard(String cardId) {
        log.info("Getting card with ID: {}", cardId);
        Response response = get(CARDS_ENDPOINT + "/" + cardId);
        response.then().statusCode(200);
        
        return response.as(Card.class);
    }
    
    public Card updateCard(String cardId, String name, String description) {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", name);
        requestBody.put("desc", description);
        
        log.info("Updating card with ID: {}", cardId);
        Response response = put(CARDS_ENDPOINT + "/" + cardId, requestBody);
        response.then().statusCode(200);
        
        Card card = response.as(Card.class);
        log.info("Card updated successfully");
        return card;
    }
    
    public void deleteCard(String cardId) {
        log.info("Deleting card with ID: {}", cardId);
        Response response = delete(CARDS_ENDPOINT + "/" + cardId);
        response.then().statusCode(200);
        
        log.info("Card deleted successfully");
    }
} 