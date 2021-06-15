package tum.space.invaders.model.spaceship;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import tum.space.invaders.controller.LaserBeam;
import tum.space.invaders.controller.Subject;
import tum.space.invaders.controller.music.CrashSound;


public abstract class Spaceship extends Subject {


    private String iconFilePath;
    private Point2D location;
    private boolean direction;      //TRUE if Direction is RIGHT, FALSE is Direction is LEFT
    private int SPEED;

    private final Dimension2D size = new Dimension2D(50, 25);

    public Spaceship(String iconFilePath, Dimension2D gameboardSize) {
        this.iconFilePath = iconFilePath;
        this.direction = true;      //By default it has direction as right;
        spawnSpaceShip(gameboardSize);
    }

    abstract void spawnSpaceShip(Dimension2D gameboardSize);


    public abstract LaserBeam shoot();

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

    public void setIconFilePath(String iconFilePath) {
        this.iconFilePath = iconFilePath;
    }

    public String getIconFilePath() {
        return iconFilePath;
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

    public Dimension2D getSize() {
        return this.size;
    }
}
