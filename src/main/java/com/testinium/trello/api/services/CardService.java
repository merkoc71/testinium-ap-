package com.testinium.trello.api.services;

import com.testinium.trello.api.client.CardClient;
import com.testinium.trello.api.models.Card;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.Random;

@Log4j2
public class CardService {
    private final CardClient cardClient;
    private final Random random;
    
    public CardService() {
        this.cardClient = new CardClient();
        this.random = new Random();
    }
    
    public Card createCard(String name, String description, String listId) {
        log.info("Creating a new card with name: {} on list: {}", name, listId);
        return cardClient.createCard(name, description, listId);
    }
    
    public Card getCard(String cardId) {
        log.info("Retrieving card with ID: {}", cardId);
        return cardClient.getCard(cardId);
    }
    
    public Card updateCard(String cardId, String newName, String newDescription) {
        log.info("Updating card with ID: {}", cardId);
        return cardClient.updateCard(cardId, newName, newDescription);
    }
    
    public void deleteCard(String cardId) {
        log.info("Deleting card with ID: {}", cardId);
        cardClient.deleteCard(cardId);
    }
    
    public Card selectRandomCardFromList(List<Card> cards) {
        if (cards == null || cards.isEmpty()) {
            throw new IllegalArgumentException("Card list cannot be null or empty");
        }
        
        int randomIndex = random.nextInt(cards.size());
        Card selectedCard = cards.get(randomIndex);
        log.info("Randomly selected card with ID: {}", selectedCard.getId());
        
        return selectedCard;
    }
} 