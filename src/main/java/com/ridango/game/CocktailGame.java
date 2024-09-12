package com.ridango.game;

import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import static com.ridango.game.LetterRevealUtil.revealLetters;
import static com.ridango.game.RandomHintUtil.getRandomHint;

@RequiredArgsConstructor
public class CocktailGame {
    private final CocktailService cocktailService;
    private final Scanner scanner = new Scanner(System.in);

    private int score = 0;
    private final Set<Long> usedCocktails = new HashSet<>();
    private final Set<String> usedHints = new HashSet<>();

    public void startGame() {
        System.out.println("Welcome to the Guess the Cocktail game!");
        boolean continuePlaying = true;

        while (continuePlaying) {
            continuePlaying = playRound();
        }

        System.out.println("Game over! Your final score is: " + score);
    }

    private boolean playRound() {
        Cocktail cocktail = getRandomCocktail();

        usedCocktails.add(cocktail.getId());

        String cocktailName = cocktail.getName();
        String hiddenName = "_".repeat(cocktailName.length());
        int attemptsLeft = 5;

        // TODO remove for playing, testing only
        System.out.println(cocktailName);

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
                System.out.println(getRandomHint(cocktail, usedHints));
            }
        }

        System.out.println("No attempts remain! The mystery cocktail was: " + cocktailName);
        return false;
    }

    private Cocktail getRandomCocktail() {
        Cocktail cocktail;
        int maxTries = 10; // Set the maximum number of tries
        int attempts = 0;

        do {
            cocktail = cocktailService.getRandomCocktail();
            attempts++;
        } while (usedCocktails.contains(cocktail.getId()) && attempts < maxTries);

        if (attempts == maxTries) {
            throw new RuntimeException("Failed to get a unique cocktail after " + maxTries + " attempts.");
        } else return cocktail;
    }


}