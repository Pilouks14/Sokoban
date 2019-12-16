package modele;

/**
 * Classe qui créé la grille du Jeu
 */
public class Grid {
    Case[][] grid;
    int dimX;
    int dimY;

    /**
     * Constructeur de la grille du Jeu
     * @param grid Tableau de Case
     * @param dimX Dimension de x
     * @param dimY Dimension de y
     */
    public Grid(Case[][] grid, int dimX, int dimY) {
        //peut être changer ça je trouve ça bizare
        this.grid = grid;
        this.dimX = dimX;
        this.dimY = dimY;
    }

    /**
     * Retourne la dimension de x
     * @return La dimension de x
     */
    public int getDimX() {
        return dimX;
    }

    /**
     * Retourne la dimension de y
     * @return La dimension de y
     */
    public int getDimY() {
        return dimY;
    }

    /**
     * Retourne la case aux coordonnées [x,y]
     * @param x Coordonnée x
     * @param y Coordonnée y
     * @return La case de coordonnée [x,y]
     */
    public Case getCase(int x, int y) {
        return grid[x][y];
    }
}
