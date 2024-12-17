import java.util.ArrayList;

import processing.core.*;

public class App extends PApplet {
    ArrayList<Blocks> blocks = new ArrayList<Blocks>();
    ArrayList<Balls> balls = new ArrayList<Balls>();
    int gameStart = 0;

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
        while (gameStart == 0) {
            fill(100, 100, 100);
            rect(200, 100, 50, 100);
            homeScreen();
        }
        while (gameStart == 1) {
            gameplay();
        }
        while(gameStart == 2) {
            endScreen();
        }
    }

    public void gameplay() {
        background(0);
        Balls ball = new Balls(200, 300, this, 1);
        balls.add(ball);
        ball.make();
        paddle(balls);
        int x = 0;
        while (x < blocks.size()) {
            Blocks block = blocks.get(x);
            block.fill();
            block.display();
            if (ball.touching(block.getY(), block.getY() + 20, block.getX() + 30, block.getX())) {
                ball.bounce(block.getY(), block.getY() + 20, block.getX() + 30, block.getX());
                block.blockHasBeenHit(1);
                if (block.blockDies()) {
                    blocks.remove(x);
                }
            }
            x++;
        }
        if(balls.size() == 0) {
            gameStart = 2;
        }
    }

    public void makeBlocks() {
        for (int y = 5; y < 100; y += 25) {
            for (int x = 0; x <= 770; x += 35) {
                Blocks temp = new Blocks(x, y, 5, this);
                temp.display();
                blocks.add(temp);
            }
        }
    }

    public void paddle(ArrayList<Balls> balls) {
        for (Balls ball : balls) {
            fill(250);
            rect(mouseX, 440, 100, 20);
            ball.bounce(440, 480, mouseX + 100, mouseX);
        }
    }

    public void homeScreen() {

    }
    public void endScreen() {

    }
}