package com.ridango.game;

import lombok.RequiredArgsConstructor;

import java.util.Random;
import java.util.Set;

@RequiredArgsConstructor
public class RandomHintUtil {


    public static String getRandomHint(Cocktail cocktail, Set<String> usedHints) {
        String[] hintTypes = {"Category", "Glass", "Ingredients"};
        String hint = null;
        Random random = new Random();

        while (usedHints.size() < hintTypes.length) {
            int index = random.nextInt(hintTypes.length);
            String hintType = hintTypes[index];

            if (!usedHints.contains(hintType)) {
                usedHints.add(hintType);
                switch (hintType) {
                    case "Category":
                        hint = "Category: " + cocktail.getCategory();
                        break;
                    case "Glass":
                        hint = "Glass: " + cocktail.getGlass();
                        break;
                    case "Ingredients":
                        hint = "Ingredients: " + cocktail.getIngredients();
                        break;
                }
                break;
            }
        }
        return hint;
    }
}
