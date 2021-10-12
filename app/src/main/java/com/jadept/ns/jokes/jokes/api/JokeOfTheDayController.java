package com.jadept.ns.jokes.jokes.api;

import com.jadept.ns.jokes.jokes.dto.Joke;
import com.jadept.ns.jokes.jokes.service.JokeOfTheDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;
import java.util.Optional;

@RestController
public class JokeOfTheDayController {
    @Autowired
    private JokeOfTheDayService jokeOfTheDayService;

    @GetMapping("/jokes/")
    public ResponseEntity<Joke> retrieveJoke() {
        Optional<com.jadept.ns.jokes.jokes.connectors.Joke> optionalJoke = jokeOfTheDayService.retrieveJokeOfTheDay();

        if(optionalJoke.isPresent()) {
            return ResponseEntity.ok(Joke.builder().id(optionalJoke.get().getId()).randomJoke(optionalJoke.get().getJoke()).build());
        } else{
            return ResponseEntity.notFound().build();
        }
    }
}
