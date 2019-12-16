package modele.movables;

import modele.State;

/**
 * Classe du Joueur
 */
public class Player extends Movable {
    /**
     * Constructeur du joueur
     * @param x Coordonnée x
     * @param y Coordonnée y
     */
    public Player(int x, int y) {
        super(x, y);
    }

    /**
     * Permet le déplacement du joueur
     * @param s Etat du jeu actuellement
     * @param d Une direction (L,R,U,D)
     */
    public void deplacement(State s, Direction d) {
        if (isPossible(s, d)) {
            super.deplacement(s, d);

        }
    }

    /**
     *Définit si le coup du joueur est possible
     * @param tab Etat du jeu actuellement
     * @param d Direction (L,R,U,D)
     * @return Booléen qui indique si le coup est possible
     */
    @Override
    public boolean isPossible(State tab, Direction d) {
        int[] coord = nextMove(getX(), getY(), d);
        int x1 = coord[0];
        int y1 = coord[1];
        if (super.isPossible(tab, d)) {
            for (Box b : tab.getBoxes()) {
                if (b.getX() == x1 && b.getY() == y1) {
                    if (!b.isPossible(tab, d))
                        return false;
                    else
                        b.deplacement(tab, d);
                    return true;
                }
            }
            return true;
        } else
            return false;
    }

}