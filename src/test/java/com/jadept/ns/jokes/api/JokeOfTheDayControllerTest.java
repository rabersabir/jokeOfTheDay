package com.jadept.ns.jokes.api;

import com.jadept.ns.jokes.connectors.Joke;
import com.jadept.ns.jokes.service.JokeOfTheDayService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class JokeOfTheDayControllerTest {

    @Mock
    private JokeOfTheDayService jokeOfTheDayService;

    @InjectMocks
    private JokeOfTheDayController jokeOfTheDayController;

    @Test
    public void retrieveJokes() {

        Joke joke = new Joke();
        joke.setJoke("That is a joke");
        joke.setId(123L);
        when(jokeOfTheDayService.retrieveJokeOfTheDay()).thenReturn(Optional.of(joke));
        ResponseEntity<com.jadept.ns.jokes.dto.Joke> response = jokeOfTheDayController.retrieveJoke();
        assertThat(response.getBody().getRandomJoke(), is("That is a joke"));
        assertThat(response.getBody().getId(), is(123L));
        assertThat(response.getStatusCode(), is(HttpStatus.OK));

    }

    @Test
    public void retrieveJokes_notJokes() {
        when(jokeOfTheDayService.retrieveJokeOfTheDay()).thenReturn(Optional.empty());
        ResponseEntity<com.jadept.ns.jokes.dto.Joke> response = jokeOfTheDayController.retrieveJoke();
        assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
    }
}
