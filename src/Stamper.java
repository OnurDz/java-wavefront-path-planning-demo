import java.util.ArrayList;

public class Stamper {
    public static void stamp(Map map) {

        Cell st = map.getStart();

        ArrayList<Tuple> q = new ArrayList<>();
        q.add(map.getGoalLocation());
        int w = 2;

        while(!q.isEmpty()) {
            for (int i = 0; i < q.size(); i++) {
                Tuple t = q.get(i);
                map.cellAt(t).setWeight(w);
            }
            q = adjList(map, q);
            w++;
        }
    }

    private static boolean visited(Map map, Tuple t) {
        return map.cellAt(t).getWeight() > 0;
    }

    private static void clearList(Map map, ArrayList<Tuple> q) {
        int size = q.size();
        for (int i = 0; i < size; i++) {
            if (!isValid(map, q.get(i))) {
                q.remove(i--);
                size--;
            }
        }
    }

    private static boolean isValid(Map map, Tuple t) {
        int row = t.x;
        int col = t.y;
        return row >= 0 && row < map.getDim().x && col >= 0 && col < map.getDim().y && !visited(map, t);
    }

    private static ArrayList<Tuple> adjList(Map map, ArrayList<Tuple> q) {
        ArrayList<Tuple> res = new ArrayList<>();
        for (int i = 0; i < q.size(); i++) {
            Tuple t = q.get(i);
            int row = t.x;
            int col = t.y;
            res.add(new Tuple(row, col - 1));
            res.add(new Tuple(row, col + 1));
            res.add(new Tuple(row - 1, col));
            res.add(new Tuple(row + 1, col));
            res.add(new Tuple(row - 1, col - 1));
            res.add(new Tuple(row - 1, col + 1));
            res.add(new Tuple(row + 1, col - 1));
            res.add(new Tuple(row + 1, col + 1));
        }
        clearList(map, res);
        return res;
    }

}
