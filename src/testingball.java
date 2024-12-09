import java.util.ArrayList;
import processing.core.PApplet;

public class testingball {
    private int ballX;
    private int ballY;
    private int damage;
    private PApplet a;
    private ArrayList<block> level;
    private int speedX;
    private int speedY;
    private int positiveOrNegative;
    private int ballDirection;
    private int ballDirectionX;
    private int ballDirectionY;
    public testingball(int x, int y, int ballDamage, PApplet c) {
        ballX = x;
        ballY = y;
        damage = ballDamage;
        a = c;
        //level = blocks;
        speedX = (int) a.random(1, 5);
        speedY = (int) a.random(1, 5);
        positiveOrNegative = (int) a.random(1, 2);
        if (positiveOrNegative == 1) {
            ballDirection = 1;
        } else {
            ballDirection = -1;
        }
        ballDirectionX = ballDirection;
        ballDirectionY = ballDirection;
    }

    private int ballColor(int damage) {
        if (damage == 1) {
            return 255;
        } else {
            return a.color(105, 10, 240);
        }

    }

    public void move() {
        a.fill(ballColor(damage));
        a.circle(ballX, ballY, 20);
        ballX = a.mouseX;
        ballY = a.mouseY;
        if (ballX + 10 > 800 || ballX - 10 < 0) {
            System.out.println("ive hit the left or right wall");
        }
        if (ballY + 10 > 600 || ballY - 10 < 0) {
            System.out.println("ive hit the top or bottom wall");
        }
    }
    public int Xpos() {
        return ballX;
    }
    public int Ypos() {
        return ballY;
    }
    public void bounce(int objXPos, int objWidth, int objYPos, int objHeight) {
        int ballLeft = ballX - 10;
        int ballRight = ballX + 10;
        int ballTop = ballY - 10;
        int ballBottom = ballY + 10;
        int objBottom = objYPos + objHeight;
        int objLeftSide = objXPos + objWidth;
        //bounce off top of object
        /*if(ballBottom > objYPos && ballBottom < objBottom && ballRight > objXPos && ballLeft < objLeftSide) {
            System.out.println("ive hit the top of the object");
        }*/
        //bounce off left of object
        if(ballTop > objYPos && ballBottom < objBottom && ballRight > objXPos && ballRight < objLeftSide) {
            System.out.println("ive hit the left side of the object");
        }
        //bounce off bottom of object
       /*  if(ballRight > objXPos && ballLeft < objLeftSide && ballTop < objBottom && ballTop > objYPos) {
            System.out.println("ive hit the bottom side of the object");
        }*/
        if(ballRight < objXPos && ballLeft > objLeftSide && ballBottom < objBottom && ballTop > objYPos) {
            System.out.println("ive hit the right side of the object");
        }
    }
}
