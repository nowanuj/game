package com.anuj.football.game;

import com.anuj.football.game.service.IGameService;
import com.anuj.football.game.service.impl.GameServiceImpl;
import com.anuj.football.game.util.SportUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.logging.Slf4JLoggingSystem;

import java.io.Console;
import java.util.Scanner;

@SpringBootApplication
public class GameApplication {

//	public static void main(String[] args) {
//		SpringApplication.run(GameApplication.class, args);
//	}

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        IGameService service = new GameServiceImpl();
        SportUtil.displayOption();
        callFunctions(sc, service);
    }

    public static void callFunctions(Scanner sc, IGameService service) throws Exception {
        int actionKey = sc.nextInt();
        switch (actionKey){
            case 1:service.startGame(sc);
                service.displayScore();
                callFunctions(sc, service);
                break;
            case 2:service.finishGame(sc);
                callFunctions(sc, service);
                break;
            case 3:service.updateScore(sc);
                service.displayScore();
                callFunctions(sc, service);
                break;
        }
    }
}
