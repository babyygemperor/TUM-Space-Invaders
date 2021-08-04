package tum.space.invaders;

public final class TUMSpaceInvaders {

    /**
     * Hey Can,
     * We've implemented everything that we had to. The entire game works
     * Both single player and multi player.
     * For some reason there is a bug, which doesn't let the game be restarted within, but instead the app has to be relaunched
     *
     * Other than that there are basic JavaFX bugs, i.e
     * 1) If a laser beam is close enough to a spaceship, it counts as a hit, even if it is not touching (Same bug as from Bumpers)
     * 2) You can only register 1 keystroke at a time, instead of 2 keystrokes
     * EDIT: FIXED 0) It just crashes sometimes lol (Edit: We fixed that bug as well)
     *
     * Here are the controls:
     * For player 1:
     * A -> move left
     * D -> move right
     * W -> shoot
     *
     * For player 2:
     * J -> move left
     * L -> move right
     * I -> shoot
     */

    private TUMSpaceInvaders() {

    }

    public static void main(String[] args) {
        TUMSpaceInvadersApplication.startApp(args);
    }

}
