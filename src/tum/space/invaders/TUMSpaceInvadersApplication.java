package tum.space.invaders;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;
import tum.space.invaders.view.GameBoardToolBar;
import tum.space.invaders.view.GameBoardUI;

public class TUMSpaceInvadersApplication extends Application {

    @Override
    public void start(Stage stage) {
        GameBoardToolBar gameBoardToolBar = new GameBoardToolBar();
        GameBoardUI gameBoardUI = new GameBoardUI(gameBoardToolBar);
        gameBoardToolBar.initialiseActions(gameBoardUI);

        Pane layout = new Pane(gameBoardUI, gameBoardToolBar);
        Scene scene = new Scene(layout);
        scene.addEventHandler(KeyEvent.KEY_PRESSED, gameBoardUI.getKeyBoardController().getKeyPressed());

        stage.setTitle("TUM Space Invaders");
        stage.setScene(scene);
        stage.setOnCloseRequest(closeEvent -> gameBoardUI.stopGame());
        stage.show();
    }

    public static void startApp(String[] args) {
        launch(args);
    }

}
