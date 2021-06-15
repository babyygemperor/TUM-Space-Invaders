package tum.space.invaders.controller;

import tum.space.invaders.model.spaceship.PlayerSpaceship;

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
        //Genuinely don't know why I added it
    }

}
