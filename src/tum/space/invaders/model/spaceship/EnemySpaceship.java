package tum.space.invaders.model.spaceship;

import tum.space.invaders.controller.LaserBeam;

public class EnemySpaceship extends Spaceship {

    private static final String FILE = "enemy.gif";

    public EnemySpaceship() {
        super(FILE);
    }

	@Override
	public LaserBeam shoot() {
		LaserBeam laserbeam = new LaserBeam(false);
		return laserbeam;
	}
    
    

}
