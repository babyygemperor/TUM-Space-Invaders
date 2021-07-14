package tum.space.invaders.view;

import javafx.application.Platform;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import tum.space.invaders.GameOutcome;
import tum.space.invaders.controller.GameBoard;
import tum.space.invaders.controller.KeyBoardController;
import tum.space.invaders.controller.LaserBeam;
import tum.space.invaders.controller.Observer;
import tum.space.invaders.controller.music.BackgroundMusic;
import tum.space.invaders.model.spaceship.Spaceship;

import java.net.URL;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class GameBoardUI extends Canvas implements Observer {

	private static final Color BACKGROUND_COLOR = Color.BLACK;

	private static final int UPDATE_PERIOD = 33;
	private static final int DEFAULT_WIDTH = 1280;
	private static final int DEFAULT_HEIGHT = 720;
	private static final Dimension2D DEFAULT_SIZE = new Dimension2D(DEFAULT_WIDTH, DEFAULT_HEIGHT);

	public static Dimension2D getPreferredSize() {
		return DEFAULT_SIZE;
	}

	private GameBoard gameBoard;
	private final GameBoardToolBar gameBoardToolBar;

	private KeyBoardController keyBoardController;

	private HashMap<String, Image> imageCache;

	private Timer gameTimer;

	boolean multiPlayer = false;

	public GameBoardUI(GameBoardToolBar gameBoardToolBar) {
		this.gameBoardToolBar = gameBoardToolBar;
		setup();
	}

	public void setup() {
		setupGameBoard();
		setupImageCache();
		this.gameBoardToolBar.updateToolBarStatus(false);
		paint();
	}

	private void setupImageCache() {
		this.imageCache = new HashMap<>();
		for (Spaceship spaceship : this.gameBoard.getEnemySpaceships()) {
			this.imageCache.computeIfAbsent(spaceship.getIconFilePath(), this::getImage);
		}
		for (LaserBeam laser : this.gameBoard.getActiveLaserbeams()) {
			this.imageCache.computeIfAbsent(laser.getFilePath(), this::getImage);

		}
		LaserBeam temp = new LaserBeam(false, new Point2D(0.0, 0.0), this.gameBoard);
		String laserImageLocation = temp.getFilePath();
		String playerImageLocation = this.gameBoard.getPlayerSpaceship().getIconFilePath();
		this.imageCache.put(playerImageLocation, getImage(playerImageLocation));
		this.imageCache.put(laserImageLocation, getImage(laserImageLocation));
	}

	private Image getImage(String playerImageLocation) {
		URL carImageURL = getClass().getClassLoader().getResource(playerImageLocation);

		if (carImageURL == null) {
			throw new IllegalArgumentException("Are you sure that your resources file has everything?");
		}

		return new Image(carImageURL.toExternalForm());
	}

	private void setupGameBoard() {
		Screen screen = Screen.getPrimary();
		Rectangle2D bounds = screen.getBounds();
		Dimension2D size = new Dimension2D(bounds.getWidth(), bounds.getHeight());
		// Dimension2D size =
		this.gameBoard = new GameBoard(size);
		this.gameBoard.setBackgroundMusicPlayer(new BackgroundMusic());
		widthProperty().setValue(size.getWidth());
		heightProperty().setValue(size.getHeight());
		this.keyBoardController = new KeyBoardController(this, this.gameBoard.getPlayerSpaceship(), this.gameBoard.getPlayer2());

		this.gameBoard.getPlayerSpaceship().addObserver(this);
		this.gameBoard.getEnemySpaceships().forEach(spaceship -> spaceship.addObserver(this));
	}

	public void moveSpaceship() {
		// TODO (not needed (yet))
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
			this.multiPlayer = false;
			this.gameBoard.stopGame();
			this.gameBoardToolBar.updateToolBarStatus(false);
			this.gameTimer.cancel();
		}
	}

	public void startGame(boolean multiPlayer) {
		if (!this.gameBoard.isRunning()) {
			this.multiPlayer = multiPlayer;
			this.gameBoard.startGame(multiPlayer);
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

		for (Spaceship enemies : this.gameBoard.getEnemySpaceships()) {
			paintSpaceship(enemies);
		}

		for (LaserBeam laserBeam : this.gameBoard.getActiveLaserbeams()) {
			paintLaserBeam(laserBeam);
		}

		paintSpaceship(this.gameBoard.getPlayerSpaceship());

		if (multiPlayer) {
			paintSpaceship(this.gameBoard.getPlayer2());
		}

	}

	private void paintSpaceship(Spaceship spaceships) {
		Point2D position = spaceships.getLocation();

		getGraphicsContext2D().drawImage(this.imageCache.get(spaceships.getIconFilePath()), position.getX(),
				position.getY(), spaceships.getSize().getWidth(), spaceships.getSize().getHeight());
	}

	private void paintLaserBeam(LaserBeam laserBeam) {
		Point2D position = laserBeam.getLocation();

		getGraphicsContext2D().drawImage(this.imageCache.get(laserBeam.getFilePath()), position.getX(), position.getY(),
				laserBeam.getSize().getWidth(), laserBeam.getSize().getHeight());
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
		this.gameTimer.scheduleAtFixedRate(timerTask, UPDATE_PERIOD, UPDATE_PERIOD);
	}

	@Override
	public void update() {
		updateGame();
	}

	public boolean isMultiPlayer() {
		return multiPlayer;
	}
}
