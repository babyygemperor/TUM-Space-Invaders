package tum.space.invaders.controller.music;

public class BackgroundMusic extends Music {

    private static final String BACKGROUND_MUSIC = "Rick_Roll.wav";

    public BackgroundMusic() {
        super(BACKGROUND_MUSIC);
    }

    @Override
    public void playMusic() {
        super.playOnce();
    }

}
