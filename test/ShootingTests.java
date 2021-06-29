import org.easymock.EasyMockExtension;
import javafx.embed.swing.JFXPanel;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import tum.space.invaders.controller.GameBoard;
import tum.space.invaders.controller.LaserBeam;
import tum.space.invaders.controller.LaserBeamShooting;
import tum.space.invaders.model.spaceship.PlayerSpaceship;
import tum.space.invaders.view.GameBoardUI;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(EasyMockExtension.class)
public class ShootingTests {

    //Added 1 Mock test

    @BeforeAll
    public static void setup() {
        final JFXPanel fxPanel = new JFXPanel();
    }

    @TestSubject 
    private final GameBoard gameBoard = new GameBoard(GameBoardUI.getPreferredSize());

    @Mock
    private LaserBeamShooting laserBeamMock;

    @Test
    public void successfulHitTest() {
        int expectedScore = gameBoard.getScore() + 1;
        PlayerSpaceship playerSpaceship = new PlayerSpaceship(gameBoard, GameBoardUI.getPreferredSize());

        LaserBeam defaultLaserBeam = new LaserBeam(true, playerSpaceship.getLocation());

    	expect(laserBeamMock.shoot()).andReturn(defaultLaserBeam);
    	replay(laserBeamMock);

    	playerSpaceship.shoot();

    	assertEquals(defaultLaserBeam, laserBeamMock.shoot());
    	assertEquals(expectedScore, gameBoard.getScore());
    }

}
