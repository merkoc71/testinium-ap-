package com.testinium.trello.api.services;

import com.testinium.trello.api.client.BoardClient;
import com.testinium.trello.api.models.Board;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class BoardService {
    private final BoardClient boardClient;
    
    public BoardService() {
        this.boardClient = new BoardClient();
    }
    
    public Board createBoard(String name, String description) {
        log.info("Creating a new board with name: {}", name);
        return boardClient.createBoard(name, description);
    }
    
    public Board getBoard(String boardId) {
        log.info("Retrieving board with ID: {}", boardId);
        return boardClient.getBoard(boardId);
    }
    
    public void deleteBoard(String boardId) {
        log.info("Deleting board with ID: {}", boardId);
        boardClient.deleteBoard(boardId);
    }
    
    public String getDefaultListId(String boardId) {
        log.info("Getting default list ID for board: {}", boardId);
        return boardClient.getDefaultListId(boardId);
    }
} 