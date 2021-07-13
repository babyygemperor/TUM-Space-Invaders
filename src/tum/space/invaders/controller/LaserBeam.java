package tum.space.invaders.controller;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import tum.space.invaders.controller.GameBoard;

public class LaserBeam {

	private final static int SPEED = 10;
	private static final String FILE = "laser.png";
	private boolean direction; // TRUE -> direction is UP; FALSE -> direction is DOWN
	private Point2D location;
	private final GameBoard gameBoard;

	private boolean hit;

	private Dimension2D size = new Dimension2D(5, 20);

	public LaserBeam(boolean direction, Point2D location, GameBoard gameBoard) {
		this.direction = direction;
		this.location = location;
		this.gameBoard = gameBoard;
	}

	public boolean hit() {
		return hit;
	}

	public void move() {
		Point2D currentPosition = getLocation();

		Point2D newPosition;

		if (getDirection()) { // If direction is UP
			newPosition = new Point2D(currentPosition.getX(),
					currentPosition.getY() - gameBoard.getSize().getHeight() * 0.03);
		} else {
			newPosition = new Point2D(currentPosition.getX(),
					currentPosition.getY() + gameBoard.getSize().getHeight() * 0.01);
		}

		setLocation(newPosition);
	}

	public void setDirection(boolean direction) {
		this.direction = direction;
	}

	public String getFilePath() {
		return FILE;
	}

	public boolean getDirection() {
		return this.direction;
	}

	public Point2D getLocation() {
		return location;
	}

	public void setLocation(Point2D location) {
		this.location = location;
	}

	public Dimension2D getSize() {
		return size;
	}

	public void disappear() {
		this.size = new Dimension2D(0, 0);
	}

	public void setHit() {
		this.hit = true;
	}

}
