package tum.space.invaders.model.spaceship;

import tum.space.invaders.controller.LaserBeam;

public class PlayerSpaceship extends Spaceship {

    private static final String FILE = "PlayerSpaceship.png";

    public PlayerSpaceship() {
        super(FILE);
    }

	@Override
	public LaserBeam shoot() {
		LaserBeam laserbeam = new LaserBeam(true);
		return laserbeam;
	}

}
