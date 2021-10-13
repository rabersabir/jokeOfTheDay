package com.jadept.ns.jokes.service;

import com.jadept.ns.jokes.connectors.Joke;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ExplicitContextServiceTest {

   private ExplicitContextService explicitContextService = new ExplicitContextService();

   @Test
    public  void aAppropriateJoke(){

       Joke joke= new Joke();
       joke.setJoke("that is a funny joke");
       explicitContextService.isJokeAppropriate(joke);
   }
}
