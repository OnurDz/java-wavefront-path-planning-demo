import java.util.ArrayList;

public class Cell {
    private int weight;
    private boolean free;

    public Cell() {
        this.weight = 0;
        this.free = true;
    }
    public Cell(int w, boolean f) {
        this.weight = w;
        this.free = f;
    }

    public int getWeight() {return weight;}
    public void setWeight(int w) {this.weight = w;}

    public boolean isFree() {return free;}
    public void setFree() {this.free = true; this.weight = 0;}
    public void setObstacle() {this.free = false;}
}
