package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Item extends Entity {
    private int i_img;
    private long time = 0;

    public Item(double x, double y, Image img) {
        super(x, y, img);
        i_img = 0;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void update(long l, char c, boolean win) {
        if (c == 'x') {
            if (!win) {
                if (l - time < 100_000_000) return;
                time = l;
                switch (i_img) {
                    case 0:
                        img = Sprite.portal.getFxImage();
                        break;
                    case 1:
                        img = Sprite.portal1.getFxImage();
                        break;
                    case 2:
                        img = Sprite.portal2.getFxImage();
                        break;
                    case 3:
                        img = Sprite.portal3.getFxImage();
                        break;
                    case 4:
                        img = Sprite.portal4.getFxImage();
                        break;
                }
                i_img = (i_img + 1) % 5;
            } else {
                if (l - time < 50_000_000) return;
                time = l;
                switch (i_img) {
                    case 0:
                        img = Sprite.portal_next.getFxImage();
                        break;
                    case 1:
                        img = Sprite.portal_next1.getFxImage();
                        break;
                    case 2:
                        img = Sprite.portal_next2.getFxImage();
                        break;
                    case 3:
                        img = Sprite.portal_next3.getFxImage();
                        break;
                    case 4:
                        img = Sprite.portal_next4.getFxImage();
                        break;
                    case 5:
                        img = Sprite.portal_next5.getFxImage();
                        break;
                    case 6:
                        img = Sprite.portal_next6.getFxImage();
                        break;
                    case 7:
                        img = Sprite.portal_next7.getFxImage();
                        break;
                    case 8:
                        img = Sprite.portal_next8.getFxImage();
                        break;
                    case 9:
                        img = Sprite.portal_next9.getFxImage();
                        break;
                    case 10:
                        img = Sprite.portal_next10.getFxImage();
                        break;
                    case 11:
                        img = Sprite.portal_next11.getFxImage();
                        break;
                    case 12:
                        img = Sprite.portal_next12.getFxImage();
                        break;
                    case 13:
                        img = Sprite.portal_next13.getFxImage();
                        break;
                    case 14:
                        img = Sprite.portal_next14.getFxImage();
                        break;
                    case 15:
                        img = Sprite.portal_next15.getFxImage();
                        break;
                    case 16:
                        img = Sprite.portal_next16.getFxImage();
                        break;

                }
                i_img = (i_img + 1) % 17;
            }
            return;
        }
        if (l - time > 300_000_000) {
            time = l;
            switch (c) {
                case 'b':
                    if (i_img == 0) img = Sprite.powerup_bombs.getFxImage();
                    else img = Sprite.powerup_bombs1.getFxImage();
                    break;
                case 'f':
                    if (i_img == 0) img = Sprite.powerup_flames.getFxImage();
                    else img = Sprite.powerup_flames1.getFxImage();
                    break;
                case 's':
                    if (i_img == 0) img = Sprite.powerup_speed.getFxImage();
                    else img = Sprite.powerup_speed1.getFxImage();
                    break;
            }
            i_img = 1 - i_img;
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(img, (y + .5) * Sprite.SCALED_SIZE, (x -.5) * Sprite.SCALED_SIZE);
    }
}
