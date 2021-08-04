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
            if (gameBoardUI.getGameBoard().isRunning()) {
                if (keyEvent.getCode() == KeyCode.A) {
                    controlledSpaceship.setDirection(false);
                    controlledSpaceship.move();
                    controlledSpaceship.update();
                } else if (keyEvent.getCode() == KeyCode.D) {
                    controlledSpaceship.setDirection(true);
                    controlledSpaceship.move();
                    controlledSpaceship.update();
                } else if (keyEvent.getCode() == KeyCode.K) {
                    controlledSpaceship.shoot();
<<<<<<< HEAD
                    gameBoardUI.getGameBoard().setScore(gameBoardUI.getGameBoard().getScore() + 1);
=======
>>>>>>> master-holder
                }
            }
        }
    };

    public EventHandler<KeyEvent> getKeyPressed() {
        return keyPressed;
    }
}
