package com.anuj.football.game.service;

import java.util.Scanner;

public interface IGameService {

    void startGame(Scanner sc);

    void finishGame(Scanner sc) throws Exception;

    void updateScore(Scanner sc);

    void displayScore();

}
