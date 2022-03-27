package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Deep extends Entity{
    private int direction;
    private long time = 0;
    private int i_img;
    private boolean pause;

    public Deep(double x, double y, Image img) {
        super(x, y, img);
    }

    public Deep(double x, double y, Image img,int direction) {
        super(x, y, img);
        this.direction = direction;
        this.time = 0;
        this.i_img = 0;
        this.pause = false;
    }

    public void update(long l,int speed) {
        if (l - time < speed * 10_000_000) return;
        time = l;
        switch (direction) {
            case 0:
                switch (i_img) {
                    case 0:
                        img = Sprite.deep_right3.getFxImage();
                        break;
                    case 1:
                        img = Sprite.deep_right2.getFxImage();
                        break;
                    case 2:
                        img = Sprite.deep_right1.getFxImage();
                        break;
                    case 3:
                        img = Sprite.deep_right.getFxImage();
                        break;
                }
                y = y - .25;
                break;
            case 1:
                switch (i_img) {
                    case 0:
                        img = Sprite.deep_right.getFxImage();
                        break;
                    case 1:
                        img = Sprite.deep_right1.getFxImage();
                        break;
                    case 2:
                        img = Sprite.deep_right2.getFxImage();
                        break;
                    case 3:
                        img = Sprite.deep_right3.getFxImage();
                        break;
                }
                y = y + .25;
                break;
            case 2:
                switch (i_img) {
                    case 0:
                        img = Sprite.deep_up.getFxImage();
                        break;
                    case 1:
                        img = Sprite.deep_up1.getFxImage();
                        break;
                    case 2:
                        img = Sprite.deep_up2.getFxImage();
                        break;
                    case 3:
                        img = Sprite.deep_up3.getFxImage();
                        break;
                }
                x = x - .25;
                break;
            case 3:
                switch (i_img) {
                    case 0:
                        img = Sprite.deep_up3.getFxImage();
                        break;
                    case 1:
                        img = Sprite.deep_up2.getFxImage();
                        break;
                    case 2:
                        img = Sprite.deep_up1.getFxImage();
                        break;
                    case 3:
                        img = Sprite.deep_up.getFxImage();
                        break;
                }
                x = x + .25;
                break;
        }
        i_img = (i_img + 1) % 4;
    }

    public void update_win(long l){
        if (l - time < 500_000_000) return;
        time = l;
        switch (i_img % 2) {
            case 0:
                img = Sprite.deep_win.getFxImage();
                break;
            case 1:
                img = Sprite.deep_win1.getFxImage();
                break;
        }
        i_img++;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public boolean isPause(){
        if(!pause){
            pause = true;
            return false;
        }
        return true;
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(img, (y + .5) * Sprite.SCALED_SIZE, (x - 1) * Sprite.SCALED_SIZE);
    }
}
