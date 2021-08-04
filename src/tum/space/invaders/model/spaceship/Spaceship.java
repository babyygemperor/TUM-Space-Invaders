package tum.space.invaders.model.spaceship;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import tum.space.invaders.controller.LaserBeam;
import tum.space.invaders.controller.LaserBeamShooting;
import tum.space.invaders.controller.Subject;

public abstract class Spaceship extends Subject implements LaserBeamShooting {

	private String iconFilePath;
	private Point2D location;
	private boolean direction; // TRUE if Direction is RIGHT, FALSE is Direction is LEFT
	private int SPEED;
	private final Dimension2D gameboardSize;
	private boolean gotHit = false;

	private Dimension2D size = new Dimension2D(50, 25);

	public Spaceship(String iconFilePath, Dimension2D gameboardSize) {
		this.iconFilePath = iconFilePath;
		this.gameboardSize = gameboardSize;
		this.direction = true; // By default it has direction as right;
		spawnSpaceShip(this.gameboardSize);
	}

	abstract void spawnSpaceShip(Dimension2D gameboardSize);

	public abstract LaserBeam shoot();

	public abstract void move();

	public boolean gotHit() {
		return gotHit;
	}

	public void disappear() {
		this.gotHit = true;
	}

	public void setIconFilePath(String iconFilePath) {
		this.iconFilePath = iconFilePath;
	}

	public String getIconFilePath() {
		return iconFilePath;
	}

	public Point2D getLocation() {
		return location;
	}

	public void setDirection(boolean direction) {
		this.direction = direction;
	}

	protected boolean getDirection() {
		return direction;
	}

	public void setLocation(Point2D location) {
		this.location = location;
	}

	public int getSPEED() {
		return SPEED;
	}

	public void setSPEED(int SPEED) {
		this.SPEED = SPEED;
	}

	public Dimension2D getSize() {
		return this.size;
	}

	public void setSize(Dimension2D size) {
		this.size = size;
	}

	public Dimension2D getGameboardSize() {
		return gameboardSize;
	}

	public boolean getGotHit() {
		return gotHit;
	}

}
