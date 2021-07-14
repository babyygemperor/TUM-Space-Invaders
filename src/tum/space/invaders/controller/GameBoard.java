package tum.space.invaders.controller;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import tum.space.invaders.GameOutcome;
import tum.space.invaders.controller.music.CrashSound;
import tum.space.invaders.controller.music.Music;
import tum.space.invaders.model.spaceship.EnemySpaceship;
import tum.space.invaders.model.spaceship.PlayerSpaceship;
import tum.space.invaders.model.spaceship.Spaceship;

import java.awt.*;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Random;

public class GameBoard {

	private Dimension2D size;
	private int score;

	private Music backgroundMusicPlayer;
	private Music crashSoundEffectPlayer = new CrashSound();

	private Player player;
	private Player player2;
	private List<EnemySpaceship> enemySpaceships;
	private List<Spaceship> explodedSpaceships;

	private List<LaserBeam> activeLaserbeams;
	private List<LaserBeam> explodedLaserbeams;

	private GameOutcome gameOutcome = GameOutcome.RUNNING;

	private boolean running;
	private boolean multiPlayer;

	public GameBoard(Dimension2D size) {
		this.size = size;
		this.score = 0;

		this.enemySpaceships = new ArrayList<>();
		this.explodedSpaceships = new ArrayList<>();

		this.activeLaserbeams = new ArrayList<>();
		this.explodedLaserbeams = new ArrayList<>();

		PlayerSpaceship playerSpaceship = new PlayerSpaceship(this, this.size);
		this.player = new Player(playerSpaceship);
		this.player.setup();

		PlayerSpaceship player2Spaceship = new PlayerSpaceship(this, this.size);
		player2 = new Player(player2Spaceship);
		player2.getPlayerSpaceship().spawnPlayer2(this.size);
		this.player2.setup();

		createCars();
	}

	// creates a nice lineup of enemyspaceships
	private void createCars() {
		for (double x = 0; x < 12.0; x++) {
			for (double y = 0; y < 5.0; y++) {
				EnemySpaceship enemy = new EnemySpaceship(this,
						(0.2 * this.getSize().getWidth()) + (0.05 * this.getSize().getWidth() * x),
						(0.4 * this.getSize().getHeight()) - (0.07 * this.getSize().getHeight() * y));
				enemySpaceships.add(enemy);
			}
		}
	}

	public boolean startGame(boolean multiPlayer) {
		playMusic();
		this.multiPlayer = multiPlayer;
		if (multiPlayer) {
			player.getPlayerSpaceship().spawnPlayer1(this.size);
			player2.getPlayerSpaceship().spawnPlayer2(this.size);
		}
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

	public PlayerSpaceship getPlayer2() {
		return player2.getPlayerSpaceship();
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

	public List<LaserBeam> getActiveLaserbeams() {
		return activeLaserbeams;
	}

	public List<LaserBeam> getExplodedLaserbeams() {
		return explodedLaserbeams;
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

		Random r = new Random();
		int index = r.nextInt(15 * enemySpaceships.size());
		
		for (Spaceship spaceship : this.enemySpaceships) {
				if (index < enemySpaceships.size() && enemySpaceships.get(index).equals(spaceship)) {
					spaceship.shoot();
				}
				spaceship.move();
		}

		if (multiPlayer) {
			if (player.getPlayerSpaceship().gotHit()) {
				player.getPlayerSpaceship().setSize(new Dimension2D(0, 0));
			}
			if (player2.getPlayerSpaceship().gotHit()) {
				player2.getPlayerSpaceship().setSize(new Dimension2D(0, 0));
			}
		}

		if (!multiPlayer && !player.getPlayerSpaceship().gotHit()) {
			player.move();
		}

		if (multiPlayer && !player2.getPlayerSpaceship().gotHit()) {
			player2.move();
		}

		for (LaserBeam laserBeam : this.activeLaserbeams) {
			laserBeam.move();
		}

		// checks if game was lost or won before

		// iterates thru activeLaserbeams and looks if they hit anything

		List<LaserBeam> beamsToRemove = new ArrayList<>();
		List<EnemySpaceship> spaceshipsToRemove = new ArrayList<>();

		for (LaserBeam laserBeam : this.activeLaserbeams) {
			Point2D p1 = laserBeam.getLocation();
			Dimension2D d1 = laserBeam.getSize();

			// detects if a Laserbeam hits an enemyspaceships hitbox
			for (EnemySpaceship enemySpaceship : this.enemySpaceships) {
				if (!enemySpaceship.gotHit() && !laserBeam.hit()) {
					Point2D p2 = enemySpaceship.getLocation();
					Dimension2D d2 = enemySpaceship.getSize();

					boolean above = p1.getY() + d1.getHeight() < p2.getY();
					boolean below = p1.getY() > p2.getY() + d2.getHeight();
					boolean right = p1.getX() + d1.getWidth() < p2.getX();
					boolean left = p1.getX() > p2.getX() + d2.getWidth();

					if (!above && !below && !right && !left && laserBeam.getDirection()) {
						laserBeam.setHit();
						enemySpaceship.disappear();
						spaceshipsToRemove.add(enemySpaceship);
						score++;
						crashSoundEffectPlayer.playMusic();
						beamsToRemove.add(laserBeam);
					}
				}
			}

			try {
				for (LaserBeam laser : this.activeLaserbeams) {
					if (!laserBeam.hit() && !laserBeam.equals(laser)) {
						Point2D p2 = laser.getLocation();
						Dimension2D d2 = laser.getSize();

						boolean above = p1.getY() + d1.getHeight() < p2.getY();
						boolean below = p1.getY() > p2.getY() + d2.getHeight();
						boolean right = p1.getX() + d1.getWidth() < p2.getX();
						boolean left = p1.getX() > p2.getX() + d2.getWidth();

						if (!above && !below && !right && !left) {
							laserBeam.setHit();
							laser.setHit();
							crashSoundEffectPlayer.playMusic();
							beamsToRemove.add(laserBeam);
							beamsToRemove.add(laser);
						}
					}

					// detects if a Laserbeam hits the PlayerSpaceship hitbox
					Point2D p2 = player.getPlayerSpaceship().getLocation();
					Point2D playerp2 = player2.getPlayerSpaceship().getLocation();
					Dimension2D d2 = player.getPlayerSpaceship().getSize();
					Dimension2D player2d2 = player2.getPlayerSpaceship().getSize();

					boolean above = p1.getY() + d1.getHeight() < p2.getY();
					boolean below = p1.getY() > p2.getY() + d2.getHeight();
					boolean right = p1.getX() + d1.getWidth() < p2.getX();
					boolean left = p1.getX() > p2.getX() + d2.getWidth();

					boolean above2 = p1.getY() + d1.getHeight() < playerp2.getY();
					boolean below2 = p1.getY() > playerp2.getY() + player2d2.getHeight();
					boolean right2 = p1.getX() + d1.getWidth() < playerp2.getX();
					boolean left2 = p1.getX() > playerp2.getX() + player2d2.getWidth();

					if (!above && !below && !right && !left) {
						player.getPlayerSpaceship().disappear();
						crashSoundEffectPlayer.playMusic();
						beamsToRemove.add(laserBeam);
					}

					if (!above2 && !below2 && !right2 && !left2) {
						player2.getPlayerSpaceship().disappear();
						crashSoundEffectPlayer.playMusic();
						beamsToRemove.add(laserBeam);
					}
				}
			} catch (ConcurrentModificationException e) {
				e.printStackTrace();
			}
		}
		if (beamsToRemove.size() > 0) {
			for (LaserBeam laserBeam: beamsToRemove) {
				activeLaserbeams.remove(laserBeam);
			}
		}

		if (spaceshipsToRemove.size() > 0) {
			for (EnemySpaceship enemySpaceship: spaceshipsToRemove) {
				this.enemySpaceships.remove(enemySpaceship);
			}
		}

		if (player.getPlayerSpaceship().gotHit() && !multiPlayer)
			gameOutcome = GameOutcome.LOST;

		if (multiPlayer && player.getPlayerSpaceship().gotHit() && player2.getPlayerSpaceship().gotHit())
			gameOutcome = GameOutcome.LOST;

		if (enemySpaceships.isEmpty())
			gameOutcome = GameOutcome.WON;
	}

	public GameOutcome getGameOutcome() {
		return gameOutcome;
	}
}
