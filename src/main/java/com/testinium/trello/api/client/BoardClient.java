package com.testinium.trello.api.client;

import com.testinium.trello.api.models.Board;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;

import java.util.HashMap;
import java.util.Map;

@Log4j2
public class BoardClient extends BaseClient {
    private static final String BOARDS_ENDPOINT = "/boards";
    
    public Board createBoard(String name, String description) {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", name);
        requestBody.put("desc", description);
        
        log.info("Creating board with name: {}", name);
        Response response = post(BOARDS_ENDPOINT, requestBody);
        response.then().statusCode(200);
        
        Board board = response.as(Board.class);
        log.info("Board created with ID: {}", board.getId());
        return board;
    }
    
    public Board getBoard(String boardId) {
        log.info("Getting board with ID: {}", boardId);
        Response response = get(BOARDS_ENDPOINT + "/" + boardId);
        response.then().statusCode(200);
        
        return response.as(Board.class);
    }
    
    public void deleteBoard(String boardId) {
        log.info("Deleting board with ID: {}", boardId);
        Response response = delete(BOARDS_ENDPOINT + "/" + boardId);
        response.then().statusCode(200);
        
        log.info("Board deleted successfully");
    }
    
    public String getDefaultListId(String boardId) {
        log.info("Getting default lists for board ID: {}", boardId);
        Response response = get(BOARDS_ENDPOINT + "/" + boardId + "/lists");
        response.then().statusCode(200);
        
        // Get the ID of the first list (usually "To Do" in Trello)
        String listId = response.jsonPath().getString("[0].id");
        log.info("Default list ID: {}", listId);
        return listId;
    }
} 