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
        if (gameStart == 1) {
            for (int y = 5; y < 100; y += 25) {
                for (int x = 0; x <= 770; x += 35) {
                    Blocks temp = new Blocks(x, y, 1, this);
                    blocks.add(temp);
                }
            }
        }

    }

    public void settings() {
        size(800, 600);
    }

    public void draw() {
        if (gameStart == 0) {
            homeScreen();
        }
        if (gameStart == 1) {
            gameplay();
        }
        if (gameStart == 2) {
            endScreen();
        }
        if (gameStart == 3) {
            tutorialScreen();
        }
    }

    public void gameplay() {
        background(0);
        stroke(0);
        strokeWeight(1);
        Balls ball = new Balls(200, 300, this, 1);
        balls.add(ball);
        ball.make();
        ball.move();
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
        int ballIndex = 0;
        if (ballIndex < balls.size()) {
            Balls thisBall = balls.get(ballIndex);
            if (thisBall.y() > height) {
                balls.remove(thisBall);
            }
        }
        if (balls.size() == 0) {
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
        fill(0);
        stroke(255);
        strokeWeight(5);
        rect(250, 100, 300, 100);
        rect(250, 250, 300, 100);
        rect(250, 400, 300, 100);
        fill(255);
        textSize(50);
        text("new game", 290, 160);
        text("saved game", 280, 310);
        text("how to play", 275, 460);
        if (newGame(250, 100) == true) {
            gameStart = 1;
        }
        if(tutorial() == true) {
            gameStart = 3;
        }
    }
    public void tutorialScreen() {
        background(0);
        strokeWeight(4);
        textSize(25);
        fill(255);
        text("use the paddle to prevent the ball from hitting the bottom, and to", 50, 50);
        text("get the ball to hit and destroy the bricks. use the paddle by moving", 50, 80);
        text("your mouse. the goal of the game is to destroy all the bricks.", 50, 110);
        text("destroy the powerup bricks to increase the strength of the ball", 50, 140);
        stroke(255);
        strokeWeight(5);
        fill(0);
        rect(250, 200, 300, 100);
        rect(250, 350, 300, 100);
        fill(255);
        textSize(50);
        text("new game", 290, 260);
        text("saved game", 280, 410);
        if (newGame(250, 200) == true) {
            gameStart = 1;
        }
    }
    public boolean tutorial() {
        if (mousePressed && mouseX >= 250 && mouseX <= 550 && mouseY >= 400 && mouseY <= 500) {
            println("Rectangle clicked!");
            return true;
        } else {
            return false;
        }
    }
    public boolean newGame(int buttonX, int buttonY) {
        if (mousePressed && mouseX >= buttonX && mouseX <= buttonX + 300 && mouseY >= buttonY && mouseY <= buttonY + 100) {
            println("Rectangle clicked!");
            return true;
        } else {
            return false;
        }
    }

    public void endScreen() {

    }
}