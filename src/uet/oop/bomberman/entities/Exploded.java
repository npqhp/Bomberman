package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Exploded extends Entity {
    private int length;
    private int i_img;
    private int index;
    private long time;

    public Exploded(double x, double y, Image img) {
        super(x, y, img);
    }

    public Exploded(double x, double y, int length, int index, int i_img, long time) {
        super(x, y, Sprite.bomb_exploded.getFxImage());
        this.length = length;
        this.i_img = i_img;
        this.index = index;
        this.time = time;
    }

    public Exploded(double x, double y, int direction, int i_img) {
        super(x, y, Sprite.bomb_exploded.getFxImage());
        //if (direction == 6) direction = 5;
        //if (direction == 8) direction = 7;
        switch (direction) {
            case 0:
                switch (i_img) {
                    case 0:
                        this.img = Sprite.bomb_exploded.getFxImage();
                        break;
                    case 1:
                        this.img = Sprite.bomb_exploded1.getFxImage();
                        break;
                    case 2:
                        this.img = Sprite.bomb_exploded2.getFxImage();
                        break;
                    case 3:
                        this.img = Sprite.bomb_exploded3.getFxImage();
                        break;
                    case 4:
                        this.img = Sprite.bomb_exploded4.getFxImage();
                        break;
                }
                break;
            case 3:
                switch (i_img) {
                    case 0:
                        this.img = Sprite.explosion_left_last.getFxImage();
                        break;
                    case 1:
                        this.img = Sprite.explosion_left_last1.getFxImage();
                        break;
                    case 2:
                        this.img = Sprite.explosion_left_last2.getFxImage();
                        break;
                    case 3:
                        this.img = Sprite.explosion_left_last3.getFxImage();
                        break;
                    case 4:
                        this.img = Sprite.explosion_left_last4.getFxImage();
                        break;
                }
                break;
            case 4:
                switch (i_img) {
                    case 0:
                        this.img = Sprite.explosion_right_last.getFxImage();
                        break;
                    case 1:
                        this.img = Sprite.explosion_right_last1.getFxImage();
                        break;
                    case 2:
                        this.img = Sprite.explosion_right_last2.getFxImage();
                        break;
                    case 3:
                        this.img = Sprite.explosion_right_last3.getFxImage();
                        break;
                    case 4:
                        this.img = Sprite.explosion_right_last4.getFxImage();
                        break;
                }
                break;
            case 1:
                switch (i_img) {
                    case 0:
                        this.img = Sprite.explosion_up_last.getFxImage();
                        break;
                    case 1:
                        this.img = Sprite.explosion_up_last1.getFxImage();
                        break;
                    case 2:
                        this.img = Sprite.explosion_up_last2.getFxImage();
                        break;
                    case 3:
                        this.img = Sprite.explosion_up_last3.getFxImage();
                        break;
                    case 4:
                        this.img = Sprite.explosion_up_last4.getFxImage();
                        break;
                }
                break;
            case 2:
                switch (i_img) {
                    case 0:
                        this.img = Sprite.explosion_down_last.getFxImage();
                        break;
                    case 1:
                        this.img = Sprite.explosion_down_last1.getFxImage();
                        break;
                    case 2:
                        this.img = Sprite.explosion_down_last2.getFxImage();
                        break;
                    case 3:
                        this.img = Sprite.explosion_down_last3.getFxImage();
                        break;
                    case 4:
                        this.img = Sprite.explosion_down_last4.getFxImage();
                        break;
                }
                break;
            case 7:
                switch (i_img) {
                    case 0:
                        this.img = Sprite.explosion_left.getFxImage();
                        break;
                    case 1:
                        this.img = Sprite.explosion_left1.getFxImage();
                        break;
                    case 2:
                        this.img = Sprite.explosion_left2.getFxImage();
                        break;
                    case 3:
                        this.img = Sprite.explosion_left3.getFxImage();
                        break;
                    case 4:
                        this.img = Sprite.explosion_left4.getFxImage();
                        break;
                }
                break;
            case 8:
                switch (i_img) {
                    case 0:
                        this.img = Sprite.explosion_right.getFxImage();
                        break;
                    case 1:
                        this.img = Sprite.explosion_right1.getFxImage();
                        break;
                    case 2:
                        this.img = Sprite.explosion_right2.getFxImage();
                        break;
                    case 3:
                        this.img = Sprite.explosion_right3.getFxImage();
                        break;
                    case 4:
                        this.img = Sprite.explosion_right4.getFxImage();
                        break;
                }
                break;
            case 5:
                switch (i_img) {
                    case 0:
                        this.img = Sprite.explosion_up.getFxImage();
                        break;
                    case 1:
                        this.img = Sprite.explosion_up1.getFxImage();
                        break;
                    case 2:
                        this.img = Sprite.explosion_up2.getFxImage();
                        break;
                    case 3:
                        this.img = Sprite.explosion_up3.getFxImage();
                        break;
                    case 4:
                        this.img = Sprite.explosion_up4.getFxImage();
                        break;
                }
                break;
            default:
                switch (i_img) {
                    case 0:
                        this.img = Sprite.explosion_down.getFxImage();
                        break;
                    case 1:
                        this.img = Sprite.explosion_down1.getFxImage();
                        break;
                    case 2:
                        this.img = Sprite.explosion_down2.getFxImage();
                        break;
                    case 3:
                        this.img = Sprite.explosion_down3.getFxImage();
                        break;
                    case 4:
                        this.img = Sprite.explosion_down4.getFxImage();
                        break;
                }
        }

    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getI_img() {
        return this.i_img;
    }

    public int getIndex() {
        return this.index;
    }

    public int getLength() {
        return length;
    }

    public long getTime() {
        return this.time;
    }

    public boolean setTime(long l) {
        if (l - time < 100000000) return false;
        time = l;
        return true;
    }

    public void setI_img(int i_img) {
        this.i_img = i_img;
    }

    @Override
    public void render(GraphicsContext gc){
        gc.drawImage(img, (y + .5) * Sprite.SCALED_SIZE, (x - .5) * Sprite.SCALED_SIZE);
    }

}