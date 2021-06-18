package tum.space.invaders.controller;

import javafx.geometry.Dimension2D;
import tum.space.invaders.GameOutcome;
import tum.space.invaders.controller.music.Music;
import tum.space.invaders.model.spaceship.EnemySpaceship;
import tum.space.invaders.model.spaceship.PlayerSpaceship;
import tum.space.invaders.model.spaceship.Spaceship;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameBoard extends Subject{

    private Dimension2D size;
    private int score;

    private Music backgroundMusicPlayer;
    private Music crashSoundEffectPlayer;

    private Player player;
    private List<EnemySpaceship> enemySpaceships;
    private List<Spaceship> explodedSpaceships;

    private GameOutcome gameOutcome = GameOutcome.RUNNING;

    private boolean running;

    public GameBoard(Dimension2D size) {
        this.size = size;
        this.score = 0;

        this.enemySpaceships = new ArrayList<>();
        this.explodedSpaceships = new ArrayList<>();


        PlayerSpaceship playerSpaceship = new PlayerSpaceship(this.size);
        this.player = new Player(playerSpaceship);
        this.player.setup();

        createCars();
    }

    private void createCars() {
        EnemySpaceship enemy = new EnemySpaceship(this.size);
        enemySpaceships.add(enemy);
    }

    public boolean startGame() {
        playMusic();
        this.running = true;
        return true;
    }

    private void playMusic() {
        this.backgroundMusicPlayer.playMusic();
    }

    public boolean stopGame() {
        stopMusic();
        this.running = false;
        return true;
    }

    private void stopMusic() {
        this.backgroundMusicPlayer.stopMusic();
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
        return player.getPlayerSpaceship();
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

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
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


    @Override
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
