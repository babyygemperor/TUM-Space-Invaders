package tum.space.invaders.view;

import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.util.Optional;

public class GameBoardToolBar extends ToolBar {

    private final Button start;
    private final Button stop;
    private final Text score;

    public GameBoardToolBar() {
        this.start = new Button("Start");
        this.stop = new Button("Stop");
        this.score = new Text("Your score: 0");

        updateToolBarStatus(false);
        getItems().addAll(this.start, new Separator(), this.stop, new Separator(), this.score);
    }

    public void initialiseActions(GameBoardUI gameBoardUI) {
        this.start.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, """
					WELCOME TUM TO SPACE INVADERS!
					An excellent game brought to you by excellent students""", ButtonType.OK);
            alert.setTitle("Preface");
            alert.setHeaderText("2021 TUM Space Invaders");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK)
                gameBoardUI.startGame();
            else {
                gameBoardUI.stopGame();
            }
        });

        this.stop.setOnAction(event -> {
            gameBoardUI.stopGame();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you really want to stop the game?", ButtonType.YES,
                    ButtonType.NO);
            alert.setTitle("Stop Game Confirmation");
            alert.setHeaderText("");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.YES) {
                gameBoardUI.setup();
            } else {
                // continue running
                gameBoardUI.startGame();
            }
        });
    }

    public void updateToolBarStatus(boolean running) {
        this.start.setDisable(running);
        this.stop.setDisable(!running);
    }

    public void refreshText(GameBoardUI gameBoardUI) {
        this.score.setText("Your score: " + gameBoardUI.getGameBoard().getScore());
    }

}
