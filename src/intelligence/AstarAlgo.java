package intelligence;

import java.util.ArrayList;
public class AstarAlgo {
    private ArrayList<Node> openList = new ArrayList<>();
    private ArrayList<Node> closedList = new ArrayList<>();
    private Node start;
    private Node end;


    public AstarAlgo(Node start, Node end) {
        this.start = start;
        this.end = end;
        start.setValue(0);
        openList.add(start);
    }

    public Node algoStart(AstarGrid grid) {
        while (openList.size() > 0) {
            Node bestBet = popMinFromList(openList);
            ArrayList<Node> neighbors = grid.getNeighbourNodes(bestBet);
            for (Node neighbor : neighbors) {
                //System.out.println(neighbor);
                neighbor.setParent(bestBet);
                if (neighbor.compare(end)) {
                    return neighbor;
                }
                neighbor.setDistanceEnd(end);
                neighbor.setValue(neighbor.getDistanceStart() + neighbor.getDistanceEnd());
                if (addToOpenList(neighbor))
                    openList.add(neighbor);
            }
            closedList.add(bestBet);
        }
        return null;
    }

    public Boolean addToOpenList(Node neighbor) {
        for (Node explored : openList) {
            if (explored.compare(neighbor)) {
                if (explored.getValue() < neighbor.getValue()) {
                    return false;
                }
            }
        }
        for (Node closed : closedList) {
            if (closed.compare(neighbor)) {
                if (closed.getValue() < neighbor.getValue())
                    return false;
            }
        }
        return true;
    }

    public Node popMinFromList(ArrayList<Node> openList) {
        Node res = new Node(-1, -1);
        res.setValue(999999999);
        for (Node node : openList) {
            if (node.getValue() < res.getValue()) {
                res = node;
            }
        }
        openList.remove(res);
        return res;
    }

    public ArrayList<Node> getPath(Node endNode) {
        ArrayList<Node> path = new ArrayList<>();
        if (endNode == null)
            return path;
        while (endNode.getParent() != null) {
            path.add(0, endNode);
            endNode = endNode.getParent();
        }
        //path.add(0, start);
        for (Node node : path) {
            System.out.println(node);
        }
        return path;
    }

}
