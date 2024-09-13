package com.ridango.game.util;

import java.util.Random;

public class LetterRevealUtil {

    public static String revealLetters(String cocktailName, String hiddenName) {
        char[] hiddenArray = hiddenName.toCharArray();

        if (!hasHiddenLetter(hiddenArray)) {
            return hiddenName;
        }

        int lettersToReveal = Math.max(1, cocktailName.length() / 5);
        for (int i = 0; i < lettersToReveal; i++) {
            unhideRandomLetter(cocktailName, hiddenArray);
        }

        return new String(hiddenArray);
    }

    private static boolean hasHiddenLetter(char[] hiddenArray) {
        for (char c : hiddenArray) {
            if (c == '_') {
                return true;
            }
        }
        return false;
    }

    private static void unhideRandomLetter(String cocktailName, char[] hiddenArray) {
        Random random = new Random();
        int index;

        do {
            index = random.nextInt(cocktailName.length());
        } while (hiddenArray[index] != '_');

        hiddenArray[index] = cocktailName.charAt(index);
    }
}