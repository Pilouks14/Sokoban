package intelligence;

import modele.movables.Box;

public class BoxGoalCouples {
    Box box;
    Node goal;

    public BoxGoalCouples(Box box, Node goal) {
        this.box = box;
        this.goal = goal;
    }

    public Box getBox() {
        return box;
    }

    public Node getGoal() {
        return goal;
    }


}
