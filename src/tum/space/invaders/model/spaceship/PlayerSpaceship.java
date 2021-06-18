package tum.space.invaders.model.spaceship;

import tum.space.invaders.controller.LaserBeam;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import tum.space.invaders.controller.music.CrashSound;


public class PlayerSpaceship extends Spaceship {

    private static final String FILE = "PlayerSpaceship.png";
    private final CrashSound shotfx;

    public PlayerSpaceship(Dimension2D size) {
        super(FILE, size);
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
		LaserBeam laserbeam = new LaserBeam(true, getLocation());
		shotfx.playMusic();
		return laserbeam;
	}

}
