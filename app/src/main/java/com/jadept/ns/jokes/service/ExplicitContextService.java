package com.jadept.ns.jokes.service;

import com.jadept.ns.jokes.connectors.Joke;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class ExplicitContextService {

   private List<String> forbiddenWords = Arrays.asList("sex", "tieten");

    public boolean isJokeAppropriate(Joke joke) {
        return joke.isSafe() && !containsSuspiciousWords(joke) && !hasRedFlag(joke.getFlags(), "sexist", "explicit", "racist");
    }

    private boolean hasRedFlag(Map<String, Boolean> flags, String... flagsToCheck) {
        return Arrays.stream(flagsToCheck).anyMatch(flags::get);
    }

    private boolean containsSuspiciousWords(Joke joke) {
        return forbiddenWords.stream().anyMatch(e -> joke.getJoke().contains(e));
    }
}
