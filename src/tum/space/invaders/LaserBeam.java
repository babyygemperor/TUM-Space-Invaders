package tum.space.invaders;

public class LaserBeam {

    private final static int SPEED = 10;
    private String iconDirectory;
    private boolean direction;      //TRUE -> direction is UP; FALSE -> direction is DOWN

    public LaserBeam() {
        //TODO init stuff
    }

    public void hit() {
        //TODO implement the logic for getting hti
    }


    public void setDirection(boolean direction) {
        this.direction = direction;
    }

    public void setIconDirectory(String iconDirectory) {
        this.iconDirectory = iconDirectory;
    }

    public String getIconDirectory() {
        return iconDirectory;
    }

    public boolean getDirection() {
        return this.direction;
    }

}
