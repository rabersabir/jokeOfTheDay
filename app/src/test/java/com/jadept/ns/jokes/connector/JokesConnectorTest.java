package com.jadept.ns.jokes.connector;

import com.jadept.ns.jokes.connectors.Joke;
import com.jadept.ns.jokes.connectors.JokeResponse;
import com.jadept.ns.jokes.connectors.JokesConnector;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.platform.commons.util.ReflectionUtils;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class JokesConnectorTest {

    @InjectMocks
    private JokesConnector jokesConnector;

    @Mock
    private RestTemplate restTemplate;
    private String jokesResourceUrl = "http://localhost/";


    @Test
    public void fetchJokes() {
        ReflectionTestUtils.setField(jokesConnector, "jokesResourceUrl", jokesResourceUrl);
        JokeResponse jokeResponse = new JokeResponse();
        jokeResponse.setError(false);
        Joke joke = new Joke();
        jokeResponse.setJokes(Arrays.asList(joke));

        ResponseEntity<JokeResponse> response = ResponseEntity.ok(jokeResponse);
        when(restTemplate.getForEntity(jokesResourceUrl + "any?type=single&amount=16", JokeResponse.class)).thenReturn(response);
        List<Joke> jokes = jokesConnector.fetchJokes(16, "single");

        assertThat(jokes.size(), CoreMatchers.is(1));
        assertThat(jokes.get(0), CoreMatchers.is(joke));

    }


    @Test(expected = IllegalStateException.class)
    public void fetchJokesResultsInError() {
        ReflectionTestUtils.setField(jokesConnector, "jokesResourceUrl", jokesResourceUrl);
        ResponseEntity<JokeResponse> response = ResponseEntity.internalServerError().build();
        when(restTemplate.getForEntity(jokesResourceUrl + "any?type=single&amount=16", JokeResponse.class)).thenReturn(response);
        jokesConnector.fetchJokes(16, "single");
    }
}
