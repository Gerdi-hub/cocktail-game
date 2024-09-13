package com.ridango.game.util;

import com.ridango.game.domain.Cocktail;
import lombok.RequiredArgsConstructor;

import java.util.Random;
import java.util.Set;

@RequiredArgsConstructor
public class RandomHintUtil {


    public static String getRandomExtraHint(Cocktail cocktail, Set<String> usedHints) {
        String[] hintTypes = {"Category", "Glass", "Ingredients"};
        String hint = null;
        Random random = new Random();

        while (usedHints.size() < hintTypes.length) {
            int index = random.nextInt(hintTypes.length);
            String hintType = hintTypes[index];

            if (!usedHints.contains(hintType)) {
                usedHints.add(hintType);
                hint = switch (hintType) {
                    case "Category" -> "Category: " + cocktail.getCategory();
                    case "Glass" -> "Glass: " + cocktail.getGlass();
                    case "Ingredients" -> "Ingredients: " + cocktail.getIngredients();
                    default -> hint;
                };
            }
            if (hint != null && !hint.endsWith("null")) {
                break;
            }

        }
        return hint;
    }
}
