package com.testinium.trello.api.tests;

import com.testinium.trello.api.models.Board;
import com.testinium.trello.api.models.Card;
import com.testinium.trello.api.services.BoardService;
import com.testinium.trello.api.services.CardService;
import com.testinium.trello.api.utils.TestDataGenerator;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

@Log4j2
public class TrelloBoardTest {

    private final BoardService boardService = new BoardService();
    private final CardService cardService = new CardService();

    @Test
    public void trelloApiFlowTest() {
        log.info("Starting Trello API flow test");
        
        // Step 1: Create a new board
        String boardName = TestDataGenerator.generateUniqueBoardName();
        String boardDescription = TestDataGenerator.generateBoardDescription();
        
        log.info("Step 1: Creating a new board: {}", boardName);
        Board createdBoard = boardService.createBoard(boardName, boardDescription);
        Assert.assertNotNull(createdBoard.getId(), "Board ID should not be null");
        Assert.assertEquals(createdBoard.getName(), boardName, "Board name should match");
        
        try {
            // Get the default list ID from the created board
            String defaultListId = boardService.getDefaultListId(createdBoard.getId());
            Assert.assertNotNull(defaultListId, "Default list ID should not be null");
            
            // Step 2: Create two cards on the board
            log.info("Step 2: Creating two cards on the board");
            List<Card> createdCards = new ArrayList<>();
            
            String cardName1 = TestDataGenerator.generateUniqueCardName();
            String cardDescription1 = TestDataGenerator.generateCardDescription();
            Card card1 = cardService.createCard(cardName1, cardDescription1, defaultListId);
            Assert.assertNotNull(card1.getId(), "Card 1 ID should not be null");
            createdCards.add(card1);
            
            String cardName2 = TestDataGenerator.generateUniqueCardName();
            String cardDescription2 = TestDataGenerator.generateCardDescription();
            Card card2 = cardService.createCard(cardName2, cardDescription2, defaultListId);
            Assert.assertNotNull(card2.getId(), "Card 2 ID should not be null");
            createdCards.add(card2);
            
            // Step 3: Update a random card from the two created
            log.info("Step 3: Updating a random card from the created cards");
            Card cardToUpdate = cardService.selectRandomCardFromList(createdCards);
            
            String updatedCardName = TestDataGenerator.generateUpdatedCardName(cardToUpdate.getName());
            String updatedCardDescription = TestDataGenerator.generateUpdatedCardDescription(cardToUpdate.getDesc());
            
            Card updatedCard = cardService.updateCard(cardToUpdate.getId(), updatedCardName, updatedCardDescription);
            Assert.assertEquals(updatedCard.getName(), updatedCardName, "Updated card name should match");
            Assert.assertEquals(updatedCard.getDesc(), updatedCardDescription, "Updated card description should match");
            
            // Step 4: Delete all created cards
            log.info("Step 4: Deleting all created cards");
            for (Card card : createdCards) {
                cardService.deleteCard(card.getId());
            }
        } finally {
            // Step 5: Delete the created board (cleanup)
            log.info("Step 5: Deleting the created board");
            boardService.deleteBoard(createdBoard.getId());
        }
        
        log.info("Trello API flow test completed successfully");
    }
} 