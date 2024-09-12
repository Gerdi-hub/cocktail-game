package com.ridango.game;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CocktailDto {
    @JsonProperty("idDrink")
    String idDrink;
    @JsonProperty("strDrink")
    String strDrink;
    @JsonProperty("strGlass")
    String strGlass;
    @JsonProperty("strCategory")
    String strCategory;
    @JsonProperty("strInstructions")
    String strInstructions;
    @JsonProperty("strIngredient1")
    String strIngredient1;
    @JsonProperty("strIngredient2")
    String strIngredient2;
    @JsonProperty("strIngredient3")
    String strIngredient3;
    @JsonProperty("strIngredient4")
    String strIngredient4;

    public Cocktail toCocktail() {
        List<String> ingredients = new ArrayList<>();
        if (strIngredient1 != null && !strIngredient1.isEmpty()) ingredients.add(strIngredient1);
        if (strIngredient2 != null && !strIngredient2.isEmpty()) ingredients.add(strIngredient2);
        if (strIngredient3 != null && !strIngredient3.isEmpty()) ingredients.add(strIngredient3);
        if (strIngredient4 != null && !strIngredient4.isEmpty()) ingredients.add(strIngredient4);

        return Cocktail.builder()
                .id(Long.valueOf(idDrink))
                .name(strDrink)
                .instructions(strInstructions)
                .glass(strGlass)
                .category(strCategory)
                .ingredients(ingredients)
                .build();
    }

}
