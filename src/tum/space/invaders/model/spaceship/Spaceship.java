package tum.space.invaders.model.spaceship;

import tum.space.invaders.controller.LaserBeam;

import java.awt.geom.Point2D;

public abstract class Spaceship {


    private String iconDirectory;
    private Point2D location;
    private boolean direction;      //TRUE if Direction is RIGHT, FALSE is Direction is LEFT
    private int SPEED;

    public Spaceship() {
        this.direction = true;      //By default it has direction as right;
    }


    public LaserBeam shoot() {
        //TODO implement the laser beam method
        return new LaserBeam();
    }

    public void move() {
        //TODO implement the movement
    }

    public boolean gotHit() {
        //TODO implement getting hit my a laser beam
        return false;
    }

    public void disappear() {
        //TODO implement the meme: you didn't see anything
    }

    public void setIconDirectory(String iconDirectory) {
        this.iconDirectory = iconDirectory;
    }

    public String getIconDirectory() {
        return iconDirectory;
    }

    public Point2D getLocation() {
        return location;
    }

    public void setDirection(boolean direction) {
        this.direction = direction;
    }

    public void setLocation(Point2D location) {
        this.location = location;
    }

    public int getSPEED() {
        return SPEED;
    }

    public void setSPEED(int SPEED) {
        this.SPEED = SPEED;
    }

}
