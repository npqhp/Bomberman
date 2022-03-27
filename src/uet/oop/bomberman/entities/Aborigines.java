package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Aborigines extends Entity {
    private boolean dead = false;
    private int i_img = 0;
    private long time = 0;
    private double runX;
    private double runY;
    private int direction = 0;
    private int index;
    private int i_time = 2;

    public Aborigines(double x, double y, Image img) {
        super(x, y, img);
    }

    public Aborigines(double x, double y, Image img, int index) {
        super(x, y, img);
        this.index = index;
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

    public boolean next(long l, boolean check_pike) {
        if (l - time < i_time * 80_000_000) return false;
        time = l;
        if (check_pike) {
            switch (direction) {
                case 0:
                    img = Sprite.aborigines_throw_left.getFxImage();
                    break;
                case 1:
                    img = Sprite.aborigines_throw_right.getFxImage();
                    break;
                case 2:
                    img = Sprite.aborigines_throw_up.getFxImage();
                    break;
                case 3:
                    img = Sprite.aborigines_throw_down.getFxImage();
                    break;
            }
            i_time = 7;
            return true;
        }
        i_time = 4; 
        switch (direction) {
            case 0:
                switch (i_img) {
                    case 0:
                        img = Sprite.aborigines_left.getFxImage();
                        break;
                    case 1:
                        img = Sprite.aborigines_left1.getFxImage();
                        break;
                    case 2:
                        img = Sprite.aborigines_left2.getFxImage();
                        break;
                }
                break;
            case 1:
                switch (i_img) {
                    case 0:
                        img = Sprite.aborigines_right.getFxImage();
                        break;
                    case 1:
                        img = Sprite.aborigines_right1.getFxImage();
                        break;
                    case 2:
                        img = Sprite.aborigines_right2.getFxImage();
                        break;
                }
                break;
            case 2:
                switch (i_img) {
                    case 0:
                        img = Sprite.aborigines_up.getFxImage();
                        break;
                    case 1:
                        img = Sprite.aborigines_up1.getFxImage();
                        break;
                    case 2:
                        img = Sprite.aborigines_up2.getFxImage();
                        break;
                }
                break;
            case 3:
                switch (i_img) {
                    case 0:
                        img = Sprite.aborigines_down.getFxImage();
                        break;
                    case 1:
                        img = Sprite.aborigines_down1.getFxImage();
                        break;
                    case 2:
                        img = Sprite.aborigines_down2.getFxImage();
                        break;
                }
                break;
        }
        i_img = (i_img + 1) % 3;
        x = x + runX;
        y = y + runY;
        return false;
    }

    public void next_dead(long l) {
        if (l - time < 800_000_000) return;
        time = l;
        switch (i_img) {
            case 0:
                img = Sprite.aborigines_dead.getFxImage();
                break;
            case 1:
                img = Sprite.aborigines_dead1.getFxImage();
                break;
            case 2:
                img = Sprite.aborigines_dead2.getFxImage();
                break;
        }
        i_img++;
    }

    public void not_way(long l) {
        if (l - time < 500_000_000) return;
        time = l;
        switch (direction) {
            case 0:
                img = Sprite.aborigines_right1.getFxImage();
                direction = 1;
                break;
            case 1:
                img = Sprite.aborigines_left1.getFxImage();
                direction = 0;
                break;
            case 2:
                img = Sprite.aborigines_down1.getFxImage();
                direction = 3;
                break;
            case 3:
                img = Sprite.aborigines_up1.getFxImage();
                direction = 2;
                break;
        }
    }

    public int getI_img() {
        return this.i_img;
    }

    public int getDirection() {
        return direction;
    }

    public void update_win(long l) {
        if (l - time < 800_000_000) return;
        time = l;
        switch (i_img % 2) {
            case 0:
                img = Sprite.aborigines_down2.getFxImage();
                break;
            case 1:
                img = Sprite.aborigines_win.getFxImage();
                break;
        }
        i_img++;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(img, (y + .5) * Sprite.SCALED_SIZE, (x - 1) * Sprite.SCALED_SIZE);
    }
}
