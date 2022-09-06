package com.anuj.football.game.validation;

import org.springframework.util.StringUtils;

public class ValidationService {

    public static void validateTeamName(String team1, String team2) throws Exception {
        if(StringUtils.isEmpty(team1) && StringUtils.isEmpty(team2)){
            throw new Exception("Either of Home Team or Away Team name is empty or null");
        }
    }

}
