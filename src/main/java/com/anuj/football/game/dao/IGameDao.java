package com.anuj.football.game.dao;

import com.anuj.football.game.model.Sport;

import java.util.HashMap;
import java.util.Map;

public interface IGameDao {

    void startGame(Sport sport);

    void finishGame(int gameId);

    void updateScore(int key, Sport sport) throws Exception;

    Map<Integer, Sport> getSportCollection();
}
