import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Paths;
import java.io.PrintWriter;
import java.io.IOException;
import processing.core.*;

public class App extends PApplet {
    ArrayList<Blocks> blocks = new ArrayList<Blocks>();
    ArrayList<Balls> balls = new ArrayList<Balls>();
    Balls ball;
    Balls ballTwo;
    Balls ballThree;
    int gameStart = 0;
    int level = 1;
    boolean paused = false;

    public static void main(String[] args) {
        PApplet.main("App");
    }

    public void setup() {
        background(0);
        settingUp();

    }

    public void settingUp() {
        for (int y = 5; y < 100; y += 25) {
            for (int x = 0; x <= 770; x += 35) {
                Blocks temp = new Blocks(x, y, level, this);
                blocks.add(temp);
            }
        }
        ball = new Balls(200, 300, this, level * 3);
        ballTwo = new Balls(250, 300, this, 1);
        ballThree = new Balls(300, 300, this, 1);
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
        if (gameStart == 4) {
            winScreen();
        }
        if (gameStart == 5) {
            pausedScreen();
        }
    }

    public void gameplay() {
        background(0);
        stroke(0);
        strokeWeight(1);
        balls.add(ball);
        balls.add(ballTwo);
        balls.add(ballThree);
        ball.make();
        ballTwo.make();
        ballThree.make();
        if (paused == false) {
            ball.move();
            ballTwo.move();
            ballThree.move();
        } else {
            gameStart = 5;
        }
        paddle(balls);
        int x = 0;
        while (x < blocks.size()) {
            Blocks block = blocks.get(x);
            block.fill();
            block.display();
            if (ball.touching(block.getY(), block.getY() + 20, block.getX() + 30, block.getX())) {
                ball.bounce(block.getY(), block.getY() + 20, block.getX() + 30, block.getX());
                int j = 1;
                while (j < level * 3) {
                    block.blockHasBeenHit(1);
                    j++;
                }
                if (block.blockDies()) {
                    blocks.remove(x);
                }
            }
            if (ballTwo.touching(block.getY(), block.getY() + 20, block.getX() + 30, block.getX())) {
                ballTwo.bounce(block.getY(), block.getY() + 20, block.getX() + 30, block.getX());
                block.blockHasBeenHit(1);
                if (block.blockDies()) {
                    blocks.remove(x);
                }
            }
            if (ballThree.touching(block.getY(), block.getY() + 20, block.getX() + 30, block.getX())) {
                ballThree.bounce(block.getY(), block.getY() + 20, block.getX() + 30, block.getX());
                block.blockHasBeenHit(1);
                if (block.blockDies()) {
                    blocks.remove(x);
                }
            }
            x++;
        }
        if (ball.y() > 600) {
            gameStart = 2;
        }
        if (blocks.size() < 0) {
            gameStart = 4;
        }
        if (key == 'p') {
            paused = true;
        }
    }

    public void pausedScreen() {
        background(0);
        fill(0);
        stroke(255);
        strokeWeight(5);
        rect(250, 250, 300, 100);
        rect(250, 400, 300, 100);
        fill(255);
        textSize(45);
        text("Save level", 295, 310);
        text("Unpause", 300, 460);
        if (newGame(250, 400) == true) {
            paused = false;
            gameStart = 1;
        }
        if (newGame(250, 250) == true) {
            saveLevel();
        }
    }

    public void winScreen() {
        background(0);
        fill(0);
        stroke(255);
        strokeWeight(5);
        rect(250, 250, 300, 100);
        rect(250, 400, 300, 100);
        fill(255);
        textSize(45);
        text("You win!", 325, 150);
        text("Save level", 295, 310);
        text("Next level", 300, 460);
        if (newGame(250, 400) == true) {
            level++;
            for (Balls i : balls) {
                i.reset();
            }
            for (int y = 5; y < 100; y += 25) {
                for (int x = 0; x <= 770; x += 35) {
                    Blocks temp = new Blocks(x, y, level, this);
                    blocks.add(temp);
                }
            }
            gameStart = 1;
        }
        if (newGame(250, 250) == true) {
            saveLevel();
        }
    }

    public int levelNumber() {
        int levelNumber = 1;
        try (Scanner scanner = new Scanner(Paths.get("file.txt"))) {
            while (scanner.hasNextLine()) {
                int number = Integer.valueOf(scanner.nextLine());
                levelNumber = number;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return levelNumber;
    }

    public void saveLevel() {
        try (PrintWriter writer = new PrintWriter("file.txt")) {
            writer.println(level);
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
        gameStart = 0;
        for (Balls i : balls) {
            i.reset();
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
        text("New game", 290, 160);
        text("Saved game", 280, 310);
        text("How to play", 275, 460);
        if (newGame(250, 100) == true) {
            gameStart = 1;
        }
        if (newGame(250, 400) == true) {
            gameStart = 3;
        }
        if (newGame(250, 250) == true) {
            level = levelNumber();
            settingUp();
            gameStart = 1;
        }
    }

    public void tutorialScreen() {
        background(0);
        strokeWeight(4);
        textSize(25);
        fill(255);
        text("Use the paddle to prevent the ball from hitting the bottom, and to", 50, 50);
        text("get the ball to hit and destroy the bricks. Use the paddle by moving", 50, 80);
        text("your mouse. The goal of the game is to destroy all the bricks.", 50, 110);
        text("Don't let the purple ball leave the screen. Use 'P' to pause.", 50, 140);
        stroke(255);
        strokeWeight(5);
        fill(0);
        rect(250, 200, 300, 100);
        rect(250, 350, 300, 100);
        fill(255);
        textSize(50);
        text("New game", 290, 260);
        text("Saved game", 280, 410);
        if (newGame(250, 200) == true) {
            gameStart = 1;
        }
        if (newGame(250, 350) == true) {
            level = levelNumber();
            settingUp();
            gameStart = 1;
        }
    }

    public void mousePressed() {
        if (gameStart == 0) {

        }
    }

    public boolean newGame(int buttonX, int buttonY) {
        if (mousePressed && mouseX >= buttonX && mouseX <= buttonX + 300 && mouseY >= buttonY && mouseY <= buttonY + 100) {
            return true;
        } else {
            return false;
        }
    }

    public void endScreen() {
        background(0);
        fill(0);
        stroke(255);
        strokeWeight(5);
        rect(250, 250, 300, 100);
        rect(250, 400, 300, 100);
        fill(255);
        textSize(45);
        text("You lose.", 325, 150);
        text("Save level", 295, 310);
        text("Play again", 300, 460);
        if (newGame(250, 400) == true) {
            for (Balls i : balls) {
                i.reset();
            }
            for (int y = 5; y < 100; y += 25) {
                for (int x = 0; x <= 770; x += 35) {
                    Blocks temp = new Blocks(x, y, level, this);
                    blocks.add(temp);
                }
            }
            gameStart = 1;
        }
        if (newGame(250, 250) == true) {
            saveLevel();
        }
    }
}