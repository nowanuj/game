package com.anuj.football.game.dao.impl;

import com.anuj.football.game.dao.IGameDao;
import com.anuj.football.game.model.Sport;

import java.util.*;

public class GameDaoImpl implements IGameDao {

    private static Map<Integer, Sport> SPORT_COLLECTION = new HashMap<>();
    //private static Map<Integer>

    @Override
    public void startGame(Sport sport) {
     if(Objects.nonNull(sport)){
         SPORT_COLLECTION.put(sport.getId(),sport);
     }else {
         System.out.println("Error while starting the Sport");
     }
    }

    @Override
    public void finishGame(int gameId) {

    }

    @Override
    public void updateScore(int key, Sport sport) throws Exception {
        if(Objects.nonNull(sport) && key != 0) {
            SPORT_COLLECTION.put(key, sport);
        }else{
            throw new Exception("Incorrect Data");
        }
    }

    public Map<Integer, Sport> getSportCollection() {
        return SPORT_COLLECTION;
    }
}
