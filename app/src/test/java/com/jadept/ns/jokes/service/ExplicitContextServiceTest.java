package com.jadept.ns.jokes.service;

import com.jadept.ns.jokes.connectors.Joke;
import org.apache.commons.collections.FastHashMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Map;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class ExplicitContextServiceTest {

   private ExplicitContextService explicitContextService = new ExplicitContextService();

   @Test
    public  void aAppropriateJoke(){

       Joke joke= new Joke();
       joke.setJoke("that is a funny joke");
       assertTrue(explicitContextService.isJokeAppropriate(joke));
   }
   @Test
   public  void notAppropriateJoke(){

      Joke joke= new Joke();
      joke.setJoke("that is a sex related joke");
      assertFalse(explicitContextService.isJokeAppropriate(joke));
   }


   @Test
   public  void oneMoreExplicitJoke(){

      Joke joke= new Joke();
      joke.setJoke("that is a related joke");
      Map<String, Boolean> flags= new FastHashMap();
      flags.put("explicit", true);
      joke.setFlags(flags);
      assertFalse(explicitContextService.isJokeAppropriate(joke));
   }
}
