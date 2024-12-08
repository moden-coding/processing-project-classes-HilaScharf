import processing.core.PApplet;

public class block {
    private int x;
    private int y;
    private PApplet c;
    private int health;
    private int color;
    public block(int xPos, int yPos, PApplet p, int level) {
        x = xPos;
        y = yPos;
        c = p;
        health = level*3;
    }
    private int colorChange() {
        if(health == 3) {
            color = c.color(2, 224, 50);
        } else if(health == 2) {
            color = c.color(246, 250, 10);
        } else if(health == 1) {
            color = c.color(255, 3, 3);
        }
        return color;

    }
    public void display() {
        c.fill(colorChange());
        c.rect(x, y, 30, 20);
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void blockHasBeenHit() {
        health -= 1;
        if(health == 3) {
            color = c.color(2, 224, 50);
        } else if(health == 2) {
            color = c.color(246, 250, 10);
        } else if(health == 1) {
            color = c.color(255, 3, 3);
        }
    }
    public boolean blockDies() {
        if(health >= 1) {
            return true;
        } else {
            return false;
        }
    }
    public int getHealth() {
        return health;
    }

}
