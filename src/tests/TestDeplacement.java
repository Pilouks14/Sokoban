package tests;

import modele.Modele;
import modele.movables.Direction;
import modele.movables.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utulities.FileParser;
import utulities.LevelMaker;

public class TestDeplacement {
    String filePath1 = "src/levels/lvl.xsb";
    String levels = FileParser.parse(filePath1);
    int x, y, xObtenu, yObtenu, xAttendu, yAttendu;
    private Modele modele = new Modele(LevelMaker.setLevel(levels, 1));
    private Direction d;

    public TestDeplacement() throws Exception {
    }

    @Test
    public void testDeplacement() {

        Player player = modele.getState().getPlayer();
        x = player.getX();
        y = player.getY();
        xAttendu = x - 1;
        yAttendu = y;
        d = Direction.L;
        modele.deplacement(d);
        xObtenu = modele.getState().getPlayer().getX();
        yObtenu = modele.getState().getPlayer().getY();

        Assertions.assertEquals(xAttendu, xObtenu);
        Assertions.assertEquals(yAttendu, yObtenu);
    }
}
