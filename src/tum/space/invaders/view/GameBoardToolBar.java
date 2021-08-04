package tum.space.invaders.view;

import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Optional;

public class GameBoardToolBar extends ToolBar {

    private final Button start;
    private final Button stop;
    private final Text score;

    private boolean multiPlayer;

    public GameBoardToolBar(Stage stage) {
        this.start = new Button("Start");
        this.stop = new Button("Stop");
        this.score = new Text("Your score: 0");

        updateToolBarStatus(false);

        Label rightSpacer = new Label();
        rightSpacer.setMaxWidth(3840);
        HBox.setHgrow(rightSpacer, Priority.ALWAYS);

        getItems().addAll(this.start, new Separator(), this.stop, new Separator(), rightSpacer, new Separator(), this.score);
        prefWidthProperty().bind(stage.widthProperty());
    }

    public void initialiseActions(GameBoardUI gameBoardUI) {
        ButtonType singlePlayer = new ButtonType("Single Player", ButtonBar.ButtonData.OK_DONE);
        ButtonType multiPlayer = new ButtonType("Multiplayer Player", ButtonBar.ButtonData.OK_DONE);
        this.start.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, """
					WELCOME TUM TO SPACE INVADERS!
					An excellent game brought to you by excellent students""", singlePlayer, multiPlayer);
            alert.setTitle("Preface");
            alert.setHeaderText("2021 TUM Space Invaders");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == singlePlayer) {
                this.multiPlayer = false;
                gameBoardUI.startGame(false);
            } else if (result.isPresent() && result.get() == multiPlayer) {
                this.multiPlayer = true;
                gameBoardUI.startGame(true);
            }
            else {
                this.multiPlayer = false;
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
                gameBoardUI.startGame(this.multiPlayer);
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
