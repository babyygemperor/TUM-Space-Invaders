package tum.space.invaders.controller;

import javafx.geometry.Point2D;
import tum.space.invaders.model.spaceship.PlayerSpaceship;
import tum.space.invaders.model.spaceship.Spaceship;

public class Player {

    private PlayerSpaceship playerSpaceship;

    public Player(PlayerSpaceship spaceship) {
        this.playerSpaceship = spaceship;
    }

    public void setPlayerSpaceship(PlayerSpaceship playerSpaceship) {
        this.playerSpaceship = playerSpaceship;
    }

    public PlayerSpaceship getPlayerSpaceship() {
        return playerSpaceship;
    }

    public void setup() {
        playerSpaceship.setLocation(new Point2D(1, 4));
    }

}
