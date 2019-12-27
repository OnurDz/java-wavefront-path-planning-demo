import java.util.Random;

public class Map {
    private Cell[][] map;
    private Cell start;
    private Tuple startLocation;
    private Cell goal;
    private Tuple goalLocation;
    private Tuple dim;
    private int size;

    public Map(Tuple d) {
        this.dim = d;
        this.size = dim.x * dim.y;
        this.map = new Cell[dim.x][dim.y];
        init();
        //setAdjacency();
    }

    public Map(Tuple d, Tuple starter, Tuple dest) {
        this.dim = d;
        this.size = dim.x * dim.y;
        this.map = new Cell[dim.x][dim.y];
        init();
        setStart(starter);
        setGoal(dest);
        //setAdjacency();
    }

    public void setGoal(Tuple dest) throws IndexOutOfBoundsException {
        goalLocation = dest;
        Cell cur = cellAt(dest);
        if(cur.isFree()) {
            this.goal = cur;
        } else throw new IndexOutOfBoundsException("Oops!\nStart cell you are trying to set is randomly occupied by an obstacle.\nBlame randomization process and rerun the program.");
    }

    public Cell getGoal() {
        return goal;
    }

    public void setStart(Tuple starter) throws IndexOutOfBoundsException {
        this.startLocation = starter;
        Cell cur = cellAt(starter);
        if(cur.isFree()) {
            this.start = cur;
        } else throw new IndexOutOfBoundsException("Oops!\nGoal cell you are trying to set is randomly occupied by an obstacle.\nBlame randomization process and rerun the program.");
    }

    public Cell getStart() {
        return start;
    }
    public int getSize() {
        return size;
    }
    public Tuple getDim() {
        return dim;
    }
    public Tuple getStartLocation() {
        return startLocation;
    }
    public Tuple getGoalLocation() {
        return goalLocation;
    }

    public void setRandomObstacle() {
        for(int i = 0; i < size / 5; i++) {
            Random rand = new Random();
            Tuple t = new Tuple(rand.nextInt(dim.x), rand.nextInt(dim.y));
            rand.nextInt(dim.x);
            rand.nextInt(dim.y);
            cellAt(t).setObstacle();
            cellAt(t).setWeight(1);
        }
    }

    public Cell cellAt(Tuple t) {
        return map[t.x][t.y];
    }

    public String toString() {
        String s = "";
        for(int i = 0; i < dim.x; i++) {
            for(int j = 0; j < dim.y; j++) {
                s = s + map[i][j].getWeight() + "\t";
            }
            s = s + "\n";
        }
        return s;
    }

    private void init() {
        for(int i = 0; i < dim.x; i++) {
            for(int j = 0; j < dim.y; j++) {
                map[i][j] = new Cell();
            }
        }
    }

    public void drawMap() {
        char[][] visualMap = new char[dim.x + 1][dim.y + 1];
        for(int i = 0; i < dim.x + 1; i++) {
            for(int j = 0; j < dim.y + 1; j++) {
                if(i == dim.x || i == 0) {
                    visualMap[i][j] = '#';
                } else if(j == dim.y || j == 0) {
                    visualMap[i][j] = '#';
                } else if(map[i][j].getWeight() == 1) {
                    visualMap[i][j] = 'o';
                } else if(map[i][j].getWeight() == 2) {
                    visualMap[i][j] = 'G';
                } else if(map[i][j].getWeight() == -9) {
                    visualMap[i][j] = 'S';
                } else if(map[i][j].getWeight() < 0) {
                    visualMap[i][j] = '*';
                } else {
                    visualMap[i][j] = '.';
                }
            }
        }
        visualMap[startLocation.x + 1][startLocation.y + 1] = 'S';
        for(int i = 0; i < dim.x + 1; i++) {
            for (int j = 0; j < dim.y + 1; j++) {
                System.out.print(visualMap[i][j] + " ");
            }
            System.out.println();
        }
    }
}
