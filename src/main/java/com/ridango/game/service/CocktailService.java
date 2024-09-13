package com.ridango.game.service;

import com.ridango.game.domain.Cocktail;
import com.ridango.game.domain.CocktailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class CocktailService {

    private final RestTemplate restTemplate;

    public Cocktail getRandomUnusedCocktail(Set<Long> usedCocktails) {
        Cocktail cocktail;
        int maxTries = 10;
        int attempts = 0;

        do {
            cocktail = getRandomCocktail();
            attempts++;
        } while (usedCocktails.contains(cocktail.getId()) && attempts < maxTries);

        if (attempts == maxTries) {
            throw new RuntimeException("Failed to get a unique cocktail after " + maxTries + " attempts.");
        } else return cocktail;
    }

    private Cocktail getRandomCocktail() {
        String url = "https://www.thecocktaildb.com/api/json/v1/1/random.php";
        CocktailResponse response = restTemplate.getForObject(url, CocktailResponse.class);

        if (response == null) {
            throw new RuntimeException("Could not get response from cocktailAPI");
        }

        return response.getCocktailDtos().get(0).toCocktail();
    }

}
