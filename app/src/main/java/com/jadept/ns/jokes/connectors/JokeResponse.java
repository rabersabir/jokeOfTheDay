package com.jadept.ns.jokes.connectors;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class JokeResponse {
    private boolean error;
    private Integer amount;
    private List<Joke> jokes;

}
