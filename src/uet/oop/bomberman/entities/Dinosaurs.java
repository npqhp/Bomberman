package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public class Dinosaurs extends Entity {
    private boolean dead = false;
    private int i_img = 0;
    private long time = 0;
    private double runX;
    private double runY;
    private int direction = 0;

    public Dinosaurs(double x, double y, Image img) {
        super(x, y, img);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public boolean getDead() {
        return this.dead;
    }

    public void setDead(boolean dead) {
        if (dead && !this.dead) {
            time = 0;
            i_img = 0;
        }
        this.dead = dead;
    }

    public double getRunX() {
        return runX;
    }

    public double getRunY() {
        return runY;
    }

    public void not_way(long l){
        if (l - time < 500_000_000) return;
        time = l;
        switch (direction) {
            case 0:
                img = Sprite.dinosaurs_right1.getFxImage();
                direction = 1;
                break;
            case 1:
                img = Sprite.dinosaurs_left1.getFxImage();
                direction = 0;
                break;
            case 2:
                img = Sprite.dinosaurs_down1.getFxImage();
                direction = 3;
                break;
            case 3:
                img = Sprite.dinosaurs_up1.getFxImage();
                direction = 2;
                break;
        }
    }

    public void setDirection(int direction) {
        this.direction = direction;
        switch (direction) {
            case 0:
                runX = 0;
                runY = -0.25;
                break;
            case 1:
                runY = 0.25;
                runX = 0;
                break;
            case 2:
                runX = -0.25;
                runY = 0;
                break;
            default:
                runX = 0.25;
                runY = 0;
        }
    }

    public void next(long l) {
        if (l - time < 400_000_000) return;
        time = l;
        switch (direction) {
            case 0:
                switch (i_img) {
                    case 0:
                        img = Sprite.dinosaurs_left.getFxImage();
                        break;
                    case 1:
                        img = Sprite.dinosaurs_left1.getFxImage();
                        break;
                    case 2:
                        img = Sprite.dinosaurs_left2.getFxImage();
                        break;
                }
                break;
            case 1:
                switch (i_img) {
                    case 0:
                        img = Sprite.dinosaurs_right.getFxImage();
                        break;
                    case 1:
                        img = Sprite.dinosaurs_right1.getFxImage();
                        break;
                    case 2:
                        img = Sprite.dinosaurs_right2.getFxImage();
                        break;
                }
                break;
            case 2:
                switch (i_img) {
                    case 0:
                        img = Sprite.dinosaurs_up.getFxImage();
                        break;
                    case 1:
                        img = Sprite.dinosaurs_up1.getFxImage();
                        break;
                    case 2:
                        img = Sprite.dinosaurs_up2.getFxImage();
                        break;
                }
                break;
            case 3:
                switch (i_img) {
                    case 0:
                        img = Sprite.dinosaurs_down.getFxImage();
                        break;
                    case 1:
                        img = Sprite.dinosaurs_down1.getFxImage();
                        break;
                    case 2:
                        img = Sprite.dinosaurs_down2.getFxImage();
                        break;
                }
                break;
        }
        i_img = (i_img + 1) % 3;
        x = x + runX;
        y = y + runY;
    }

    public void next_dead(long l){
        if (l - time < 800_000_000) return;
        time = l;
        switch (i_img) {
            case 0:
                img = Sprite.dinosaurs_dead.getFxImage();
                break;
            case 1:
                img = Sprite.dinosaurs_dead1.getFxImage();
                break;
            case 2:
                img = Sprite.dinosaurs_dead2.getFxImage();
                break;
        }
        i_img++;
    }

    public void update_win(long l) {
        if (l - time < 800_000_000) return;
        time = l;
        switch (i_img % 2) {
            case 0:
                img = Sprite.dinosaurs_down.getFxImage();
                break;
            case 1:
                img = Sprite.dinosaurs_down2.getFxImage();
                break;
        }
        i_img++;
    }

    public int getI_img() {
        return this.i_img;
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(img, (y + .5) * Sprite.SCALED_SIZE, (x - 1) * Sprite.SCALED_SIZE);
    }
}
