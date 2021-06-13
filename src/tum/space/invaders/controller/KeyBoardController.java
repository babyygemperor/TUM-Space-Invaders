package tum.space.invaders.controller;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import tum.space.invaders.model.spaceship.Spaceship;
import tum.space.invaders.view.GameBoardUI;

import java.awt.geom.Point2D;

public class KeyBoardController {

    private final Spaceship controlledSpaceship;

    public KeyBoardController(GameBoardUI gameBoardUI, Spaceship controlledSpaceship) {
        this.controlledSpaceship = controlledSpaceship;
        gameBoardUI.addEventHandler(KeyEvent.KEY_PRESSED, this::keyPressed);
    }

    public void keyPressed(KeyEvent keyEvent) {
        Point2D spaceshipPosition = controlledSpaceship.getLocation();
        KeyCode keyPressed = keyEvent.getCode();

        if (keyPressed == KeyCode.LEFT || keyPressed == KeyCode.A) {
            controlledSpaceship.setDirection(false);
        } else if (keyPressed == KeyCode.RIGHT || keyPressed == KeyCode.D) {
            controlledSpaceship.setDirection(true);
        } else if (keyPressed == KeyCode.SPACE) {
            controlledSpaceship.shoot();
        }
    }

}
