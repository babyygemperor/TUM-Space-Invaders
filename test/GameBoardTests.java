import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import tum.space.invaders.GameOutcome;
import tum.space.invaders.controller.GameBoard;
import tum.space.invaders.controller.music.BackgroundMusic;
import tum.space.invaders.view.GameBoardUI;

import static org.junit.jupiter.api.Assertions.*;

public class GameBoardTests {

    //1 Test for testing the properties of the GameBoard

    //Note: This setup exists because of a bug in JavaFX which says that the MediaPlayer is uninitialised. This is a bodge to it
    //It is perfectly normal for a new JavaFX window to open because of it and instantly close as well. It's a bodge
    @BeforeAll
    public static void setup() {
        final JFXPanel fxPanel = new JFXPanel();
    }

    @Test
    public void testGameBoard() {
        GameBoard gameBoard = new GameBoard(GameBoardUI.getPreferredSize());

        assertFalse(gameBoard.isRunning());

        gameBoard.setBackgroundMusicPlayer(new BackgroundMusic());
        gameBoard.startGame(false);
        assertTrue(gameBoard.isRunning());
        assertEquals(GameOutcome.RUNNING, gameBoard.getGameOutcome());

        assertEquals(gameBoard.getPlayer().getPlayerSpaceship(), gameBoard.getPlayerSpaceship());
    }

}
