import java.util.ArrayList;

import processing.core.*;

public class App extends PApplet {
    ArrayList<block> blocks = new ArrayList<>();
    int damage = 10;
    ball Ball = new ball(300, 400, damage, this);
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
        makeBlocks(blocks);
        collide();
        paddle();

    }

    public void makeBlocks(ArrayList<block> temp2) {
        for (int y = 5; y < 100; y += 25) {
            for (int x = 0; x <= 770; x += 35) {
                block temp = new block(x, y, this, 1);
                temp2.add(temp);
            }
        }
        for(block i: temp2) {
            color = i.colorChange(i.getHealth());
            i.display(color);
        }
    }

    public void collide() {
        for (int j = 0; j < blocks.size(); j++) {
            block i = blocks.get(j);
             if (Ball.Xpos() >= i.getX() && Ball.Xpos() <= (i.getX() + 60) && Ball.Ypos() <= i.getY() && Ball.Ypos() >= (i.getY() - 60)) {
                blockCollides(i);
                i.display(color);
            }
        }

    }

    public void blockCollides(block i) {
            i.blockHasBeenHit();
            i.colorChange(i.getHealth());
            if (i.blockDies() == true) {
                blocks.remove(i);
            }
            color = i.colorChange(i.getHealth());
        }
        public void paddle() {
            fill(250);
            rect(mouseX, 440, 100, 20);
            Ball.bounce(mouseX, 100, 440, 20);
        }
}
