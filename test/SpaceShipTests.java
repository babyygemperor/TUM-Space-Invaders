import javafx.embed.swing.JFXPanel;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import tum.space.invaders.controller.GameBoard;
import tum.space.invaders.controller.Player;
import tum.space.invaders.model.spaceship.EnemySpaceship;
import tum.space.invaders.model.spaceship.PlayerSpaceship;
import tum.space.invaders.view.GameBoardUI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class SpaceShipTests {

    //6 tests for spaceships

    //Note: This setup exists because of a bug in JavaFX which says that the MediaPlayer is uninitialised. This is a bodge to it
    //It is perfectly normal for a new JavaFX window to open because of it and instantly close as well. It's a bodge
    @BeforeAll
    public static void setup() {
        final JFXPanel fxPanel = new JFXPanel();
    }

    @Test
    public void testPlayerSpaceshipSize() {
        PlayerSpaceship playerSpaceship = new PlayerSpaceship(new GameBoard(GameBoardUI.getPreferredSize()), GameBoardUI.getPreferredSize());

        assertEquals(new Dimension2D(75, 38), playerSpaceship.getSize());
        assertEquals(new Dimension2D(1280, 720), playerSpaceship.getGameboardSize());
    }

    @Test
    public void testPlayerSpaceshipMetadata() {
        PlayerSpaceship playerSpaceship = new PlayerSpaceship(new GameBoard(GameBoardUI.getPreferredSize()), GameBoardUI.getPreferredSize());

        assertEquals("PlayerSpaceship.png", playerSpaceship.getIconFilePath());
        assertEquals(0, playerSpaceship.getSPEED());
        assertFalse(playerSpaceship.gotHit());
        assertEquals(new Point2D(602.7, 556), playerSpaceship.getLocation());
    }

    @Test
    public void testPlayerProperties() {
        PlayerSpaceship playerSpaceship = new PlayerSpaceship(new GameBoard(GameBoardUI.getPreferredSize()), GameBoardUI.getPreferredSize());
        Player player = new Player(playerSpaceship);

        assertEquals(playerSpaceship, player.getPlayerSpaceship());
        assertEquals("PlayerSpaceship.png", player.getPlayerSpaceship().getIconFilePath());
        assertEquals(new Dimension2D(75, 38), player.getPlayerSpaceship().getSize());
        assertEquals(new Dimension2D(1280, 720), player.getPlayerSpaceship().getGameboardSize());
        assertEquals(0, player.getPlayerSpaceship().getSPEED());
        assertFalse(player.getPlayerSpaceship().gotHit());
        assertEquals(new Point2D(602.7, 556), player.getPlayerSpaceship().getLocation());
    }

    @Test
    public void testEnemySpaceship() {
        EnemySpaceship enemySpaceships = new EnemySpaceship(new GameBoard(GameBoardUI.getPreferredSize()), 0, 0);

        assertEquals(new Dimension2D(50, 25), enemySpaceships.getSize());
        assertEquals(new Dimension2D(1280, 720), enemySpaceships.getGameboardSize());
    }

    @Test
    public void testEnemySpaceshipMetadata() {
        EnemySpaceship enemySpaceships = new EnemySpaceship(new GameBoard(GameBoardUI.getPreferredSize()), 0, 0);

        assertEquals("enemy.gif", enemySpaceships.getIconFilePath());
        assertEquals(0, enemySpaceships.getSPEED());
        assertFalse(enemySpaceships.gotHit());
        assertEquals(new Point2D(615, 139), enemySpaceships.getLocation());
    }

    @Test
    public void movementTest() {
        Dimension2D gameBoardSize = GameBoardUI.getPreferredSize();
        PlayerSpaceship playerSpaceship = new PlayerSpaceship(new GameBoard(GameBoardUI.getPreferredSize()), GameBoardUI.getPreferredSize());
        double newPosition;

        assertEquals(new Point2D(602.7, 556), playerSpaceship.getLocation());
        playerSpaceship.setDirection(true);
        playerSpaceship.move();
        assertEquals(new Point2D(609.1, 556), playerSpaceship.getLocation());
        playerSpaceship.move();
        assertEquals(new Point2D(615.5, 556), playerSpaceship.getLocation());
        playerSpaceship.move();
        assertEquals(new Point2D(621.9, 556), playerSpaceship.getLocation());

        playerSpaceship.setDirection(false);
        playerSpaceship.move();
        assertEquals(new Point2D(615.5, 556), playerSpaceship.getLocation());
        playerSpaceship.move();
        assertEquals(new Point2D(609.1, 556), playerSpaceship.getLocation());
        playerSpaceship.move();
        assertEquals(new Point2D(602.7, 556), playerSpaceship.getLocation());

        for (int i = 0; i < 10; i++) {
            newPosition = playerSpaceship.getLocation().getX() - gameBoardSize.getWidth() * 0.005;
            playerSpaceship.move();
            assertEquals(new Point2D(newPosition, 556), playerSpaceship.getLocation());
        }

    }

}
