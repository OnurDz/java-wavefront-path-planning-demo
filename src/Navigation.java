import java.util.LinkedList;
import java.util.Queue;

public class Navigation {
    Map stampedMap;
    Tuple start;
    Tuple goal;

    public Navigation(Map map, Tuple startLocation, Tuple goalLocation) {
        stampedMap = map;
        start = startLocation;
        goal = goalLocation;
    }

    public Queue<Tuple> navigate() throws IndexOutOfBoundsException {
        Queue<Tuple> q = new LinkedList<>();
        Tuple t = start;
        Tuple next;
        while(stampedMap.cellAt(t).getWeight() != 2) {
            q.add(t);
            next = getNext(t);
            stampedMap.cellAt(t).setWeight(-1);
            if(next.x == -1) {
                break;
            }
            t = next;
        }
        //stampedMap.cellAt(start).setWeight(-9);
        q.add(t);
        return q;
    }

    private Tuple getNext(Tuple location) {
        Tuple t;
        int weight = stampedMap.cellAt(location).getWeight();
        int row = location.x;
        int col = location.y;

        for (int i = row + 1; i >= row - 1; i--) {
            for (int j = col + 1; j >= col - 1; j--) {
                if (i == j)
                    continue;
                t = new Tuple(i, j);
                if(isValid(stampedMap, t) && stampedMap.cellAt(t).getWeight() < weight)
                    return t;
            }
        }

        return new Tuple(-1, -1);
    }

    private boolean isValid(Map map, Tuple t) {
        int row = t.x;
        int col = t.y;
        return row >= 0 && row < map.getDim().x && col >= 0 && col < map.getDim().y && map.cellAt(t).getWeight() > 1;
    }
}
