package com.testinium.trello.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Card {
    private String id;
    private String name;
    private String desc;
    private String idBoard;
    private String idList;
    private String url;
    private String shortUrl;
    private boolean closed;
} 