package com.ridango;

import com.ridango.game.util.LetterRevealUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LetterRevealUtilTest {

    @Test
    void testRevealLettersWithNoHiddenLetters() {
        String cocktailName = "B5";
        String hiddenName = "B5";

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
        if (input.length() != output.length()) {
            return false;
        }

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == output.charAt(i)) {
                return true;
            }
        }

        return false;
    }
}
