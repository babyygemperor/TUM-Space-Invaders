import org.easymock.EasyMockExtension;
import javafx.embed.swing.JFXPanel;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(EasyMockExtension.class)
public class ShootingTests {

    @BeforeAll
    public static void setup() {
        final JFXPanel fxPanel = new JFXPanel();
    }
    @TestSubject 
    private GameBoard gameBoard = new GameBoard(new Dimension2D(10.0, 10.0));
    @Mock
    private LaserBeam laserBeamMock;
    @Test
    public void successfulHitTest() {
    	
    	expect(laserBeamMock.hit()).andReturn(true);
    	replay(laserBeamMock);
    }
}
