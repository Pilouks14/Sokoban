package modele;

import modele.movables.Direction;
import utulities.AbstractModeleEcouteur;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Classe du Modèle du Jeu
 */
public class Modele extends AbstractModeleEcouteur {
    private State state;
    private Deque<State> history = new ArrayDeque();
    private State newState;

    //private Menu menu;

    /**
     * Constructeur du Modèle du Jeu
     * @param state Etat actuel du jeu
     */
    public Modele(State state) {
        this.state = state;
    }

    /**
     * Retourne l'état du Jeu
     * @return Etat du jeu
     */
    public State getState() {
        return state;
    }

    /**
     * Effectue le déplacement d'un Objet Déplacable
     * @param d Direction (L,R,U,D)
     */
    public void deplacement(Direction d) {
        newState = state.deplacement(d);
        history.addLast(state);
        if(newState.getPlayer().getX()==state.getPlayer().getX() && newState.getPlayer().getY()==state.getPlayer().getY()){
            history.removeLast();
        }
        state = state.deplacement(d);
        fireChangement();
    }

    /**
     * Annule un déplacement sur le Jeu
     */
    public void annulDeplacement() {
        if (history.size() != 0) {
            state = history.removeLast();
            fireChangement();
        }
    }

    /**
     * Détermine si le jeu est fini
     * @return Booléen qui indique si le jeu est fini
     */
    public boolean isFinished() {
        return state.isFinished();
    }

    /**
     * Recommence le niveau à son état initial
     */
    public void resetLevel() {
        if (history.size() != 0) {
            state = history.removeFirst();
            fireChangement();
        }
        fireChangement();
        history = new ArrayDeque();
    }
  /*
    public void nextLevel(){
    }

    public void selectLevel(){
    }
*/


}
