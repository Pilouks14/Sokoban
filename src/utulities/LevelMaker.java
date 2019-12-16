package utulities;

import modele.Case;
import modele.Grid;
import modele.State;
import modele.movables.Box;
import modele.movables.Player;

import java.util.ArrayList;

/**
 * LevelMaker permet de générer un niveau en fonction d'un String
 */
public class LevelMaker {

    /**
     * Sépare le String contenant les niveaux et en retourne un sous forme de String
     * @param lvls String contenant les niveaux
     * @param lvlNbr Numéro du niveau à récupérer
     * @return String avec le niveau demandé
     */
    public static String getLevelAsString(String lvls, int lvlNbr) {
        return lvls.split(";.*\n")[lvlNbr - 1];

    }
    /**
     * Permet d'obtenir les dimension d'un niveau, pour qu'il soit tous rectangulaire
     * @param lvl String du niveau dont on veut les dimensions
     * @return int[] Tableau d'entier contenant les dimensions
     */
    public static int[] getDim(String lvl) {
        int height = 0;
        int width = 0;
        int counter = 0;
        for (char s : lvl.toCharArray()) {
            if (s != '\n') {
                counter += 1;
            } else {
                if (counter > width)
                    width = counter;
                counter = 0;
                height += 1;
            }
        }
        int[] res = new int[2];
        res[0] = width;
        res[1] = height;
        return res;
    }

    /**
     * Crée le niveau voulu
     * @param lvls String des niveaux
     * @param lvlNbr Numéro du niveau requis
     * @return Un état de jeu State utilisable par le modèle
     */
    public static State setLevel(String lvls, int lvlNbr) {
        String lvl = getLevelAsString(lvls, lvlNbr);
        Player player = new Player(0, 0);
        Case[][] grid;
        ArrayList<Box> boxes = new ArrayList<Box>();
        int[] dim = getDim(lvl);
        int x = dim[0];
        int y = dim[1];
        //On initialise la grille du jeu avec le sol.
        grid = new Case[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                grid[i][j] = Case.FLOOR;
            }
        }
        int i = 0;
        int j = 0;
        for (char s : lvl.toCharArray()) {
            switch (s) {
                case ' ':
                    grid[i][j] = Case.FLOOR;
                    break;
                case '#':
                    grid[i][j] = Case.WALL;
                    break;
                case '.':
                    grid[i][j] = Case.GOAL;
                    break;
                case '@':
                    player = new Player(i, j);
                    break;
                case '$':
                    boxes.add(new Box(i, j));
                    break;
                case '*':
                    //caisse sur zone de rangement
                    boxes.add(new Box(i, j));
                    grid[i][j] = Case.GOAL;
                    break;
                case '+':
                    // personnage sur zone de rangement
                    player = new Player(i, j);
                    grid[i][j] = Case.GOAL;
                    break;


            }
            i++;
            if (s == '\n') {
                i = 0;
                j++;
            }
        }
        return new State(new Grid(grid, x, y), boxes, player);
    }
}
