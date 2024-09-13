package com.ridango.game;

import com.ridango.game.service.CocktailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class CocktailGameApplication implements CommandLineRunner {

	@Autowired
    CocktailService cocktailService;

    public static void main(String[] args) {
		SpringApplication.run(CocktailGameApplication.class, args);
	}

	@Override public void run(String... args) throws Exception {
		CocktailGame game = new CocktailGame(cocktailService);
		game.startGame();
	}

}
