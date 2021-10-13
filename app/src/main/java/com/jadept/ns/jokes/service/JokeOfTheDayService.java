package com.jadept.ns.jokes.service;

import com.jadept.ns.jokes.connectors.Joke;
import com.jadept.ns.jokes.connectors.JokesConnector;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JokeOfTheDayService {
    private final JokesConnector jokesConnector;
    private final ExplicitContextService explicitContextService;

    /**
     * retrieves a list of jokes from jokes connector . Filter inappropriate jokes then selects the shortest joke.
     *
     * @return the shortest joke or empty when not found
     */
    public Optional<Joke> retrieveJokeOfTheDay() {
        List<Joke> jokes = jokesConnector.fetchJokes(16, "single");
        List<Joke> appropriateJokes = jokes.stream().
                filter(explicitContextService::isJokeAppropriate).
                collect(Collectors.toList());
        return findShortestJoke(appropriateJokes);
    }

    private Optional<Joke> findShortestJoke(List<Joke> jokes) {
        if (CollectionUtils.isEmpty(jokes)) {
            return Optional.empty();
        }
        Joke smallestJoke = jokes.get(0);
        for (Joke joke : jokes) {
            if (joke.getJoke().length() < smallestJoke.getJoke().length()) {
                smallestJoke = joke;
            }
        }
        return Optional.of(smallestJoke);
    }
}
