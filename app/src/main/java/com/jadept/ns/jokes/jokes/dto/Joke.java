package com.jadept.ns.jokes.jokes.dto;

import lombok.Builder;
import lombok.Getter;



@Builder
@Getter
public class Joke {

    private Long id;
    private String randomJoke;

}
