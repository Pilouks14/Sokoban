package tests;

import modele.Modele;
import modele.State;
import modele.movables.Direction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utulities.FileParser;
import utulities.LevelMaker;


public class TestReset {
    String filePath1 = "src/levels/lvl.xsb";
    String levels = FileParser.parse(filePath1);
    private Modele modele = new Modele(LevelMaker.setLevel(levels, 1));
    private Direction dL, dD;

    public TestReset() throws Exception {
    }

    @Test
    public void testReset() {
        dL = Direction.L;
        dD = Direction.D;
        State oldState = modele.getState();
        modele.deplacement(dL);
        modele.deplacement(dD);
        modele.resetLevel();

        Assertions.assertSame(oldState, modele.getState());
    }
}
