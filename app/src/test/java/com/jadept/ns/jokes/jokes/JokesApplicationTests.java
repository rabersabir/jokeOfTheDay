package com.jadept.ns.jokes.jokes;

import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import com.jadept.ns.jokes.jokes.api.JokeOfTheDayController;
import com.jadept.ns.jokes.jokes.dto.Joke;
import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


@SpringBootTest
@AutoConfigureWireMock
public class JokesApplicationTests {
    @Autowired
    JokeOfTheDayController joke;
    @ClassRule
    public static WireMockClassRule wiremock = new WireMockClassRule();

    @Test
    void happyFlow() {

        stubFor(get("/jokes/")
                .willReturn(okJson("{\n" +
                        "  \"error\": false,\n" +
                        "  \"amount\": 1,\n" +
                        "  \"jokes\": [\n" +
                        "    {\n" +
                        "      \"category\": \"Programming\",\n" +
                        "      \"type\": \"single\",\n" +
                        "      \"joke\": \"UDP is better in the COVID era since it avoids unnecessary handshakes.\",\n" +
                        "      \"flags\": {\n" +
                        "        \"nsfw\": false,\n" +
                        "        \"religious\": false,\n" +
                        "        \"political\": false,\n" +
                        "        \"racist\": false,\n" +
                        "        \"sexist\": false,\n" +
                        "        \"explicit\": false\n" +
                        "      },\n" +
                        "      \"id\": 259,\n" +
                        "      \"safe\": true,\n" +
                        "      \"lang\": \"en\"\n" +
                        "    }\n" +
                        "  ]\n" +
                        "}")));


        ResponseEntity<Joke> theJokeOfTheDay = joke.retrieveJoke();
        assertThat(theJokeOfTheDay.getStatusCode(), is(HttpStatus.OK));
        assertThat(theJokeOfTheDay.getBody().getRandomJoke(), is("UDP is better in the COVID era since it avoids unnecessary handshakes."));
    }


    @Test
    void happyFlowWithExplicietJoke() {

        stubFor(get("/jokes/")
                .willReturn(okJson("{\n" +
                        "  \"error\": false,\n" +
                        "  \"amount\": 1,\n" +
                        "  \"jokes\": [\n" +
                        "    {\n" +
                        "      \"category\": \"Programming\",\n" +
                        "      \"type\": \"single\",\n" +
                        "      \"joke\": \"UDP is better in the COVID era since it avoids unnecessary handshakes.\",\n" +
                        "      \"flags\": {\n" +
                        "        \"nsfw\": false,\n" +
                        "        \"religious\": false,\n" +
                        "        \"political\": false,\n" +
                        "        \"racist\": false,\n" +
                        "        \"sexist\": false,\n" +
                        "        \"explicit\": false\n" +
                        "      },\n" +
                        "      \"id\": 259,\n" +
                        "      \"safe\": true,\n" +
                        "      \"lang\": \"en\"\n" +
                        "    },{\n" +
                        "      \"category\": \"Programming2\",\n" +
                        "      \"type\": \"single\",\n" +
                        "      \"joke\": \"This is some dirty short joke\",\n" +
                        "      \"flags\": {\n" +
                        "        \"nsfw\": false,\n" +
                        "        \"religious\": false,\n" +
                        "        \"political\": false,\n" +
                        "        \"racist\": false,\n" +
                        "        \"sexist\": false,\n" +
                        "        \"explicit\": true\n" +
                        "      },\n" +
                        "      \"id\": 260,\n" +
                        "      \"safe\": true,\n" +
                        "      \"lang\": \"en\"\n" +
                        "    }\n" +
                        "  ]\n" +
                        "}")));


        ResponseEntity<Joke> theJokeOfTheDay = joke.retrieveJoke();
        assertThat(theJokeOfTheDay.getStatusCode(), is(HttpStatus.OK));
        assertThat(theJokeOfTheDay.getBody().getRandomJoke(), is("UDP is better in the COVID era since it avoids unnecessary handshakes."));
    }


    @Test
    void funnyShortJoke() {

        stubFor(get("/jokes/")
                .willReturn(okJson("{\n" +
                        "  \"error\": false,\n" +
                        "  \"amount\": 1,\n" +
                        "  \"jokes\": [\n" +
                        "    {\n" +
                        "      \"category\": \"Programming\",\n" +
                        "      \"type\": \"single\",\n" +
                        "      \"joke\": \"UDP is better in the COVID era since it avoids unnecessary handshakes.\",\n" +
                        "      \"flags\": {\n" +
                        "        \"nsfw\": false,\n" +
                        "        \"religious\": false,\n" +
                        "        \"political\": false,\n" +
                        "        \"racist\": false,\n" +
                        "        \"sexist\": false,\n" +
                        "        \"explicit\": false\n" +
                        "      },\n" +
                        "      \"id\": 259,\n" +
                        "      \"safe\": true,\n" +
                        "      \"lang\": \"en\"\n" +
                        "    },{\n" +
                        "      \"category\": \"Programming\",\n" +
                        "      \"type\": \"single\",\n" +
                        "      \"joke\": \"This is some dirty short joke\",\n" +
                        "      \"flags\": {\n" +
                        "        \"nsfw\": false,\n" +
                        "        \"religious\": false,\n" +
                        "        \"political\": false,\n" +
                        "        \"racist\": false,\n" +
                        "        \"sexist\": false,\n" +
                        "        \"explicit\": true\n" +
                        "      },\n" +
                        "      \"id\": 260,\n" +
                        "      \"safe\": true,\n" +
                        "      \"lang\": \"en\"\n" +
                        "    },{\n" +
                        "      \"category\": \"Programming\",\n" +
                        "      \"type\": \"single\",\n" +
                        "      \"joke\": \"Why Java engineer wear glasses ? because they can't C#\",\n" +
                        "      \"flags\": {\n" +
                        "        \"nsfw\": false,\n" +
                        "        \"religious\": false,\n" +
                        "        \"political\": false,\n" +
                        "        \"racist\": false,\n" +
                        "        \"sexist\": false,\n" +
                        "        \"explicit\": false\n" +
                        "      },\n" +
                        "      \"id\": 261,\n" +
                        "      \"safe\": true,\n" +
                        "      \"lang\": \"en\"\n" +
                        "    }\n" +
                        "  ]\n" +
                        "}")));

        ResponseEntity<Joke> theJokeOfTheDay = joke.retrieveJoke();
        assertThat(theJokeOfTheDay.getStatusCode(), is(HttpStatus.OK));
        assertThat(theJokeOfTheDay.getBody().getRandomJoke(), is("Why Java engineer wear glasses ? because they can't C#"));
    }

}