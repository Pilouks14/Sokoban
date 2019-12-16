package tests;

import modele.Modele;
import modele.movables.Direction;
import modele.movables.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utulities.FileParser;
import utulities.LevelMaker;

public class TestDeplacementMur {
    String filePath1 = "src/levels/lvl.xsb";
    String levels = FileParser.parse(filePath1);
    int x, y, xObtenu, yObtenu, xAttendu, yAttendu;
    private Modele modele = new Modele(LevelMaker.setLevel(levels, 2));
    private Direction d;

    public TestDeplacementMur() throws Exception {
    }

    @Test
    public void testDeplacementMur() {

        Player player = modele.getState().getPlayer();
        x = player.getX();
        y = player.getY();
        xAttendu = x; //Les coordonnées attendues sont les mêmes que celles de base car il y a un mur
        yAttendu = y; //qui empêche le déplacement du personnage vers le haut.
        d = Direction.U;
        modele.deplacement(d);
        xObtenu = modele.getState().getPlayer().getX();
        yObtenu = modele.getState().getPlayer().getY();

        Assertions.assertEquals(xAttendu, xObtenu);
        Assertions.assertEquals(yAttendu, yObtenu);
    }
}
