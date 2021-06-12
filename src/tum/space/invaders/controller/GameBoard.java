package tum.space.invaders.controller;

import tum.space.invaders.controller.music.Music;
import tum.space.invaders.model.spaceship.EnemySpaceship;
import tum.space.invaders.model.spaceship.PlayerSpaceship;

import java.util.List;

public class GameBoard {

    private int size;
    private int score;

    private Music MusicPlayer;

    private PlayerSpaceship playerSpaceship;
    private List<EnemySpaceship> enemySpaceships;

    public GameBoard() {
        //TODO initialise the game board
    }

    public boolean startGame() {
        //TODO start the game, boolean, to report if it was successfully started or not
        return false;
    }

    public boolean stopGame() {
        //TODO implement the method to stop the game, if it was successfully started or not
        return false;
    }

    public void refreshGameBoard() {
        //TODO implement the method to refresh the game board
    }

    public int getSize() {
        return size;
    }

    public int getScore() {
        return score;
    }

    public List<EnemySpaceship> getEnemySpaceships() {
        return enemySpaceships;
    }

    public PlayerSpaceship getPlayerSpaceship() {
        return playerSpaceship;
    }

    public Music getMusicPlayer() {
        return MusicPlayer;
    }

    public void setMusicPlayer(Music musicPlayer) {
        MusicPlayer = musicPlayer;
    }

    public void setEnemySpaceships(List<EnemySpaceship> enemySpaceships) {
        this.enemySpaceships = enemySpaceships;
    }

    public void setPlayerSpaceship(PlayerSpaceship playerSpaceship) {
        this.playerSpaceship = playerSpaceship;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
