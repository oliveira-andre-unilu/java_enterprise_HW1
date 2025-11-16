package lu.uni.jakartaee.moviefy.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lu.uni.jakartaee.moviefy.api.MovieDTO;
import lu.uni.jakartaee.moviefy.exeptions.APICallNotSuccessful;
import lu.uni.jakartaee.moviefy.exeptions.ActorNotCreatedException;
import lu.uni.jakartaee.moviefy.exeptions.DirectorNotCreatedException;
import lu.uni.jakartaee.moviefy.jpa.Actor;
import lu.uni.jakartaee.moviefy.jpa.Director;
import lu.uni.jakartaee.moviefy.jpa.Movie;

import java.io.IOException;
import java.io.Serializable;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Stateless
@Named("movieAPIService")
public class MovieAPIService implements Serializable {

    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final String BASE_URL = "https://www.omdbapi.com/?apikey=2739384b";

    @Inject
    private MovieService movieService;

    public MovieDTO callAPI(String title, Optional<String> type, Optional<Integer> releaseYear)
            throws APICallNotSuccessful {

        // Build URL correctly
        String finalURL = BASE_URL +
                "&t=" + URLEncoder.encode(title, StandardCharsets.UTF_8);

        if (type.isPresent()) {
            finalURL += "&type=" + type.get();
        }
        if (releaseYear.isPresent()) {
            finalURL += "&y=" + releaseYear.get();
        }

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(finalURL))
                .header("Accept", "application/json")
                .build();

        HttpResponse<String> response;
        try {
            response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new APICallNotSuccessful("Network error while calling OMDb");
        }

        if (response.statusCode() != 200) {
            throw new APICallNotSuccessful("OMDb returned error code " + response.statusCode());
        }

        try {
            return MAPPER.readValue(response.body(), MovieDTO.class);
        } catch (JsonProcessingException e) {
            throw new APICallNotSuccessful("Could not parse JSON returned by OMDb " + e.toString());
        }
    }

    public Movie saveMovietoDB(MovieDTO dto) {

        List<String> actors = Arrays.stream(dto.getActors().split(","))
                .map(String::trim)
                .toList();

        int runTime = 0;
        try {
            runTime = Integer.parseInt(dto.getRuntime().split(" ")[0]);
        } catch (Exception ignored) {}

        return movieService.addMovie2(
                dto.getTitle(),
                dto.getDirector(),
                actors,
                dto.getGenre(),
                runTime,
                Integer.parseInt(dto.getYear()),
                dto.getPlot(),
                dto.getPoster()
        );

    }
}

