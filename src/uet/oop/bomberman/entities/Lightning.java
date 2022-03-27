package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public class Lightning extends Entity {
    private int i_img = 0;
    private long time = 0;
    private double runX;
    private double runY;
    private int direction = 0;

    public Lightning(double x, double y, Image img) {
        super(x, y, img);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
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
        switch (i_img) {
            case 0:
                img = Sprite.lightning.getFxImage();
                break;
            case 1:
                img = Sprite.lightning1.getFxImage();
                break;
            case 2:
                img = Sprite.lightning2.getFxImage();
        }
        i_img = (i_img + 1) % 3;
        x = x + runX;
        y = y + runY;
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(img, (y + .5) * Sprite.SCALED_SIZE, (x - .5) * Sprite.SCALED_SIZE);
    }
}
