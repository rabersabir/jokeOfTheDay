package com.jadept.ns.jokes.connector;

import com.jadept.ns.jokes.connectors.JokesConnector;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(MockitoJUnitRunner.class)
public class JokesConnectorTest {

    @InjectMocks
    private JokesConnector jokesConnector;

    @Mock
    private RestTemplate restTemplate;

    @Test
    public  void test(){

        jokesConnector.fetchJokes(16, "single");
    }
}
