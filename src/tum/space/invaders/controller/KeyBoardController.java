package tum.space.invaders.controller;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import tum.space.invaders.model.spaceship.Spaceship;
import tum.space.invaders.view.GameBoardUI;


public class KeyBoardController {

    private final Spaceship controlledSpaceship;
    private final Spaceship secondSpaceship;
    private final GameBoardUI gameBoardUI;

    public KeyBoardController(GameBoardUI gameBoardUI, Spaceship controlledSpaceship, Spaceship secondSpaceship) {
        this.gameBoardUI = gameBoardUI;
        this.controlledSpaceship = controlledSpaceship;
        this.secondSpaceship = secondSpaceship;
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
                } else if (keyEvent.getCode() == KeyCode.W) {
                    if (!controlledSpaceship.gotHit()) {
                        controlledSpaceship.shoot();
                    }
                }

                if (gameBoardUI.isMultiPlayer()) {
                    if (keyEvent.getCode() == KeyCode.J) {
                        secondSpaceship.setDirection(false);
                        secondSpaceship.move();
                        secondSpaceship.update();
                    } else if (keyEvent.getCode() == KeyCode.L) {
                        secondSpaceship.setDirection(true);
                        secondSpaceship.move();
                        secondSpaceship.update();
                    } else if (keyEvent.getCode() == KeyCode.I) {
                        if (!secondSpaceship.gotHit()) {
                            secondSpaceship.shoot();
                        }
                    }
                }
            }
        }
    };

    public EventHandler<KeyEvent> getKeyPressed() {
        return keyPressed;
    }
}
