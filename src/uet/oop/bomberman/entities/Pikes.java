package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Pikes extends Entity {
    private int direction;
    private long time;
    private int index;

    public Pikes(double x, double y, Image img) {
        super(x, y, img);
    }

    public Pikes(double x, double y, Image img, int direction,int index) {
        super(x, y, img);
        switch (direction) {
            case 0:
                this.y = this.y - 1;
                this.img = Sprite.aborigines_pike_left.getFxImage();
                break;
            case 1:
                this.y = this.y + 1;
                this.img = Sprite.aborigines_pike_right.getFxImage();
                break;
            case 2:
                this.x = this.x - 1;
                this.img = Sprite.aborigines_pike_up.getFxImage();
                break;
            default:
                this.x = this.x + 1;
                this.img = Sprite.aborigines_pike_down.getFxImage();
        }
        this.index = index;
        this.direction = direction;
        time = 0;
    }

    public void update(long l) {
        if (l - time < 100_000_000) return;
        time = l;
        switch (direction) {
            case 0:
                y = y - 0.2;
                break;
            case 1:
                y = y + 0.2;
                break;
            case 2:
                x = x - 0.2;
                break;
            default:
                x = x + 0.2;
        }
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getIndex() {
        return index;
    }

    public int getDirection() {
        return direction;
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(img, (y + .5) * Sprite.SCALED_SIZE, (x - .5) * Sprite.SCALED_SIZE);
    }
}