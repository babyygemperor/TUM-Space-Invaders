package tum.space.invaders.controller;

import tum.space.invaders.controller.music.CrashSound;

public class LaserBeam {

    private final static int SPEED = 10;
    private String filePath = "LaserBeam.jpg";
    private boolean direction;      //TRUE -> direction is UP; FALSE -> direction is DOWN

    public LaserBeam(boolean direction) {
        this.direction = direction;
    }

    public void hit() {
        //TODO implement the logic for getting hti
    }


    public void setDirection(boolean direction) {
        this.direction = direction;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public boolean getDirection() {
        return this.direction;
    }

}
