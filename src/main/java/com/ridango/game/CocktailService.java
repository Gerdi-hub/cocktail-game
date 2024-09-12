package com.ridango.game;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CocktailService {

//    private final RestTemplate restTemplate = new RestTemplate();

    private final RestTemplate restTemplate;

    public CocktailService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Cocktail getRandomCocktail() {
        String url = "https://www.thecocktaildb.com/api/json/v1/1/random.php";
        CocktailResponse response = restTemplate.getForObject(url, CocktailResponse.class);
        Cocktail cocktail = response.getCocktailDtos().get(0).toCocktail();
        return cocktail;
    }
}
