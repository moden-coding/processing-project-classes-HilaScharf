import processing.core.PApplet;

public class Blocks {
    private int blockX;
    private int blockY;
    private int health;
    private PApplet a;
    public Blocks(int xPos, int yPos, int level, PApplet c) {
        blockX = xPos;
        blockY = yPos;
        health = level * 3;
        a = c;
    }
    public void fill() {
        if (health >= 3) {
            a.fill(2, 224, 50);
        } else if (health == 2) {
            a.fill(246, 250, 10);
        } else if (health == 1) {
            a.fill(255, 3, 3);
        }
    }
    public void display() {
        a.rect(blockX, blockY, 30, 20);
    }
    public int getX() {
        return blockX;
    }
    public int getY() {
        return blockY;
    }
    public void blockHasBeenHit(int damage) {
        health = health - 1;
        if (health >= 3) {
            a.fill(2, 224, 50);
        } else if (health == 2) {
            a.fill(246, 250, 10);
        } else if (health == 1) {
            a.fill(255, 3, 3);
        } 
    }
    public boolean blockDies() {
        if(health >= 1) {
            return false;
        } else {
            return true;
        }
    }
    public int getHealth() {
        return health;
    }
}
