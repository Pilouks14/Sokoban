package tests;

import modele.Modele;
import modele.State;
import modele.movables.Direction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utulities.FileParser;
import utulities.LevelMaker;


public class TestCancel {
    String filePath1 = "src/levels/lvl.xsb";
    String levels = FileParser.parse(filePath1);
    private Modele modele = new Modele(LevelMaker.setLevel(levels, 1));
    private Direction d;

    public TestCancel() throws Exception {
    }

    @Test
    public void testCancel() {
        d = Direction.L;
        modele.deplacement(d);
        State newState = modele.getState();
        modele.deplacement(d);
        modele.annulDeplacement();

        Assertions.assertSame(newState, modele.getState());
    }
}
