package tum.space.invaders.view;

import javafx.application.Platform;
import javafx.geometry.Dimension2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import tum.space.invaders.GameOutcome;
import tum.space.invaders.controller.GameBoard;
import tum.space.invaders.controller.KeyBoardController;
import tum.space.invaders.controller.Observer;
import tum.space.invaders.controller.music.BackgroundMusic;
import tum.space.invaders.model.spaceship.Spaceship;

import java.awt.geom.Point2D;
import java.net.URL;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class GameBoardUI extends Canvas implements Observer {

    private static final Color BACKGROUND_COLOR = Color.BLACK;

    private static final int UPDATE_PERIOD = 33;
    private static final int DEFAULT_WIDTH = 500;
    private static final int DEFAULT_HEIGHT = 300;
    private static final Dimension2D DEFAULT_SIZE = new Dimension2D(DEFAULT_WIDTH, DEFAULT_HEIGHT);

    public static Dimension2D getPreferredSize() {
        return DEFAULT_SIZE;
    }

    private GameBoard gameBoard;
    private final GameBoardToolBar gameBoardToolBar;

    private KeyBoardController keyBoardController;

    private HashMap<String, Image> imageCache;

    private Timer gameTimer;


    public GameBoardUI(GameBoardToolBar gameBoardToolBar) {
        this.gameBoardToolBar = gameBoardToolBar;
        setup();
    }

    public void setup() {
        setupGameBoard();
        setupImageCache();
        this.gameBoardToolBar.updateToolBarStatus(true);
        paint();
    }


    private void setupImageCache() {
        this.imageCache = new HashMap<>();
        for (Spaceship spaceship: this.gameBoard.getEnemySpaceships()) {
            this.imageCache.computeIfAbsent(spaceship.getIconFilePath(), this::getImage);
        }
        String playerImageLocation = this.gameBoard.getPlayerSpaceship().getIconFilePath();
        this.imageCache.put(playerImageLocation, getImage(playerImageLocation));
    }

    private Image getImage(String playerImageLocation) {
        URL carImageURL = getClass().getClassLoader().getResource(playerImageLocation);

        if (carImageURL == null) {
            throw new IllegalArgumentException("Are you sure that your resources file has everything?");
        }

        return new Image(carImageURL.toExternalForm());
    }

    private void setupGameBoard() {
        Dimension2D size = getPreferredSize();
        this.gameBoard = new GameBoard(size);
        this.gameBoard.setBackgroundMusicPlayer(new BackgroundMusic());
        widthProperty().setValue(size.getWidth());
        heightProperty().setValue(size.getHeight());
        this.keyBoardController = new KeyBoardController(this, this.gameBoard.getPlayerSpaceship());
    }

    public void moveSpaceship() {
        //TODO move the spaceships (I know, very informative)
    }

    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public KeyBoardController getKeyBoardController() {
        return keyBoardController;
    }

    public void stopGame() {
        if (this.gameBoard.isRunning()) {
            this.gameBoard.stopGame();
        }
    }

    public void startGame() {
        if (!this.gameBoard.isRunning()) {
            this.gameBoard.startGame();
            this.gameBoardToolBar.updateToolBarStatus(true);
            startTimer();
            paint();
        }
    }

    private void updateGame() {
        if (gameBoard.isRunning()) {
            // updates car positions and re-renders graphics
            this.gameBoard.update();

            this.gameBoardToolBar.refreshText(this);
            // when this.gameBoard.getOutcome() is OPEN, do nothing
            if (this.gameBoard.getGameOutcome() == GameOutcome.LOST) {
                showAsyncAlert("Oh.. you lost.");
                this.stopGame();
            } else if (this.gameBoard.getGameOutcome() == GameOutcome.WON) {
                showAsyncAlert("Congratulations! You won!!");
                this.stopGame();
            }
            paint();
        }
    }

    private void paint() {
        getGraphicsContext2D().setFill(BACKGROUND_COLOR);
        getGraphicsContext2D().fillRect(0, 0, getWidth(), getHeight());

        for (Spaceship enemies: this.gameBoard.getEnemySpaceships()) {
            paintSpaceship(enemies);
        }

        paintSpaceship(this.gameBoard.getPlayerSpaceship());

    }

    private void paintSpaceship(Spaceship spaceships) {
        Point2D position = spaceships.getLocation();

        getGraphicsContext2D().drawImage(this.imageCache.get(spaceships.getIconFilePath()),
                position.getX(), position.getY(), spaceships.getSize().getWidth(), spaceships.getSize().getHeight());
    }

    private void showAsyncAlert(String s) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(s);
            alert.showAndWait();
            this.setup();
        });
    }

    private void startTimer() {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                updateGame();
            }
        };
        if (this.gameTimer != null) {
            this.gameTimer.cancel();
        }
        this.gameTimer = new Timer();
        this.gameTimer.scheduleAtFixedRate(timerTask, 33, 33);
    }

    @Override
    public void update() {
        updateGame();
    }

}
