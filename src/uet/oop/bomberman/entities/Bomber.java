package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends Entity {
    private int direction;
    private int status;
    private double width = 1;
    private double height = 1.5;
    private long speed = 60_000_000;
    private long time = 0;
    private boolean dead = false;
    private boolean cant_dead = true;

    public Bomber(double x, double y, Image img) {
        super(x, y, img);
        direction = 4;
        status = 0;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public boolean getDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        if (!this.dead && dead && !cant_dead) {
            status = 0;
            time = 0;
        }
        this.dead = dead;
    }

    public void update(int key) {
        switch (key) {
            case 1:
                switch (status) {
                    case 0:
                        this.img = Sprite.player_up1.getFxImage();
                        this.status++;
                        break;
                    case 1:
                        this.img = Sprite.player_up2.getFxImage();
                        this.status++;
                        break;
                    case 2:
                        this.img = Sprite.player_up3.getFxImage();
                        this.status++;
                        break;
                    default:
                        this.img = Sprite.player_up.getFxImage();
                        this.status = 0;
                }
                break;
            case 2:
                switch (status) {
                    case 0:
                        this.img = Sprite.player_down1.getFxImage();
                        this.status++;
                        break;
                    case 1:
                        this.img = Sprite.player_down2.getFxImage();
                        this.status++;
                        break;
                    case 2:
                        this.img = Sprite.player_down3.getFxImage();
                        this.status++;
                        break;
                    default:
                        this.img = Sprite.player_down.getFxImage();
                        this.status = 0;
                }
                break;
            case 3:
                switch (status) {
                    case 0:
                        this.img = Sprite.player_left1.getFxImage();
                        this.status++;
                        break;
                    case 1:
                        this.img = Sprite.player_left2.getFxImage();
                        this.status++;
                        break;
                    case 2:
                        this.img = Sprite.player_left3.getFxImage();
                        this.status++;
                        break;
                    default:
                        this.img = Sprite.player_left.getFxImage();
                        this.status = 0;
                }
                break;
            default:
                switch (status) {
                    case 0:
                        this.img = Sprite.player_right1.getFxImage();
                        this.status++;
                        break;
                    case 1:
                        this.img = Sprite.player_right2.getFxImage();
                        this.status++;
                        break;
                    case 2:
                        this.img = Sprite.player_right3.getFxImage();
                        this.status++;
                        break;
                    default:
                        this.img = Sprite.player_right.getFxImage();
                        this.status = 0;
                }
        }
    }

    public void update_speed() {
        speed -= 10_000_000;
    }

    public void update_dead(long l) {
        if (l - time > 1_000_000_000) {
            time = l;
            switch (status) {
                case 0:
                    img = Sprite.player_dead.getFxImage();
                    break;
                case 1:
                    img = Sprite.player_dead1.getFxImage();
                    break;
                case 2:
                    img = Sprite.player_dead2.getFxImage();
                    break;
                case 3:
                    img = Sprite.player_dead3.getFxImage();
                    break;
            }
            status++;
        }
    }

    public int getStatus() {
        return status;
    }

    public boolean check_time(long l) {
        if (l - time > speed) {
            time = l;
            return true;
        }
        return false;
    }

    public void run(double runX, double runY) {
        x += runX;
        y += runY;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public Image getImg() {
        return img;
    }

    public void setCant_dead(boolean cant_dead) {
        this.cant_dead = cant_dead;
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(img, (y + .5) * Sprite.SCALED_SIZE, (x - 1) * Sprite.SCALED_SIZE);
    }
}
