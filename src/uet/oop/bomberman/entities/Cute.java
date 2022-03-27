package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Cute extends Entity {
    private int i_img = 0;
    private long time = 0;
    private double runX;
    private double runY;
    private int direction = 0;
    private boolean dead = false;

    public Cute(double x, double y, Image img) {
        super(x, y, img);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getRunX() {
        return runX;
    }

    public double getRunY() {
        return runY;
    }

    public void not_way(long l) {
        if (l - time < 500_000_000) return;
        time = l;
        switch (direction) {
            case 0:
                img = Sprite.cute_down.getFxImage();
                break;
            case 1:
                img = Sprite.cute_down1.getFxImage();
                break;
            case 2:
                img = Sprite.cute_down2.getFxImage();
                break;
            case 3:
                img = Sprite.cute_down3.getFxImage();
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

    public void setDirection_jump(int direction) {
        this.direction = direction;
        switch (direction) {
            case 0:
                runX = 0;
                runY = -0.5;
                break;
            case 1:
                runY = 0.5;
                runX = 0;
                break;
            case 2:
                runX = -0.5;
                runY = 0;
                break;
            default:
                runX = 0.5;
                runY = 0;
        }
    }

    public void next(long l, int t) {
        if (l - time < 400_000_000) return;
        if (t != -1) setDirection(t);
        time = l;
        switch (direction) {
            case 0:
                switch (i_img) {
                    case 0:
                        img = Sprite.cute_right3.getFxImage();
                        break;
                    case 1:
                        img = Sprite.cute_right2.getFxImage();
                        break;
                    case 2:
                        img = Sprite.cute_right1.getFxImage();
                        break;
                    case 4:
                        img = Sprite.cute_right.getFxImage();
                        break;
                }
                break;
            case 1:
                switch (i_img) {
                    case 0:
                        img = Sprite.cute_right.getFxImage();
                        break;
                    case 1:
                        img = Sprite.cute_right1.getFxImage();
                        break;
                    case 2:
                        img = Sprite.cute_right2.getFxImage();
                        break;
                    case 3:
                        img = Sprite.cute_right3.getFxImage();
                        break;
                }
                break;
            case 2:
                switch (i_img) {
                    case 0:
                        img = Sprite.cute_up.getFxImage();
                        break;
                    case 1:
                        img = Sprite.cute_up1.getFxImage();
                        break;
                    case 2:
                        img = Sprite.cute_up2.getFxImage();
                        break;
                    case 3:
                        img = Sprite.cute_up3.getFxImage();
                        break;
                }
                break;
            case 3:
                switch (i_img) {
                    case 0:
                        img = Sprite.cute_down.getFxImage();
                        break;
                    case 1:
                        img = Sprite.cute_down1.getFxImage();
                        break;
                    case 2:
                        img = Sprite.cute_down2.getFxImage();
                        break;
                    case 3:
                        img = Sprite.cute_down3.getFxImage();
                        break;
                }
                break;
        }
        i_img = (i_img + 1) % 4;
        x = x + runX;
        y = y + runY;
    }

    public void update_win(long l) {
        if (l - time < 800_000_000) return;
        time = l;
        switch (i_img % 2) {
            case 0:
                img = Sprite.cute_right.getFxImage();
                break;
            case 1:
                img = Sprite.cute_right3.getFxImage();
                break;
        }
        i_img++;
    }

    public int getI_img() {
        return this.i_img;
    }

    public void next_dead(long l) {
        if (l - time < 800_000_000) return;
        time = l;
        switch (i_img) {
            case 0:
                img = Sprite.cute_dead.getFxImage();
                x = x + .5;
                break;
            case 1:
                img = Sprite.cute_dead.getFxImage();
                break;
            case 2:
                img = Sprite.cute_dead.getFxImage();
                break;
        }
        i_img++;
    }

    public void next_jump(long l, int t){
        if (l - time < 800_000_000) return;
        if (t != -1) setDirection_jump(t);
        time = l;
        switch (direction) {
            case 0:
                switch (i_img) {
                    case 0:
                        img = Sprite.cute_right3.getFxImage();
                        break;
                    case 1:
                        img = Sprite.cute_right2.getFxImage();
                        break;
                    case 2:
                        img = Sprite.cute_right1.getFxImage();
                        break;
                    case 4:
                        img = Sprite.cute_right.getFxImage();
                        break;
                }
                break;
            case 1:
                switch (i_img) {
                    case 0:
                        img = Sprite.cute_right.getFxImage();
                        break;
                    case 1:
                        img = Sprite.cute_right1.getFxImage();
                        break;
                    case 2:
                        img = Sprite.cute_right2.getFxImage();
                        break;
                    case 3:
                        img = Sprite.cute_right3.getFxImage();
                        break;
                }
                break;
            case 2:
                switch (i_img) {
                    case 0:
                        img = Sprite.cute_up.getFxImage();
                        break;
                    case 1:
                        img = Sprite.cute_up1.getFxImage();
                        break;
                    case 2:
                        img = Sprite.cute_up2.getFxImage();
                        break;
                    case 3:
                        img = Sprite.cute_up3.getFxImage();
                        break;
                }
                break;
            case 3:
                switch (i_img) {
                    case 0:
                        img = Sprite.cute_up3.getFxImage();
                        break;
                    case 1:
                        img = Sprite.cute_up2.getFxImage();
                        break;
                    case 2:
                        img = Sprite.cute_up1.getFxImage();
                        break;
                    case 3:
                        img = Sprite.cute_up.getFxImage();
                        break;
                }
                break;
        }
        i_img = (i_img + 1) % 4;
        x = x + runX;
        y = y + runY;
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

    @Override
    public void render(GraphicsContext gc) {
        if (direction == 3) gc.drawImage(img, (y + .5) * Sprite.SCALED_SIZE, (x - .5) * Sprite.SCALED_SIZE);
        else gc.drawImage(img, (y + .5) * Sprite.SCALED_SIZE, (x - 1) * Sprite.SCALED_SIZE);
    }
}
