package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.JavaSound.Sound;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public class Lightning_Start extends Entity {
    private int i_img = 0;
    private long time = 0;

    public Lightning_Start(double x, double y, Image img) {
        super(x, y, img);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void next(long l) {
        if (l - time < 1_000_000_000) return;
        time = l;
        if(i_img == 4) new Sound("thunder.wav").start();
        switch (i_img) {
            case 0:
                img = Sprite.lightning_start1.getFxImage();
                break;
            case 1:
                img = Sprite.lightning_start.getFxImage();
                break;
            case 2:
                img = Sprite.lightning_start3.getFxImage();
                break;
            case 3:
                img = Sprite.lightning_start2.getFxImage();
                break;
            case 4:
                img = Sprite.lightning_start4.getFxImage();
                break;
            case 5:
                img = Sprite.lightning_dead.getFxImage();
                break;
        }
        i_img = (i_img + 1) % 8;
    }

    public void next_dead(long l) {
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

    public int getI_img() {
        return this.i_img;
    }

    @Override
    public void render(GraphicsContext gc) {
        if (i_img == 5 || i_img == 0 || i_img == 6)
            gc.drawImage(img, (y + .5) * Sprite.SCALED_SIZE, (x - .5) * Sprite.SCALED_SIZE);
        else gc.drawImage(img, (y) * Sprite.SCALED_SIZE, (x - 1) * Sprite.SCALED_SIZE);
    }
}
