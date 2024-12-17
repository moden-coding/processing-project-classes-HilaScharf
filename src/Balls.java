import processing.core.PApplet;

public class Balls {
    private int boxX;
    private int boxY;
    private PApplet a;
    private int damage;
    private int directionX;
    private int directionY;
    private int speedX;
    private int speedY;
    private boolean lose;

    public Balls(int X, int Y, PApplet c, int strength) {
        boxX = X;
        boxY = Y;
        a = c;
        damage = strength;
        directionX = 1;
        directionY = -1;
        speedX = (int)a.random(2, 7);
        speedY = (int)a.random(2, 7);
        lose = false;
    }

    private int ballColor() {
        if (damage == 1) {
            return 255;
        } else {
            return a.color(105, 10, 240);
        }
    }

    public void make() {
        a.fill(ballColor());
        a.rect(boxX, boxY, 20, 20);
    }

    public void move() {
        boxX += speedX * directionX;
        boxY += speedY * directionY;
        if (boxX + 20 >= a.width || boxX <= 0) {
            directionX = directionX * -1;
        }
        if (boxY + 20 >= a.height) {
            lose = true;
        } 
        if(boxY <= 0) {
            directionY = directionY * -1;
        }
    }
    public boolean lose() {
        if(lose == true) {
            return true;
        } else {
            return false;
        }
    }

    public boolean touching(int blockTop, int blockBottom, int blockRight, int blockLeft) {
        if (boxY <= blockBottom && boxX + 20 >= blockLeft && boxX <= blockRight) {
            return true;
        } else if (boxY + 20 < blockTop && boxX + 20 > blockLeft && boxX < blockRight) {
            return true;
        } else if (boxY > blockTop && boxY + 20 < blockBottom && boxX + 20 > blockLeft && boxX < blockLeft) {
            return true;
        } else if (boxY > blockTop && boxY + 20 < blockBottom && boxX < blockRight && boxX + 20 > blockRight) {
            return true;
        } else {
            return false;
        }
    }

    // bounce off blocks
    public void bounce(int blockTop, int blockBottom, int blockRight, int blockLeft) {
        //bounce off bottom
        if (boxY < blockBottom && boxY > blockTop && boxX + 20 > blockLeft && boxX < blockRight) {
            directionY = directionY * -1;
            boxY += 10;
            speedX = (int) a.random(2, 7);
            speedY = (int) a.random(2, 7);
        }
        //bounce off top
        if(boxY + 20 > blockTop && boxY < blockTop && boxX + 20 > blockLeft && boxX < blockRight) {
            directionY = directionY * -1;
            boxY -= 10;
            speedX = (int) a.random(2, 7);
            speedY = (int) a.random(2, 7);
        }
        //bounce off left
        if (boxY > blockTop && boxY + 20 < blockBottom && boxX + 20 > blockLeft && boxX < blockLeft) {
            boxX -= 10;
            directionX = directionX * -1;
            speedX = (int) a.random(2, 7);
            speedY = (int) a.random(2, 7);
        }
        //bounce off right
        if (boxY > blockTop && boxY + 20 < blockBottom && boxX < blockRight && boxX + 20 > blockRight) {
            boxX += 10;
            directionX = directionX * -1;
            speedX = (int) a.random(2, 7);
            speedY = (int) a.random(2, 7);
        }
    }

    public int damage() {
        return damage;
    }

    public int x() {
        return boxX;
    }

    public int y() {
        return boxY;
    }
}
