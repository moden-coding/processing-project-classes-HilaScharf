import java.util.ArrayList;

import processing.core.*;

public class App extends PApplet {
    ArrayList<Blocks> blocks = new ArrayList<Blocks>();
    Balls ball = new Balls(200, 300, this, 1);
    Blocks block = new Blocks(5, 5, 1, this);

    public static void main(String[] args) {
        PApplet.main("App");
    }

    public void setup() {
        background(0);
        for (int y = 5; y < 100; y += 25) {
            for (int x = 0; x <= 770; x += 35) {
                Blocks temp = new Blocks(x, y, 1, this);
                blocks.add(temp);
            }
        }
    }

    public void settings() {
        size(800, 600);
    }

    public void draw() {
        background(0);
        ball.make();
        //paddle();
        
        
        int x = 0;
        while (x < blocks.size()) {
            blocks.get(x).fill();
            blocks.get(x).display();
            if (ball.touching(blocks.get(x).getY(), blocks.get(x).getY() + 20, blocks.get(x).getX() + 30, blocks.get(x).getX())) {
                ball.bounce(blocks.get(x).getY(), blocks.get(x).getY() + 20, blocks.get(x).getX() + 30, blocks.get(x).getX());
                blocks.get(x).blockHasBeenHit(1);
                if(blocks.get(x).blockDies()) {
                    blocks.remove(x);
                }
            }
            x++;

        }
    }

    public void makeBlocks() {
        for (int y = 5; y < 100; y += 25) {
            for (int x = 0; x <= 770; x += 35) {
                Blocks temp = new Blocks(x, y, 1, this);
                temp.display();
                blocks.add(temp);
            }
        }
    }
    public void paddle() {
        fill(250);
        rect(mouseX, 440, 100, 20);
        ball.bounce(440, 460, mouseX + 100, mouseX);
    }
}