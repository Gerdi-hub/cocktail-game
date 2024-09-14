package com.ridango;

import com.ridango.game.domain.Cocktail;
import com.ridango.game.util.RandomHintUtil;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
class RandomHintUtilTest {

    @Test
    void testGetRandomExtraHintWithNoUsedHints() {
        Cocktail cocktail = Cocktail.builder()
                .name("Margarita")
                .category("Cocktail")
                .glass("Cocktail glass")
                .ingredients(List.of("Tequila, Triple sec, Lime juice, Salt"))
                .build();
        Set<String> usedHints = new HashSet<>();

        String hint = RandomHintUtil.getRandomExtraHint(cocktail, usedHints);

        assertNotNull(hint);
        assertTrue(hint.startsWith("Category:") || hint.startsWith("Glass:") || hint.startsWith("Ingredients:"));
    }

    @Test
    void testGetRandomExtraHintWithSomeUsedHints() {
        Cocktail cocktail = Cocktail.builder()
                .name("Margarita")
                .category("Cocktail")
                .glass("Cocktail glass")
                .ingredients(List.of("Tequila, Triple sec, Lime juice, Salt"))
                .build();
        Set<String> usedHints = new HashSet<>();
        usedHints.add("Category");

        String hint = RandomHintUtil.getRandomExtraHint(cocktail, usedHints);

        assertNotNull(hint);
        assertTrue(hint.startsWith("Glass:") || hint.startsWith("Ingredients:"));
    }

    @Test
    void testGetRandomExtraHintWithAllHintsUsed() {
        Cocktail cocktail = Cocktail.builder()
                .name("Margarita")
                .category("Cocktail")
                .glass("Cocktail glass")
                .ingredients(List.of("Tequila, Triple sec, Lime juice, Salt"))
                .build();
        Set<String> usedHints = new HashSet<>();
        usedHints.add("Category");
        usedHints.add("Glass");
        usedHints.add("Ingredients");

        String hint = RandomHintUtil.getRandomExtraHint(cocktail, usedHints);

        assertNull(hint);
    }

    @Test
    void testGetRandomExtraHintWithEmptyCocktailFields() {
        Cocktail cocktail = Cocktail.builder()
                .name("")
                .category("")
                .glass("")
                .ingredients(new ArrayList<>())
                .build();
        Set<String> usedHints = new HashSet<>();

        String hint = RandomHintUtil.getRandomExtraHint(cocktail, usedHints);

        assertNotNull(hint);
        assertTrue(hint.startsWith("Category:") || hint.startsWith("Glass:") || hint.startsWith("Ingredients:"));
    }
}