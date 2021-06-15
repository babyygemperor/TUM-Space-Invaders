package tum.space.invaders.model.spaceship;

import tum.space.invaders.controller.LaserBeam;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;


public class EnemySpaceship extends Spaceship {

    private static final String FILE = "enemy.gif";

    public EnemySpaceship(Dimension2D size) {
        super(FILE, size);
    }

    @Override
    void spawnSpaceShip(Dimension2D gameboardSize) {
        double x = (gameboardSize.getWidth() - getSize().getWidth()) * 0.5;
        double y = (gameboardSize.getHeight() - getSize().getHeight()) * 0.2;
        super.setLocation(new Point2D(x, y));
    }

	@Override
	public LaserBeam shoot() {
		LaserBeam laserbeam = new LaserBeam(false);
		return laserbeam;
	}
    
    

}
