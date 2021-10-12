package com.jadept.ns.jokes.jokes.connectors;

import com.jadept.ns.jokes.jokes.connectors.Joke;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class JokesConnector {

    @Value("${jokes.api.url}")
    private String jokesResourceUrl;

    public List<Joke> retrieveJokes() {

        RestTemplate restTemplate = new RestTemplate();
     ;
        ResponseEntity<JokeResponse> response
                = restTemplate.getForEntity(jokesResourceUrl, JokeResponse.class);
        if (response.getStatusCode() != HttpStatus.OK ||  response.getBody().isError()) {
            throw new IllegalStateException("444");
        }
        return response.getBody().getJokes();
    }

}
