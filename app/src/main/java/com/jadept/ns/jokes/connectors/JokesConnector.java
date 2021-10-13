package com.jadept.ns.jokes.connectors;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@RequiredArgsConstructor
public class JokesConnector {

    @Value("${jokes.api.url}")
    private String jokesResourceUrl;

    public List<Joke> fetchJokes(Integer amount, String type) {
        RestTemplate restTemplate= new RestTemplate();
        ResponseEntity<JokeResponse> response
                = restTemplate.getForEntity(jokesResourceUrl+"?"+getParams(amount, type), JokeResponse.class);
        if (response.getStatusCode() != HttpStatus.OK ||  response.getBody().isError()) {
            throw new IllegalStateException("Something went wrong:) No jokes");
        }
        return response.getBody().getJokes();
    }

    private  String getParams(Integer amount, String type){
        return  "type="+type+"&amount="+amount;
    }
}
