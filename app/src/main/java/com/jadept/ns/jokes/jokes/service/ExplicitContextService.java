package com.jadept.ns.jokes.jokes.service;

import com.jadept.ns.jokes.jokes.connectors.Joke;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class ExplicitContextService {

    List<String> forbiddenWords = Arrays.asList("sex", "tieten");

    public boolean isJokeAppropriate(Joke joke) {
        if (!joke.isSafe() || containsSuspiciousWords(joke) || hasRedFlag(joke.getFlags(), "sexist", "explicit","racist")) {
            return false;
        }
        return true;
    }

    private boolean hasRedFlag(Map<String, Boolean> flags, String... flagsToCheck) {
        return Arrays.stream(flagsToCheck).anyMatch(e -> flags.get(e));
    }

    private boolean containsSuspiciousWords(Joke joke) {
        return forbiddenWords.stream().anyMatch(e -> joke.getJoke().contains(e));
    }
}
