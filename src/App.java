import java.util.ArrayList;

import processing.core.*;

public class App extends PApplet {
    ArrayList<block> blocks = new ArrayList<>();
    int damage = 10;
    testingball Ball = new testingball(300, 400, damage, this);
    int color = 0;

    public static void main(String[] args) {
        PApplet.main("App");
    }

    public void setup() {
        background(0);

    }

    public void settings() {
        size(800, 600);
    }

    public void draw() {
        background(0);
        Ball.move();
        collide();
        makeBlocks(blocks);
        paddle();

    }

    public void makeBlocks(ArrayList<block> temp2) {
        for (int y = 5; y < 100; y += 25) {
            for (int x = 0; x <= 770; x += 35) {
                block temp = new block(x, y, this, 1);
                temp2.add(temp);
            }
        }

        for (block i : temp2) {
            i.display();
        }
    }

    public void collide() {
        for (int j = 0; j < blocks.size(); j++) {
            block i = blocks.get(j);
            Ball.bounce(i.getX(), 30, i.getY(), 20);
            blockCollides(i);
        }

    }

    public void blockCollides(block i) {
        i.blockHasBeenHit();
        //i.colorChange();
        if (i.blockDies() == true) {
            blocks.remove(i);
        }
    }

    public void paddle() {
        fill(250);
        rect(300, 440, 100, 20);
        Ball.bounce(300, 100, 440, 20);
    }
}
