package com.jadept.ns.jokes.connectors;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;
 @Getter
 @Setter
public class Joke {
    private Long id;
    private boolean safe;
    private String lang;
    private String category;
    private String type;
    private String joke;
    private Map<String, Boolean> flags;

  }
