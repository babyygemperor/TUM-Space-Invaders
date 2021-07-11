package tum.space.invaders.model.spaceship;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import tum.space.invaders.controller.GameBoard;
import tum.space.invaders.controller.LaserBeam;

public class EnemySpaceship extends Spaceship {

	private static final String FILE = "enemy.gif";
	private final GameBoard gameBoard;

	public EnemySpaceship(GameBoard gameBoard) {
		super(FILE, gameBoard.getSize());
		this.gameBoard = gameBoard;
	}

	@Override
	void spawnSpaceShip(Dimension2D gameboardSize) {
		double x = (gameboardSize.getWidth() - getSize().getWidth()) * 0.5;
		double y = (gameboardSize.getHeight() - getSize().getHeight()) * 0.2;
		super.setLocation(new Point2D(x, y));
	}

	@Override
	public LaserBeam shoot() {
		return new LaserBeam(false, getLocation(), gameBoard);
	}

	@Override
	public void move() {
		// TODO??
	}

}
