package vue;

import modele.Case;
import modele.Grid;
import modele.Modele;
import modele.State;
import modele.movables.Box;
import utulities.EcouteurModele;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Classe creant le Graphique du niveau
 */
public class SokobanVue extends JPanel implements EcouteurModele {
    public static final int LARGEUR_CASE = 30;
    public static final int HAUTEUR_CASE = 30;
    private static BufferedImage imageVictoire;
    private static BufferedImage imageSol;
    private static BufferedImage imageMur;
    private static BufferedImage imagePersonnage;
    private static BufferedImage imageCaisse;
    private static BufferedImage imageCible;
    private Modele modele;
    private Grid grid;
    private int dimX;
    private int dimY;

    /**
     * Constructeur de SokobanVue
     * @param modele Prend le Modele du jeu
     */
    SokobanVue(Modele modele) {
        modele.ajoutEcouteur(this);
        this.modele = modele;
        State state = modele.getState();
        grid = state.getGrid();
        dimX = grid.getDimX();
        dimY = grid.getDimY();
        setPreferredSize(new Dimension(LARGEUR_CASE * dimX, HAUTEUR_CASE * dimY));


    }

    /**
     * Met en memoire les images qui vont servir dans l'affichage graphique
     */
    public static void initializeResource() {
        try {
            imageVictoire = ImageIO.read(new File("src/docannexes/victoire.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            imageSol = ImageIO.read(new File("src/docannexes/sol.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            imageMur = ImageIO.read(new File("src/docannexes/mur.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            imagePersonnage = ImageIO.read(new File("src/docannexes/licorne.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            imageCaisse = ImageIO.read(new File("src/docannexes/caisse.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            imageCible = ImageIO.read(new File("src/docannexes/cible.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Affiche le niveau de jeu
     * @param g Graphics qui permet de dessiner des images
     */
    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        paintLevel(g);
        paintReste(g);
        //paint_movable(g,this.source);

    }

    /**
     * Dessine les boites et le joueur
     * @param g Graphics qui permet de dessiner des images
     */
    public void paintReste(Graphics g) {
        State state = modele.getState();
        //Le joueur
        int x = state.getPlayer().getX() * LARGEUR_CASE;
        int y = state.getPlayer().getY() * HAUTEUR_CASE;
        g.drawImage(imagePersonnage, x, y, LARGEUR_CASE, HAUTEUR_CASE, null);

        //les caisses
        for (Box box : state.getBoxes()) {
            x = box.getX() * LARGEUR_CASE;
            y = box.getY() * HAUTEUR_CASE;
            g.drawImage(imageCaisse, x, y, LARGEUR_CASE, HAUTEUR_CASE, null);

        }

        if (state.isFinished()) {
            g.drawImage(imageVictoire, 0, 0, LARGEUR_CASE * dimX, HAUTEUR_CASE * dimY, null);
        }
        //this.repaint();
    }

    /**
     * Dessine le niveau sans les Objets Deplacables
     * @param g Graphics qui permet de dessiner des images
     */
    private void paintLevel(Graphics g) {
        //La grille


        State state = modele.getState();
        for (int i = 0; i < dimX; i++) {
            for (int j = 0; j < dimY; j++) {
                if (grid.getCase(i, j) == Case.FLOOR) {
                    g.drawImage(imageSol, i * LARGEUR_CASE, j * LARGEUR_CASE, LARGEUR_CASE, HAUTEUR_CASE, null);
                } else if (grid.getCase(i, j) == Case.WALL) {
                    g.drawImage(imageMur, i * LARGEUR_CASE, j * LARGEUR_CASE, LARGEUR_CASE, HAUTEUR_CASE, null);
                } else if (grid.getCase(i, j) == Case.GOAL) {
                    g.drawImage(imageCible, i * LARGEUR_CASE, j * LARGEUR_CASE, LARGEUR_CASE, HAUTEUR_CASE, null);

                    //g.fillRect(i*LARGEUR_CASE,j*HAUTEUR_CASE,LARGEUR_CASE,HAUTEUR_CASE);
                }
            }


        }
    }

    /**
     * Permet de mettre a jour l'affichage Graphique a chaque coup
     */
    @Override
    public void modeleMisAJour(Object source) {
        this.repaint();
        this.revalidate();
    }

}
