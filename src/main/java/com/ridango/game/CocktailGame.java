package com.ridango.game;

import com.ridango.game.domain.Cocktail;
import com.ridango.game.domain.HighScore;
import com.ridango.game.repository.HighScoreRepository;
import com.ridango.game.service.CocktailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import static com.ridango.game.util.LetterRevealUtil.revealLetters;
import static com.ridango.game.util.RandomHintUtil.getRandomExtraHint;

@Service
@RequiredArgsConstructor
public class CocktailGame {
    private final CocktailService cocktailService;
    private final HighScoreRepository highScoreRepository;
    private final Scanner scanner = new Scanner(System.in);
    private int score;
    private final Set<Long> usedCocktails = new HashSet<>();
    private String playerName;


    public void playGame() {
        boolean playNewGame = true;
        while (playNewGame) {
            score = 0;
            System.out.println("Welcome to the Guess the Cocktail game!");
            System.out.print("Please enter your name: ");
            playerName = scanner.nextLine();

            boolean isGameOngoing = true;
            while (isGameOngoing) {
                isGameOngoing = playRound();
            }

            System.out.println("Game over! Your final score is: " + score);
            saveHighScore();
            printTopHighScores();

            System.out.print("Do you want to play again? (yes/no): ");
            String response = scanner.nextLine();
            playNewGame = response.equalsIgnoreCase("yes");
        }
        System.out.println("BYE");
    }

    private boolean playRound() {
        Cocktail cocktail = cocktailService.getRandomUnusedCocktail(usedCocktails);
        usedCocktails.add(cocktail.getId());

        String cocktailName = cocktail.getName();
        String hiddenName = "_".repeat(cocktailName.length());
        final Set<String> usedHints = new HashSet<>();
        int attemptsLeft = 5;

        // TODO remove for playing, testing only
//        System.out.println(cocktailName);

        printRoundIntro(cocktail.getInstructions(), hiddenName);

        while (attemptsLeft > 0) {
            System.out.println("Attempts left: " + attemptsLeft);
            System.out.print("Your guess: ");
            String guess = scanner.nextLine();

            if (guess.equalsIgnoreCase(cocktailName)) {
                return handleCorrectGuess(attemptsLeft);
            } else {
                attemptsLeft--;
            }
            if (attemptsLeft > 0) {
                hiddenName = revealLetters(cocktailName, hiddenName);
                System.out.println("Wrong! Here's a hint: " + hiddenName);
                printRandomExtraHintIfAvailable(cocktail, usedHints);
            }
        }

        System.out.println("No attempts remain! The mystery cocktail was: " + cocktailName);
        return false;
    }

    private static void printRoundIntro(String instructions, String hiddenName) {
        System.out.println("Instructions: " + instructions);
        System.out.println("Guess the cocktail: " + hiddenName);
    }

    private boolean handleCorrectGuess(int attemptsLeft) {
        score += attemptsLeft;
        System.out.println("Correct! Your score is now: " + score);
        return true;
    }

    private static void printRandomExtraHintIfAvailable(Cocktail cocktail, Set<String> usedHints) {
        String hint = getRandomExtraHint(cocktail, usedHints);
        if (hint != null) {
            System.out.println(hint);
        }
    }
    private void saveHighScore() {
        HighScore highScore = new HighScore();
        highScore.setScore(score);
        highScore.setPlayerName(playerName);

        highScoreRepository.save(highScore);
    }

    private void printTopHighScores() {
        List<HighScore> topHighScores = highScoreRepository.findTop10ByOrderByScoreDesc();
        System.out.println("Top 10 High Scores:");
        for (HighScore highScore : topHighScores) {
            System.out.println("Player: " + highScore.getPlayerName() + ", Score: " + highScore.getScore());
        }
    }

}