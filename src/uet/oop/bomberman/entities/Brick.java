package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Brick extends Entity {

    public Brick(double x, double y, Image img) {
        super(x, y, img);
    }

    public Brick(double x, double y, int i_img, boolean up) {
        super(x, y, Sprite.brick.getFxImage());
        if (up) {
            switch (i_img) {
                case 0:
                    this.img = Sprite.brick_exploded.getFxImage();
                    break;
                case 1:
                    this.img = Sprite.brick_exploded1.getFxImage();
                    break;
                case 2:
                    this.img = Sprite.brick_exploded2.getFxImage();
                    break;
                case 3:
                    this.img = Sprite.brick_exploded3.getFxImage();
                    break;
                case 4:
                    this.img = Sprite.brick_exploded4.getFxImage();
                    break;
            }
        } else {
            switch (i_img) {
                case 0:
                    this.img = Sprite.brick_up_exploded.getFxImage();
                    break;
                case 1:
                    this.img = Sprite.brick_up_exploded1.getFxImage();
                    break;
                case 2:
                    this.img = Sprite.brick_up_exploded2.getFxImage();
                    break;
                case 3:
                    this.img = Sprite.brick_up_exploded3.getFxImage();
                    break;
                case 4:
                    this.img = Sprite.brick_up_exploded4.getFxImage();
                    break;
            }
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(img, (y + .5) * Sprite.SCALED_SIZE, (x - .5) * Sprite.SCALED_SIZE);
    }
}
