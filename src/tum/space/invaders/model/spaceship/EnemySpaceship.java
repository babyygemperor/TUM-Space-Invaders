package tum.space.invaders.model.spaceship;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import tum.space.invaders.controller.GameBoard;
import tum.space.invaders.controller.LaserBeam;
import tum.space.invaders.controller.music.CrashSound;


public class EnemySpaceship extends Spaceship {

	private static final String FILE = "enemy.gif";
	private final CrashSound shotfx;
	private final GameBoard gameBoard;

	public EnemySpaceship(GameBoard gameBoard, double x, double y) {
		super(FILE, gameBoard.getSize());
		this.gameBoard = gameBoard;
		this.setLocation(new Point2D(x, y));
		shotfx = new CrashSound();

	}

	@Override
	void spawnSpaceShip(Dimension2D gameboardSize) {
		double x = (gameboardSize.getWidth() - getSize().getWidth()) * 0.5;
		double y = (gameboardSize.getHeight() - getSize().getHeight()) * 0.2;
		super.setLocation(new Point2D(x, y));
	}

	@Override
	public LaserBeam shoot() {
//		return new LaserBeam(false, getLocation(), gameBoard);
		LaserBeam laserbeam = new LaserBeam(false, getLocation().add(getSize().getWidth() / 2, -getSize().getHeight()),
				gameBoard);
		gameBoard.getActiveLaserbeams().add(laserbeam);
		shotfx.playMusic();
		return laserbeam;
	}

	@Override
	public void move() {
		// TODO (not needed (yet)) if EnemySpaceships need to move (not needed (yet))
//		Point2D currentPosition = getLocation();
//
//		Point2D newPosition;
//
//		if (getDirection()) { // If direction is right
//			newPosition = new Point2D(currentPosition.getX() + getGameboardSize().getWidth() * 0.005,
//					currentPosition.getY());
//		} else {
//			newPosition = new Point2D(currentPosition.getX() - getGameboardSize().getWidth() * 0.005,
//					currentPosition.getY());
//		}
//		if(currentPosition) {
//			
//		}
//		setLocation(newPosition);
	}

}
