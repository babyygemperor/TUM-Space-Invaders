package tum.space.invaders.controller;

import javafx.geometry.Dimension2D;
import tum.space.invaders.GameOutcome;
import tum.space.invaders.controller.music.Music;
import tum.space.invaders.model.spaceship.EnemySpaceship;
import tum.space.invaders.model.spaceship.PlayerSpaceship;

import java.util.List;

public class GameBoard {

    private Dimension2D size;
    private int score;

    private Music backgroundMusicPlayer;
    private Music crashSoundEffectPlayer;

    private PlayerSpaceship playerSpaceship;
    private List<EnemySpaceship> enemySpaceships;

    private GameOutcome gameOutcome = GameOutcome.RUNNING;

    private boolean running;

    public GameBoard(Dimension2D size) {
        this.size = size;


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

    public Dimension2D getSize() {
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

    public Music getBackgroundMusicPlayer() {
        return backgroundMusicPlayer;
    }

    public void setBackgroundMusicPlayer(Music backgroundMusicPlayer) {
        this.backgroundMusicPlayer = backgroundMusicPlayer;
    }

    public void setEnemySpaceships(List<EnemySpaceship> enemySpaceships) {
        this.enemySpaceships = enemySpaceships;
    }

    public void setPlayerSpaceship(PlayerSpaceship playerSpaceship) {
        this.playerSpaceship = playerSpaceship;
    }

    public void setSize(Dimension2D size) {
        this.size = size;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isRunning() {
        return this.running;
    }

    public void update() {
        moveSpaceships();
    }

    private void moveSpaceships() {
        //TODO the master logic goes here
    }

    public GameOutcome getGameOutcome() {
        return gameOutcome;
    }
}
