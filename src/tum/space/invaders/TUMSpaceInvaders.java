package tum.space.invaders;

import java.util.Arrays;

public final class TUMSpaceInvaders {

    /**
     * Hey Can,
     * We've implemented the very basics of stuff need to get this game to the very basic of running
     * These are the FRs we've implemented in this task
     * FR1: 1) The player should be able to shoot and move the spaceship
     * FR2: 3) The score of the player should be displayed
     * FR3: 4) Shooting sound must be played for every shot fired
     * FR4: 5) The player should be able to start and stop the game
     * FR5: 6) A background music should be played during the entire game
     *
     * To move the spaceship, the player has to presses either A/Left key or D/Right key to go in the respective directions (no implemented yet)
     * To shoot a laser beam, the player has to press K
     *
     * Apart from that this game is also very immersive since it runs on the entire display (Minimum supported is 1280x720, max supported is 3840z2160)
     * We don't believe that anyone on a 5K monitor would be playing this game
     *
     * Apart from that we've implemented the Observer pattern here to update the location of the cars every frame
     * effectively converting it to our own advantage, the UML diagram for it is at T03E02
     */

    private TUMSpaceInvaders() {

    }

    public static void main(String[] args) {
        TUMSpaceInvadersApplication.startApp(args);
    }

}
