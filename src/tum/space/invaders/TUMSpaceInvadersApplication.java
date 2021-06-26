package tum.space.invaders;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import tum.space.invaders.view.GameBoardToolBar;
import tum.space.invaders.view.GameBoardUI;

public class TUMSpaceInvadersApplication extends Application {

    @Override
    public void start(Stage stage) {
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getBounds();

        stage.setX(bounds.getMinX());
        stage.setX(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());
        //stage.setMaximized(true);

        GameBoardToolBar gameBoardToolBar = new GameBoardToolBar(stage);
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
