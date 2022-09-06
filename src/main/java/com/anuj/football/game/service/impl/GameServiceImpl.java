package com.anuj.football.game.service.impl;

import com.anuj.football.game.dao.IGameDao;
import com.anuj.football.game.dao.impl.GameDaoImpl;
import com.anuj.football.game.model.Sport;
import com.anuj.football.game.model.Team;
import com.anuj.football.game.service.IGameService;
import com.anuj.football.game.validation.ValidationService;
import org.springframework.util.StringUtils;

import javax.sound.midi.Soundbank;
import java.sql.Struct;
import java.util.*;

public class GameServiceImpl implements IGameService {

    private IGameDao iGameDao = new GameDaoImpl();

    List<String> teamList = Arrays.asList(new String[]{"Home", "Away"});
    @Override
    public void startGame(Scanner sc) {
        Map<String, String> teamMap = getTeamNames(sc);
        String homeTeam = teamMap.get(teamList.get(0));
        String awayTeam = teamMap.get(teamList.get(1));
        if(!StringUtils.isEmpty(homeTeam) && !StringUtils.isEmpty(awayTeam)) {
            Random random = new Random();
            List<Team> teams = getListOfTeam(homeTeam, awayTeam, random);
            Sport sportObject = Sport.builder()
                    .id(random.nextInt(5))
                    .teams(teams)
                    .build();
            iGameDao.startGame(sportObject);
        }else {
            System.out.println("Either of Home Team or Away team name is Empty");
        }
    }

    @Override
    public void finishGame(Scanner sc) throws Exception {
        int gameId = getGameId(sc);
        Map<Integer, Sport> gameCollection = iGameDao.getSportCollection();
        if(gameCollection.containsKey(gameId)) {
            iGameDao.getSportCollection().remove(gameId);
            for (int i = 0; i < 30; i++) {
                System.out.println("\b");
            }
        }else {
            throw new Exception("Game is doestn't exist");
        }
        displayScore();
    }

    public int getGameId(Scanner sc){
        try {
            System.out.println("Enter the Match id :");
            return sc.nextInt();
        }catch (Exception e){
            System.out.println("Alphabets are not allowed");
            getGameId(sc);
        }
        return -1;
    }

    @Override
    public void updateScore(Scanner sc) {

        try {
            System.out.println("Enter Team Name :");
            String teamName = sc.next();
            System.out.println("Enter Score to update :");
            int newScore = sc.nextInt();
            if(!StringUtils.isEmpty(teamName) || newScore != 0){

            }else {
                System.out.println("Data is incorrect");
                updateScore(sc);
            }
            searchAndUpdateTeam(teamName, newScore);
        } catch (Exception e) {
            updateScore(sc);
        }

    }



    @Override
    public void displayScore() {
        Map<Integer, Sport> sportData = iGameDao.getSportCollection();
        System.out.println("===========================================================================");
        System.out.println("Match      ||Home Team           || Score      ||Home Team      || Score ");
        System.out.println("===========================================================================");
        for(Map.Entry<Integer, Sport> entry : sportData.entrySet()){
            List<Team> teams = entry.getValue().getTeams();
            System.out.print("Match-"+entry.getKey()+"    "+teams.get(0).getTeamName()+"                  "+teams.get(0).getScore()+"          ");
            System.out.print(teams.get(1).getTeamName()+"              "+teams.get(1).getScore()+"\n");
        }
    }

    private List<Team> getListOfTeam(String homeTeam, String awayTeam, Random random){
        List<Team> teams = new ArrayList<>();

        Team team1 = new Team(random.nextInt(50), homeTeam, 0);
        Team team2 = new Team(random.nextInt(50), awayTeam, 0);

        teams.add(team1);
        teams.add(team2);
        return teams;
    }


  public void searchAndUpdateTeam(String team1, int score) throws Exception {
      int key = 0;
      Map<Integer, Sport> sportData = iGameDao.getSportCollection();
      for(Map.Entry<Integer, Sport> entry : sportData.entrySet()){
          List<Team> teamList = entry.getValue().getTeams();
          if(teamList.get(0).getTeamName().toUpperCase().equals(team1.toUpperCase()) || teamList.get(1).getTeamName().toUpperCase().equals(team1.toUpperCase())){
              key = entry.getKey();
              break;
          }
      }

      Sport sport = sportData.get(key);
      for(Team  team: sport.getTeams()){
          if(team1.toUpperCase().equals(team.getTeamName().toUpperCase())) {
              team.setScore(score);
              break;
          }
      }
      iGameDao.updateScore(key, sport);
  }

  public Map<String, String> getTeamNames(Scanner sc){
        Map<String, String> teamMap = new HashMap<>();
        for(int i=0;i<2;i++) {
            System.out.println("Enter name of "+teamList.get(i)+" team");
            String team = sc.next();
            teamMap.put(teamList.get(i), team);
        }
        return teamMap;
  }

}
