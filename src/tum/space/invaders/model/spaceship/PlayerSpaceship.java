package tum.space.invaders.model.spaceship;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import tum.space.invaders.controller.GameBoard;
import tum.space.invaders.controller.LaserBeam;
import tum.space.invaders.controller.music.CrashSound;

public class PlayerSpaceship extends Spaceship {

	private static final String FILE = "PlayerSpaceship.png";
	private final CrashSound shotfx;
	private final GameBoard gameBoard;

	public PlayerSpaceship(GameBoard gameBoard, Dimension2D size) {
		super(FILE, size);
		this.gameBoard = gameBoard;
		setSize(new Dimension2D(75, 38));
		shotfx = new CrashSound();
	}

	@Override
	public void spawnSpaceShip(Dimension2D gameboardSize) {
		double x = (gameboardSize.getWidth() - getSize().getWidth()) * 0.49;
		double y = (gameboardSize.getHeight() - getSize().getHeight()) * 0.8;
		super.setLocation(new Point2D(x, y));
	}

	@Override
	public LaserBeam shoot() {
		LaserBeam laserbeam = new LaserBeam(true, getLocation().add(getSize().getWidth() / 2, -getSize().getHeight()),
				gameBoard);
		gameBoard.getActiveLaserbeams().add(laserbeam);
		shotfx.playMusic();
		return laserbeam;
	}

	@Override
	public void move() {
		Point2D currentPosition = getLocation();

		Point2D newPosition;

		if (getDirection()) { // If direction is right
			newPosition = new Point2D(currentPosition.getX() + getGameboardSize().getWidth() * 0.005,
					currentPosition.getY());
		} else {
			newPosition = new Point2D(currentPosition.getX() - getGameboardSize().getWidth() * 0.005,
					currentPosition.getY());
		}

		setLocation(newPosition);
	}

}
