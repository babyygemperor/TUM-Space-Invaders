package tum.space.invaders.controller;

import javafx.geometry.Point2D;
import tum.space.invaders.controller.GameBoard;

public class LaserBeam {

	private final static int SPEED = 10;
	private String filePath = "LaserBeam.jpg";
	private boolean direction; // TRUE -> direction is UP; FALSE -> direction is DOWN
	private Point2D location;
	private final GameBoard gameBoard;

	public LaserBeam(boolean direction, Point2D location, GameBoard gameBoard) {
		this.direction = direction;
		this.location = location;
		this.gameBoard = gameBoard;
	}

	public boolean hit() {
		// TODO implement the logic for getting hti
		return false;
	}

	public void move() {
		Point2D currentPosition = getLocation();

		Point2D newPosition;

		if (getDirection()) { // If direction is UP
			newPosition = new Point2D(currentPosition.getX(),
					currentPosition.getY() + gameBoard.getSize().getHeight() * 0.005);
		} else {
			newPosition = new Point2D(currentPosition.getX() - gameBoard.getSize().getWidth() * 0.005,
					currentPosition.getY() - gameBoard.getSize().getHeight() * 0.005);
		}

		setLocation(newPosition);
	}

	public void setDirection(boolean direction) {
		this.direction = direction;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFilePath() {
		return filePath;
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

}
