package com.jadept.ns.jokes.jokes.service;

import com.jadept.ns.jokes.jokes.connectors.Joke;
import com.jadept.ns.jokes.jokes.connectors.JokesConnector;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JokeOfTheDayService {
    @Autowired
    private JokesConnector jokesConnector;
    @Autowired
    private ExplicitContextService explicitContextService;

    public Optional<Joke> retrieveJokeOfTheDay() {

        List<Joke> jokes = jokesConnector.retrieveJokes();

        List<Joke> appropriateJokes = jokes.stream().
                filter(joke -> explicitContextService.isJokeAppropriate(joke)).
                collect(Collectors.toList());

        return findShortestJoke(appropriateJokes);

    }




    private Optional<Joke> findShortestJoke(List<Joke> jokes){
        if (CollectionUtils.isEmpty(jokes)) {
            return Optional.empty();
        }
        Joke smallestJoke = jokes.get(0);
        for (Joke joke:jokes) {
            if (joke.getJoke().length() < smallestJoke.getJoke().length()) {
                smallestJoke = joke;
            }
        }
        return Optional.of(smallestJoke);
    }
}
