package tum.space.invaders.controller;

import javafx.geometry.Point2D;

public class LaserBeam {

    private final static int SPEED = 10;
    private String filePath = "LaserBeam.jpg";
    private boolean direction;      //TRUE -> direction is UP; FALSE -> direction is DOWN
    private Point2D location;

    public LaserBeam(boolean direction, Point2D location) {
        this.direction = direction;
        this.location = location;
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

    public Point2D getLocation() {
        return location;
    }

    public void setLocation(Point2D location) {
        this.location = location;
    }

}
