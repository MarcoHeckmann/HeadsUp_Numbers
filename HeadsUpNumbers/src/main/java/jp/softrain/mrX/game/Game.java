package jp.softrain.mrX.game;

import jp.softrain.mrX.Main;
import jp.softrain.mrX.gamestates.GameStates;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.*;

public class Game {

    private Main instance;

    public Game(Main instance) {
        this.instance = instance;
    }

    private GameStates gameStates = GameStates.WAITING;
    private Player player1;
    private Player player2;
    private boolean IsPlayerOneTurn;
    private boolean IsPlayerTwoTurn;
    private int correctNumber;


    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public GameStates getGameStates() {
        return gameStates;
    }

    public void setGameStates(GameStates gameStates) {
        this.gameStates = gameStates;
    }

    public boolean isReadyToStart(){
        return player1 != null && player2 != null;
    }

    public void startGame(){
        if (!isReadyToStart() || getGameStates() != GameStates.WAITING) return;
        setGameStates(GameStates.INGAME);
        randomizeNumber();

        RandomizePlayerTurn();

        for (Player player : Bukkit.getOnlinePlayers()){
            player.sendMessage("§7The Server has chosen a Number, §aYou can now guess!");
            player.sendMessage("§a" + getCurrentTurnPlayer().getName() + " §7Guess first.");
        }
    }
    public void randomizeNumber(){
        Random random = new Random();

        correctNumber = random.nextInt(0,10); //10M
    }

    public int getCorrectNumber(){

        return correctNumber;
    }

    public void switchPlayer(){
        if (isPlayerOneTurn()){
            setPlayerOneTurn(false);
            setPlayerTwoTurn(true);
        } else {
            setPlayerOneTurn(true);
            setPlayerTwoTurn(false);
        }
    }

    public Player getCurrentTurnPlayer(){
        if (isPlayerOneTurn()){
            return player1;
        }
        if (isPlayerTwoTurn()){
            return player2;
        }
        return null;
    }

    public boolean isPlayerOneTurn() {
        return IsPlayerOneTurn;
    }

    public boolean isPlayerTwoTurn() {
        return IsPlayerTwoTurn;
    }

    public void setPlayerOneTurn(boolean playerOneTurn) {
        IsPlayerOneTurn = playerOneTurn;
    }

    public void setPlayerTwoTurn(boolean playerTwoTurn) {
        IsPlayerTwoTurn = playerTwoTurn;
    }
    public void RandomizePlayerTurn(){
        List<Player> players = new ArrayList<>();

        if (player1 != null) players.add(player1);
        if (player2 != null) players.add(player2);

        Random random = new Random();

        Player player = players.get(random.nextInt(players.size()));

        boolean isPlayerOne = (player == player1);
        setPlayerOneTurn(isPlayerOne);
        setPlayerTwoTurn(!isPlayerOne);
    }
}
