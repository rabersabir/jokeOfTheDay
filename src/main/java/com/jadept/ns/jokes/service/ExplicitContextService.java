package com.jadept.ns.jokes.service;

import com.jadept.ns.jokes.connectors.Joke;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class ExplicitContextService {
    /**
    * This list could be retrieved from a properties file, database or even a Micro Service.
     * These are only examples :)
     */
    private List<String> forbiddenWords = Arrays.asList("sex", "tieten");

    /**
     * checks if a joke Appropriate ad not containing words that could be insulting
     *
     * @param joke the give check to be checked
     * @return true if passed the check, otherwise false
     */
    public boolean isJokeAppropriate(Joke joke) {
        return joke.isSafe() && !containsSuspiciousWords(joke) && !hasRedFlag(joke.getFlags(), "sexist", "explicit", "racist");
    }

    private boolean hasRedFlag(Map<String, Boolean> flags, String... flagsToCheck) {
        return !CollectionUtils.isEmpty(flags) && Arrays.stream(flagsToCheck).anyMatch(flags::get);
    }

    /**
     * This check to be sure that joke does not contain some words that could be also sexist or racist.
     * Sometimes it is good to do not
     * @param joke the joke to checked
     * @return true if the contains of those words. false otherwise
     */
    private boolean containsSuspiciousWords(Joke joke) {
        return forbiddenWords.stream().anyMatch(e -> joke.getJoke().contains(e));
    }
}
