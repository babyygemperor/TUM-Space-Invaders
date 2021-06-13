package tum.space.invaders.controller.music;

public class CrashSound extends Music {

    private static final String LASER_BEAM = "shoot.mp3";

    public CrashSound() {
        super(LASER_BEAM);
    }

    @Override
    public void playMusic() {
        super.playOnce();
    }

}
