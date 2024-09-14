package com.ridango;

import com.ridango.game.util.LetterRevealUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LetterRevealUtilTest {

    @Test
    void testRevealLettersWithNoHiddenLetters() {
        String cocktailName = "Tom Collins";
        String hiddenName = "Tom Collins";

        String result = LetterRevealUtil.revealLetters(cocktailName, hiddenName);

        assertEquals(hiddenName, result);
    }

    @Test
    void testRevealLettersWithAllHiddenLetters() {
        String cocktailName = "Tom Collins";
        String hiddenName = "___________";

        String result = LetterRevealUtil.revealLetters(cocktailName, hiddenName);

        assertEquals(hiddenName.length(), result.length());
        assertTrue(hasMatchingLetter(cocktailName, result));
    }

    @Test
    void testRevealLettersWithPartiallyHiddenLetters() {
        String cocktailName = "Mojito";
        String hiddenName = "M_____";

        String result = LetterRevealUtil.revealLetters(cocktailName, hiddenName);

        assertNotEquals(hiddenName, result);
        assertEquals(hiddenName.length(), result.length());
        assertTrue(result.contains("M"));
    }

    private boolean hasMatchingLetter(String input, String output) {
        for (char c : input.toCharArray()) {
            if (output.indexOf(c) >= 0) {
                return true;
            }
        }
        return false;
    }
}
