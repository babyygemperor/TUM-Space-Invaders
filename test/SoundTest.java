import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import tum.space.invaders.controller.music.BackgroundMusic;
import tum.space.invaders.controller.music.CrashSound;

import static org.junit.jupiter.api.Assertions.*;

public class SoundTest {

    //2 Tests for background music check

    //Note: This setup exists because of a bug in JavaFX which says that the MediaPlayer is uninitialised. This is a bodge to it
    //It is perfectly normal for a new JavaFX window to open because of it and instantly close as well. It's a bodge
    @BeforeAll
    public static void setup() {
        final JFXPanel fxPanel = new JFXPanel();
    }

    private void waitFor(int milliseconds) {
        long startTime = System.currentTimeMillis();
        long currentTime;

        do {
            currentTime = System.currentTimeMillis();
        } while (currentTime < startTime + milliseconds);
    }

    @Test
    public void playBackgroundMusicTest() {
        BackgroundMusic backgroundMusicPlayer = new BackgroundMusic();
        backgroundMusicPlayer.playMusic();

        assertEquals("background.mp3", backgroundMusicPlayer.getMusicFile());

        waitFor(100);

        assertTrue(backgroundMusicPlayer.isPlayingMusic());

        backgroundMusicPlayer.stopMusic();

        waitFor(100);

        assertFalse(backgroundMusicPlayer.isPlayingMusic());
    }

    @Test
    public void playCrashSoundEffectTest() {
        CrashSound crashSound = new CrashSound();
        crashSound.playMusic();

        assertEquals("shoot.mp3", crashSound.getMusicFile());

        waitFor(100);

        assertTrue(crashSound.isPlayingMusic());

        crashSound.stopMusic();

        waitFor(100);

        assertFalse(crashSound.isPlayingMusic());
    }
}