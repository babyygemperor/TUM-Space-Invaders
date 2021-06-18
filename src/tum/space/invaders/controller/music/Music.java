package tum.space.invaders.controller.music;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.net.URL;

public abstract class Music {

    private String musicFile;

    private final MediaPlayer musicPlayer;

    public Music(String musicDirectory) {
        this.musicFile = musicDirectory;
        this.musicPlayer = new MediaPlayer(new Media(convertNameToUrl(musicFile)));
    }

    public void playMusic() {
        if (isPlayingMusic()) {
            return;
        }

        this.musicPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        this.musicPlayer.play();
    }

    public void playOnce() {
        if (isPlayingMusic()) {
            return;
        }
        //this.musicPlayer.setCycleCount(1);
        this.musicPlayer.play();
    }

    public void reset() {
        this.musicPlayer.seek(Duration.ZERO);
    }

    public void stopMusic() {
        if (isPlayingMusic()) {
            this.musicPlayer.stop();
        }
    }

    public boolean isPlayingMusic() {
        return MediaPlayer.Status.PLAYING.equals(this.musicPlayer.getStatus());
    }

    public String getMusicFile() {
        return musicFile;
    }

    public void setMusicFile(String musicFile) {
        this.musicFile = musicFile;
    }

    private String convertNameToUrl(String fileName) {
        URL musicSourceUrl = getClass().getClassLoader().getResource(fileName);
        if (musicSourceUrl == null) {
            throw new IllegalArgumentException(
                    "Please ensure that your resources folder contains the appropriate files for this exercise.");
        }
        return musicSourceUrl.toExternalForm();
    }

}
