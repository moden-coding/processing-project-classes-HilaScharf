import java.util.ArrayList;
import processing.core.PApplet;

public class ball {
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
    public ball(int x, int y, int ballDamage, PApplet c) {
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
        ballX += ballDirectionX * speedX;
        ballY += ballDirectionY * speedY;
        if (ballX + 10 > 800 || ballX - 10 < 0) {
            int placeHolder = speedX;
            speedX = speedY;
            speedY = placeHolder;
            ballDirectionX = ballDirectionX * -1;
        }
        if (ballY + 10 > 600 || ballY - 10 < 0) {
            int placeHolder = speedX;
            speedX = speedY;
            speedY = placeHolder;
            ballDirectionY = ballDirectionY * -1;
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
        if(ballBottom > objYPos && ballBottom < objBottom && ballRight > objXPos && ballLeft < objLeftSide) {
            int placeHolder = speedX;
            speedX = speedY;
            speedY = placeHolder;
            ballDirectionY = ballDirectionY * -1;
        }
        //bounce off left of object
        if(ballTop > objYPos && ballBottom < objBottom && ballRight > objXPos && ballRight < objLeftSide) {
            int placeHolder = speedX;
            speedX = speedY;
            speedY = placeHolder;
            ballDirectionX = ballDirectionX * -1;
        }
        //bounce off bottom of object
        if(ballRight > objXPos && ballLeft < objLeftSide && ballTop < objBottom && ballTop > objYPos) {
            int placeHolder = speedX;
            speedX = speedY;
            speedY = placeHolder;
            ballDirectionY = ballDirectionY * -1;
        }
        /*if(ballRight < objXPos && ballLeft > objLeftSide && ballBottom < objBottom && ballTop > objYPos) {
            int placeHolder = speedX;
            speedX = speedY;
            speedY = placeHolder;
            ballDirectionX = ballDirectionX * -1;
        }*/
    }
}
