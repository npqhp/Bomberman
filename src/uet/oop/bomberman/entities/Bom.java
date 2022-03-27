package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Bom extends Entity {
    private int i_img;
    private long time;
    private int length;
    private boolean run = true;

    public Bom(double x, double y, Image img) {
        super(x, y, img);
    }

    public Bom(double x, double y, Image img, long time, int i_img, int length) {
        super(x, y, img);
        this.i_img = i_img;
        this.time = time;
        this.length = length;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getLength() {
        return this.length;
    }

    public void setI_img(int i_img) {
        this.i_img = i_img;
    }

    public int getI_img() {
        return this.i_img;
    }

    public void setRun(boolean run) {
        this.run = run;
    }

    public boolean getRun() {
        return this.run;
    }

    public void update(long l, boolean up, boolean down) {
        if (l - time < 100000000) return;
        time = l;
        if (i_img == 23) run = false;
        int i = i_img % 4;
        if (!up && !down) {
            switch (i) {
                case 0:
                    i_img++;
                    this.img = Sprite.bomb.getFxImage();
                    break;
                case 1:
                    this.img = Sprite.bomb_1.getFxImage();
                    i_img++;
                    break;
                case 2:
                    i_img++;
                    this.img = Sprite.bomb_2.getFxImage();
                    break;
                case 3:
                    i_img++;
                    this.img = Sprite.bomb_3.getFxImage();
                    break;
            }
            return;
        }
        if (up && !down) {
            switch (i) {
                case 0:
                    i_img++;
                    this.img = Sprite.bomb_4.getFxImage();
                    break;
                case 1:
                    this.img = Sprite.bomb_5.getFxImage();
                    i_img++;
                    break;
                case 2:
                    i_img++;
                    this.img = Sprite.bomb_6.getFxImage();
                    break;
                case 3:
                    i_img++;
                    this.img = Sprite.bomb_7.getFxImage();
                    break;
            }
            return;
        }
        if (!up && down){
            switch (i) {
                case 0:
                    i_img++;
                    this.img = Sprite.bomb_8.getFxImage();
                    break;
                case 1:
                    this.img = Sprite.bomb_9.getFxImage();
                    i_img++;
                    break;
                case 2:
                    i_img++;
                    this.img = Sprite.bomb_10.getFxImage();
                    break;
                case 3:
                    i_img++;
                    this.img = Sprite.bomb_11.getFxImage();
                    break;
            }
            return;
        }
        switch (i) {
            case 0:
                i_img++;
                this.img = Sprite.bomb_12.getFxImage();
                break;
            case 1:
                this.img = Sprite.bomb_13.getFxImage();
                i_img++;
                break;
            case 2:
                i_img++;
                this.img = Sprite.bomb_14.getFxImage();
                break;
            case 3:
                i_img++;
                this.img = Sprite.bomb_15.getFxImage();
                break;
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(img, (y + .5) * Sprite.SCALED_SIZE, (x - .5) * Sprite.SCALED_SIZE);
    }
}
