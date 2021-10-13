package com.jadept.ns.jokes.service;

import com.jadept.ns.jokes.connectors.Joke;
import com.jadept.ns.jokes.connectors.JokesConnector;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class JokeOfTheDayServiceTest {
    @Mock
    private JokesConnector jokesConnector;
    @Mock
    private ExplicitContextService explicitContextService;

    @InjectMocks
    private JokeOfTheDayService jokeOfTheDayService;

    @Test
    public void happyFlow() {
        Joke joke = createJoke("this is a joke");
        Joke shortJoke = createJoke("short joke");

        when(jokesConnector.fetchJokes(16, "single")).thenReturn(Arrays.asList(joke, shortJoke));

        when(explicitContextService.isJokeAppropriate(joke)).thenReturn(true);
        when(explicitContextService.isJokeAppropriate(shortJoke)).thenReturn(true);

        Optional<Joke> result = jokeOfTheDayService.retrieveJokeOfTheDay();
        assertThat(result.get(), CoreMatchers.is(shortJoke));
    }



    @Test
    public void shorttestJoke() {
        Joke joke = createJoke("this is a joke");
        Joke shortJoke = createJoke("short joke");

        when(jokesConnector.fetchJokes(16, "single")).thenReturn(Arrays.asList(joke, shortJoke));

        when(explicitContextService.isJokeAppropriate(joke)).thenReturn(true);
        when(explicitContextService.isJokeAppropriate(shortJoke)).thenReturn(false);

        Optional<Joke> result = jokeOfTheDayService.retrieveJokeOfTheDay();
        assertThat(result.get(), CoreMatchers.is(joke));
    }


    private Joke createJoke(String theJoke) {
        Joke joke = new Joke();
        joke.setJoke(theJoke);
        return joke;
    }

}
