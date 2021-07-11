package tum.space.invaders.controller;

import javafx.geometry.Dimension2D;
import tum.space.invaders.GameOutcome;
import tum.space.invaders.controller.music.Music;
import tum.space.invaders.model.spaceship.EnemySpaceship;
import tum.space.invaders.model.spaceship.PlayerSpaceship;
import tum.space.invaders.model.spaceship.Spaceship;

import java.util.ArrayList;
import java.util.List;

public class GameBoard {

	private Dimension2D size;
	private int score;

	private Music backgroundMusicPlayer;
	private Music crashSoundEffectPlayer;

	private Player player;
	private List<EnemySpaceship> enemySpaceships;
	private List<Spaceship> explodedSpaceships;

	private List<LaserBeam> activeLaserbeams;
	private List<LaserBeam> explodedLaserbeams;

	private GameOutcome gameOutcome = GameOutcome.RUNNING;

	private boolean running;

	public GameBoard(Dimension2D size) {
		this.size = size;
		this.score = 0;

		this.enemySpaceships = new ArrayList<>();
		this.explodedSpaceships = new ArrayList<>();

		PlayerSpaceship playerSpaceship = new PlayerSpaceship(this, this.size);
		this.player = new Player(playerSpaceship);
		this.player.setup();

		createCars();
	}

	private void createCars() {
		EnemySpaceship enemy = new EnemySpaceship(this);
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

	public void update() {
		moveSpaceships();
	}

	private void moveSpaceships() {
		// update positions of all spaceships
		for (Spaceship spaceship : this.enemySpaceships) {
			spaceship.move();
		}
		player.move();

		for (LaserBeam laserBeam : this.activeLaserbeams) {
			laserBeam.move();
		}

		// checks if game was lost or won before
		if (player.getPlayerSpaceship().gotHit())
			gameOutcome = GameOutcome.LOST;

		if (enemySpaceships.isEmpty())
			gameOutcome = GameOutcome.WON;

		// iterates thru activeLaserbeams and looks if they hit anything

		for (LaserBeam laserBeam : this.activeLaserbeams) {

			for (EnemySpaceship enemySpaceship : this.enemySpaceships) {
				if (laserBeam.getLocation().equals(enemySpaceship.getLocation())) {
					laserBeam.hit();
					enemySpaceship.disappear();
					crashSoundEffectPlayer.playMusic();
					enemySpaceships.remove(enemySpaceship);
					explodedSpaceships.add(enemySpaceship);
					activeLaserbeams.remove(laserBeam);
					explodedLaserbeams.add(laserBeam);
				}
				if (player.getPlayerSpaceship().getLocation().equals(laserBeam.getLocation())) {
					laserBeam.hit();
					player.getPlayerSpaceship().disappear();
					crashSoundEffectPlayer.playMusic();
					activeLaserbeams.remove(laserBeam);
					explodedLaserbeams.add(laserBeam);
				}

			}
		}
		// if laserbeasm are outside the playing field they get removed
		for (LaserBeam laserBeam : this.activeLaserbeams) {
			if (laserBeam.getLocation().getX() < 0.0 || laserBeam.getLocation().getX() > this.getSize().getHeight()
					|| laserBeam.getLocation().getY() < 0.0
					|| laserBeam.getLocation().getY() > this.getSize().getWidth()) {
				activeLaserbeams.remove(laserBeam);
				explodedLaserbeams.add(laserBeam);
			}
		}

	}

	public GameOutcome getGameOutcome() {
		return gameOutcome;
	}
}
