package tum.space.invaders.model.spaceship;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

public class PlayerSpaceship extends Spaceship {

    private static final String FILE = "PlayerSpaceship.png";

    public PlayerSpaceship(Dimension2D size) {
        super(FILE, size);
    }

    @Override
    public void spawnSpaceShip(Dimension2D gameboardSize) {
        double x = (gameboardSize.getWidth() - getSize().getWidth()) * 0.5;
        double y = (gameboardSize.getHeight() - getSize().getHeight()) * 0.8;
        super.setLocation(new Point2D(x, y));
    }

}
