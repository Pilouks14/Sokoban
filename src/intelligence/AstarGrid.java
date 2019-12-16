package intelligence;

import modele.Case;
import modele.Grid;
import modele.State;
import modele.movables.Box;
import modele.movables.Movable;
import modele.movables.Player;

import java.util.ArrayList;

public class AstarGrid {
    State state;
    ArrayList<Node> goals = new ArrayList<>();
    Boolean[][] boolGrid;
    Boolean[][] boolGridUnmovable;
    int dimX, dimY;

    public AstarGrid(State state) {
        this.state = state;
        Grid grid = state.getGrid();
        dimX = grid.getDimX();
        dimY = grid.getDimY();
        boolGrid = new Boolean[dimX][dimY];
        for (int i = 0; i < dimX; i++) {
            for (int j = 0; j < dimY; j++) {
                if (grid.getCase(i, j) == Case.FLOOR || grid.getCase(i, j) == Case.GOAL) {
                    boolGrid[i][j] = true;
                } else
                    boolGrid[i][j] = false;
                if (grid.getCase(i, j) == Case.GOAL)
                    goals.add(new Node(i, j));
            }
        }
    }

    public AstarGrid(State state, Boolean[][] boolGrid, ArrayList goals, int dimX, int dimY) {
        this.state = state;
        this.boolGrid = boolGrid;
        this.goals = goals;
        this.dimX = dimX;
        this.dimY = dimY;
    }

    Node[][] nodes;

    public State getState() {
        return state;
    }

    public ArrayList getGoals() {
        return goals;
    }

    public Boolean[][] getBoolGrid() {
        return boolGrid;
    }

    public int getDimX() {
        return dimX;
    }

    public int getDimY() {
        return dimY;
    }

    public AstarGrid makeCopy() {
        Boolean[][] originalBool = getBoolGrid();
        Boolean[][] boolGridCopy = copyBoolGrid();
        return new AstarGrid(getState(), boolGridCopy, getGoals(), getDimX(), getDimY());
    }

    public Boolean[][] copyBoolGrid() {
        Boolean[][] boolGridCopy = new Boolean[getDimX()][getDimY()];
        for (int i = 0; i < getDimX(); i++) {
            for (int j = 0; j < getDimY(); j++) {
                boolGridCopy[i][j] = boolGrid[i][j];
            }
        }
        return boolGridCopy;
    }

    public AstarGrid movableToUnmovable(Movable movable) {
        AstarGrid res = makeCopy();
        for (Box box : state.getBoxes()) {
            res.getBoolGrid()[box.getX()][box.getY()] = false;
        }
        if (movable instanceof Player)
            return res;
        else
            res.getBoolGrid()[movable.getX()][movable.getY()] = true;
        return res;
    }

    public AstarGrid movableToUnmovable() {
        AstarGrid res = makeCopy();
        for (Box box : state.getBoxes()) {
            res.getBoolGrid()[box.getX()][box.getY()] = false;
        }
        return res;
    }

    public Boolean[][] movableToUnmovableForGrid() {
        Boolean[][] res = copyBoolGrid();
        for (Box box : state.getBoxes()) {
            res[box.getX()][box.getY()] = false;
        }
        return res;
    }

    public ArrayList<Node> getNeighbourNodes(Box box) {
        return getNeighbourNodes(new Node(box.getX(), box.getY()));
    }

    public ArrayList<Node> getNeighbourNodes(Node node) {
        //On transforme les box en false, comme ça si une boite en colle un autre son chemin est bloqué.
        //Notre pathfinder ne cherchera pas à arrivé sur la boite mais à coté donc tans pis ça valeur est false
        //faut juste une case d'arrivé à coté

        if (boolGridUnmovable == null)
            boolGridUnmovable = movableToUnmovableForGrid();
        ArrayList<Node> res = new ArrayList<>();
        int x = node.getX();
        int y = node.getY();
        if (x - 1 >= 0) {
            if (boolGridUnmovable[x - 1][y])
                res.add(new Node(x - 1, y));
        }
        if (x + 1 < getDimX()) {
            if (boolGridUnmovable[x + 1][y])
                res.add(new Node(x + 1, y));
        }
        if (y - 1 >= 0) {
            if (boolGridUnmovable[x][y - 1]) {
                res.add(new Node(x, y - 1));
            }
        }
        if (y + 1 < getDimY()) {
            if (boolGridUnmovable[x][y + 1]) {
                res.add(new Node(x, y + 1));
            }
        }
        return res;
    }

    public void showToDel() {
        for (int i = 0; i < getDimY(); i++) {
            for (int j = 0; j < getDimX(); j++) {
                if (getBoolGrid()[j][i])
                    System.out.print("0");
                else
                    System.out.print("X");
            }
            System.out.print("\n");
        }
    }


}
