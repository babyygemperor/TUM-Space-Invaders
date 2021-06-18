package tum.space.invaders.controller;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import tum.space.invaders.model.spaceship.Spaceship;
import tum.space.invaders.view.GameBoardUI;


public class KeyBoardController {

    private final Spaceship controlledSpaceship;
    private final GameBoardUI gameBoardUI;

    public KeyBoardController(GameBoardUI gameBoardUI, Spaceship controlledSpaceship) {
        this.gameBoardUI = gameBoardUI;
        this.controlledSpaceship = controlledSpaceship;
    }

    private final EventHandler<KeyEvent> keyPressed = new EventHandler<>() {

        @Override
        public void handle(KeyEvent keyEvent) {
            if (keyEvent.getCode() == KeyCode.LEFT || keyEvent.getCode() == KeyCode.A) {
                controlledSpaceship.setDirection(false);
            } else if (keyEvent.getCode() == KeyCode.RIGHT || keyEvent.getCode() == KeyCode.D) {
                controlledSpaceship.setDirection(true);
            } else if (keyEvent.getCode() == KeyCode.K) {
                if (gameBoardUI.getGameBoard().isRunning()) {
                    controlledSpaceship.shoot();
                    gameBoardUI.getGameBoard().setScore(gameBoardUI.getGameBoard().getScore() + 1);
                }
            }
        }
    };

    public EventHandler<KeyEvent> getKeyPressed() {
        return keyPressed;
    }
}
