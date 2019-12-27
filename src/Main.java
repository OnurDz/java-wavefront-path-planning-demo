import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        int x, y;
        try {
            x = Integer.parseInt(args[0]);
            y = Integer.parseInt(args[1]);
        } catch(Exception e) {
            System.err.println("Invalid Arguments!\nUsage: java Main <number of rows> <number of columns>");
            return;
        }
        Tuple dim = new Tuple(x, y);
        Map map = new Map(dim);
        map.setRandomObstacle();

        System.out.println("Initial map:");
        System.out.println(map);

        try {
            map.setGoal(new Tuple(7, 11));
            map.setStart(new Tuple(0, 0));
        } catch(IndexOutOfBoundsException e) {
            System.err.println(e.getMessage());
            return;
        }

        Stamper.stamp(map);
        System.out.println("Propogated map:");
        System.out.println(map);
        Navigation navi = new Navigation(map, new Tuple(0, 0), map.getGoalLocation());

        System.out.println("Cell order to navigate:");
        try {
            Queue<Tuple> orderedLocations = navi.navigate();
            while(!orderedLocations.isEmpty()) {
                System.out.print(orderedLocations.remove() + " -> ");
            }
            System.out.println("\n");
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Path is randomly blocked.\nTry generating a new map by rerunning the program.");
        }

        System.out.println("Visual map:");
        map.drawMap();
    }
}
