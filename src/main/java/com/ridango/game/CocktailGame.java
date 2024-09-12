package com.ridango.game;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class CocktailGame {
    private final CocktailService cocktailService;
    private final Scanner scanner;
    private int score;
    private Set<Long> usedCocktails;

    public CocktailGame(CocktailService cocktailService) {
        this.cocktailService = cocktailService;
        this.scanner = new Scanner(System.in);
        this.score = 0;
        this.usedCocktails = new HashSet<>();
    }

    public void startGame() {
        System.out.println("Welcome to the Guess the Cocktail game!");
        boolean continuePlaying = true;

        while (continuePlaying) {
            continuePlaying = playRound();
        }

        System.out.println("Game over! Your final score is: " + score);
    }

    private boolean playRound() {
        Cocktail cocktail = cocktailService.getRandomCocktail();
        while (usedCocktails.contains(cocktail.getId())) {
            cocktail = cocktailService.getRandomCocktail();
        }
        usedCocktails.add(cocktail.getId());

        String cocktailName = cocktail.getName();
        String hiddenName = "_".repeat(cocktailName.length());
        int attemptsLeft = 5;

        System.out.println("Instructions: " + cocktail.getInstructions());
        System.out.println("Guess the cocktail: " + hiddenName);

        while (attemptsLeft > 0) {
            System.out.println("Attempts left: " + attemptsLeft);
            System.out.print("Your guess: ");
            String guess = scanner.nextLine();

            if (guess.equalsIgnoreCase(cocktailName)) {
                score += attemptsLeft;
                System.out.println("Correct! Your score is now: " + score);
                return true;
            } else {
                attemptsLeft--;
                hiddenName = revealLetters(cocktailName, hiddenName);
                System.out.println("Wrong! Here's a hint: " + hiddenName);
                System.out.println("Category: " + cocktail.getCategory());
                System.out.println("Glass: " + cocktail.getGlass());
                System.out.println("Ingredients: " + cocktail.getIngredients());
            }
        }

        System.out.println("No attempts remain! The mystery cocktail was: " + cocktailName);
        return false;
    }

    private String revealLetters(String cocktailName, String hiddenName) {
        char[] hiddenArray = hiddenName.toCharArray();
        for (int i = 0; i < cocktailName.length(); i++) {
            if (hiddenArray[i] == '_') {
                hiddenArray[i] = cocktailName.charAt(i);
                break;
            }
        }
        return new String(hiddenArray);
    }
}