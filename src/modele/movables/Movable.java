package modele.movables;

import modele.Case;
import modele.Grid;
import modele.State;
import utulities.AbstractModeleEcouteur;

/**
 * Classe abstraite des Objets Déplacables
 */
public abstract class Movable extends AbstractModeleEcouteur {
    private int x;
    private int y;


    /**
     * Constructeur d'un objet déplacable avec deux coordonnées
     * @param x Coordonnée x
     * @param y Coordonnée y
     */
    public Movable(int x, int y) {
        this.x = x;
        this.y = y;

    }

    /**
     * Retourne la valeur de la coordonnée x
     * @return La valeur de la coordonnée x
     */
    public int getX() {
        return x;
    }

    /**
     * Retourne la valeur de la coordonnée y
     * @return La valeur de la coordonnée y
     */
    public int getY() {
        return y;
    }

    /**
     * Affiche les coordonnées x et y
     * @return Les coordonnées x et y
     */
    @Override
    public String toString() {
        return "x" + getX() + "y" + getY();
    }

    /**
     * Permet le déplacement des objets déplacables
     * @param s Etat du jeu actuellement
     * @param d Une direction (L,R,U,D)
     */
    public void deplacement(State s, Direction d) {
        if (this.isPossible(s, d)) {
            switch (d) {
                case L:
                    this.x -= 1;
                    break;
                case R:
                    this.x += 1;
                    break;
                case U:
                    this.y -= 1;
                    break;
                case D:
                    this.y += 1;
                    break;
            }
            fireChangement();
        }

    }

    /**
     * Effectue le déplacement de L'objet déplacable
     * @param x Coordonnée x
     * @param y Coordonné y
     * @param d Direction (L,R,U,D)
     * @return Les nouvelles coordonnées sous forme d'un tableau
     */
    public int[] nextMove(int x, int y, Direction d) {
        int x1 = x;
        int y1 = y;
        switch (d) {
            case L:
                x1 -= 1;
                break;
            case R:
                x1 += 1;
                break;
            case U:
                y1 -= 1;
                break;
            case D:
                y1 += 1;
                break;
        }
        int[] coord;
        coord = new int[2];
        coord[0] = x1;
        coord[1] = y1;
        return coord;
    }

    /**
     * Définit si le coup est possible
     * @param tab Etat du jeu actuellement
     * @param d Direction (L,R,U,D)
     * @return Booléen qui indique si le coup est possible
     */
    public boolean isPossible(State tab, Direction d) {
        int[] coord = nextMove(x, y, d);
        int x1 = coord[0];
        int y1 = coord[1];
        if (x1 >= tab.getGrid().getDimX() || y1 >= tab.getGrid().getDimY()
                || x1 < 0 || y1 < 0)
            return false;

        Grid grid = tab.getGrid();
        return (grid.getCase(x1, y1) != Case.WALL);


    }
}