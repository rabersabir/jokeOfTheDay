package com.jadept.ns.jokes.jokes.api;

import com.jadept.ns.jokes.jokes.dto.Joke;
import com.jadept.ns.jokes.jokes.service.JokeOfTheDayService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class JokeOfTheDayController {

    private final JokeOfTheDayService jokeOfTheDayService;

    @GetMapping("/jokes/")
    public ResponseEntity<Joke> retrieveJoke() {
        Optional<com.jadept.ns.jokes.jokes.connectors.Joke> optionalJoke = jokeOfTheDayService.retrieveJokeOfTheDay();
        return optionalJoke.map(joke -> ResponseEntity.ok(Joke.builder().
                id(joke.getId()).randomJoke(joke.getJoke()).build())).
                orElseGet(() -> ResponseEntity.notFound().build());
    }
}
