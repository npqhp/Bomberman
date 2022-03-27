package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import uet.oop.bomberman.JavaSound.Sound;
import uet.oop.bomberman.Level.CreateLevel;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Grass;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

import static javafx.scene.paint.Color.rgb;

public class BombermanGame extends Application {
    private int width;
    private int height;

    private GraphicsContext gc;
    private GraphicsContext gc_info;
    private Canvas canvas;
    private Canvas information;
    Group root = new Group();
    private List<Entity> entities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();

    private int start_level = 3;
    private int level = 1;
    private String keyword = "";
    private boolean run = false;
    private int score = 0;
    private long time = 0;
    private int stage_start = 4;
    private int time_minutes;
    private int time_second1;
    private int time_second2;
    private int a_dead;
    private int i_time = 0;
    private int i_clock = 0;
    private long time_update = 0;
    private long time_clock = 0;
    private long time_rectangle = 0;
    private int i_next_level = 3;
    private int i_rectangle = 0;
    private boolean win = false;
    private boolean time_up = false;
    CreateLevel createLevel = new CreateLevel();

    Sound stage_sound = new Sound("Stage start.wav");
    Sound sound_win = new Sound("Ending.wav");

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        canvas = new Canvas(Sprite.SCALED_SIZE * 15, Sprite.SCALED_SIZE * 15);
        gc = canvas.getGraphicsContext2D();
        canvas.setTranslateY(2 * Sprite.SCALED_SIZE);

        root.getChildren().add(canvas);
        // Tao scene
        Scene scene = new Scene(root);
        scene.setFill(rgb(78, 250, 3));

        // Scane_start
        Label stage_level = new Label("Stage " + level);
        stage_level.setTextFill(Color.WHITE);
        stage_level.setFont(Font.font("Segoe MDL2 Assets", 64));
        Group root_start = new Group();
        root_start.getChildren().add(stage_level);
        Scene scene_start = new Scene(root_start, rgb(0, 0, 0));
        scene_start.setFill(rgb(0, 0, 0));
        stage_level.setTranslateX(8 * 32);
        stage_level.setTranslateY(5 * 32);

        // Them scene vao stage
        stage.setScene(scene_start);

        stage.setX(7 * 32);
        stage.setY(3 * 32);
        stage.show();
        //footstep.star_repeat();

        scene.setOnKeyPressed(ke -> {
            keyword = ke.getCode().toString();
            if (keyword.equals("LEFT") ||
                    keyword.equals("RIGHT") ||
                    keyword.equals("UP") ||
                    keyword.equals("DOWN")) run = true;
        });
        scene.setOnKeyReleased(ke -> {
            keyword = ke.getCode().toString();
            switch (keyword) {
                case "SPACE":
                    if (!time_up) createLevel.add_bom();
                    break;
                case "W":
                    createLevel.setWin();
                    break;
                case "N":
                    createLevel.setNextLevel();
                    i_next_level = 0;
                    break;
            }
            run = false;
        });

        stage.setOnCloseRequest(event -> {
                    createLevel.stop();
                }
        );

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (time_up) {
                    if (createLevel.isGameOver()) {
                        stage.setHeight(15 * 32);
                        stage.setWidth(25 * 32);
                        stage.setScene(scene_start);
                        stage_level.setTranslateX(6 * 32);
                        stage_level.setTranslateY(4 * 32);
                        stage_level.setText(" GAME OVER\nYour Score: " + score);
                        return;
                    }
                    updatetime(l);
                    updateClock(l);
                    updateRectangle(l);
                    createLevel.update_time_up(l, keyword, run);
                    if (createLevel.getA_dead() != a_dead) {
                        updateA_dead();
                    }

                    return;
                }
                if (win) return;
                if (start_level != -1) {
                    if (l - time < 1_000_000_000) return;
                    if (start_level < i_next_level) {
                        time = l;
                        start_level++;
                        return;
                    }
                    if (level == 7) {
                        stage.setHeight(15 * 32);
                        stage.setWidth(25 * 32);
                        stage.setScene(scene_start);
                        stage_level.setTranslateX(6 * 32);
                        stage_level.setTranslateY(4 * 32);
                        stage_level.setText(" You Win \nYour Score: " + score);
                        sound_win.start();
                        win = true;
                        return;
                    }

                    i_next_level = 3;
                    time = l;
                    stage.setScene(scene_start);
                    stage.setHeight(15 * 32);
                    stage.setWidth(25 * 32);
                    stage_level.setTranslateX(8 * 32);
                    stage_level.setTranslateY(5 * 32);
                    stage_level.setText("Stage " + level);
                    stage_sound.start();
                    stage_start = 0;
                    createLevel.setLevel(level);
                    createLevel.setGc(gc);
                    createLevel.createMap();
                    width = createLevel.getWidth();
                    height = createLevel.getHeight();
                    canvas.setHeight(height * Sprite.SCALED_SIZE);
                    canvas.setWidth((width + 1) * Sprite.SCALED_SIZE);
                    createLevel.renderLevel();
                    newTime();

                    information = new Canvas(Sprite.SCALED_SIZE * (width + 1), Sprite.SCALED_SIZE * 2);
                    gc_info = information.getGraphicsContext2D();
                    new Grass(0, (double) (width - 15) / 2, Sprite.infomation.getFxImage()).render(gc_info);
                    for (int i = 0; i < width - 15; i++) {
                        new Grass(0, (double) i / 2, Sprite.infomation_i.getFxImage()).render(gc_info);
                        new Grass(0, ((double) i / 2) + 16 + (double) (width - 15) / 2, Sprite.infomation_i.getFxImage()).render(gc_info);
                    }
                    new Grass(0.63, (double) (width - 15) / 2 + 15, getNum(level)).render(gc_info);
                    new Grass(0.58, (double) (width - 15) / 2 + 1.5, getNum(a_dead)).render(gc_info);
                    printScore(score);
                    root.getChildren().add(information);

                    level++;
                    start_level = -1;
                    return;
                }
                if (stage_start != 3) {
                    if (l - time > 1_000_000_000) {
                        time = l;
                        if (stage_start == 2) {
                            stage.setScene(scene);
                            stage.setHeight((height + 2.2) * Sprite.SCALED_SIZE);
                            stage.setWidth((width + 1.5) * Sprite.SCALED_SIZE);
                            createLevel.start_theme();
                        }
                        stage_start++;
                    }
                    return;
                }
                if (!createLevel.isNextLevel()) {
                    if (!createLevel.isGameOver()) {
                        createLevel.update(l, keyword, run);
                        createLevel.render();
                        updatetime(l);
                        updateClock(l);
                        updateRectangle(l);
                        if (createLevel.getA_dead() != a_dead) updateA_dead();
                        if (createLevel.getScore() != score) printScore(createLevel.getScore());
                        time = l;
                    } else {
                        stage.setHeight(15 * 32);
                        stage.setWidth(25 * 32);
                        stage.setScene(scene_start);
                        stage_level.setTranslateX(6 * 32);
                        stage_level.setTranslateY(4 * 32);
                        stage_level.setText(" GAME OVER\nYour Score: " + score);
                    }
                } else {
                    run = false;
                    start_level = 0;
                    time = 0;
                }

            }
        };
        timer.start();
    }

    public void printScore(int sc) {
        score = sc;
        int i = 0;
        while (sc > 0) {
            int a = sc % 10;
            sc = sc / 10;
            new Grass(0.57, (double) (width - 15) / 2 + 7 - 0.5 * i, getNum(a)).render(gc_info);
            i++;
        }
    }

    public void newTime() {
        time_minutes = 5;
        time_second1 = 0;
        time_second2 = 0;
        a_dead = 8;
    }

    public void updatetime(long l) {
        if (time_minutes == 0 && time_second1 <= 1) {
            if (l - time_update < 500_000_000) return;
            i_time = 1 - i_time;
            time_update = l;

            if (!time_up && time_minutes == 0 && time_second1 == 0 && time_second2 == 0) {
                createLevel.clearmap();
                time_up = true;
            }
            if (i_time == 1) {if (time_second1 == 1 && time_second2 == 0) new Sound("timeup.wav").start1();
                new Grass(0.63, (double) (width - 15) / 2 + 8.87, Sprite.num_null.getFxImage()).render(gc_info);
                new Grass(0.63, (double) (width - 15) / 2 + 9.83, Sprite.num_null.getFxImage()).render(gc_info);
                new Grass(0.63, (double) (width - 15) / 2 + 10.35, Sprite.num_null.getFxImage()).render(gc_info);
            } else {
                new Grass(0.63, (double) (width - 15) / 2 + 8.87, getNum_red(time_minutes)).render(gc_info);
                new Grass(0.63, (double) (width - 15) / 2 + 9.83, getNum_red(time_second1)).render(gc_info);
                new Grass(0.63, (double) (width - 15) / 2 + 10.35, getNum_red(time_second2)).render(gc_info);
                if (time_second2 == 0 && time_second1 == 0) return;
                time_second2 -= 1;
                if (time_second2 < 0) {
                    time_second2 = 9;
                    time_second1 -= 1;
                }
                if (time_second1 < 0) {
                    time_second1 = 5;
                    time_minutes--;
                }
            }
            return;
        }
        if (l - time_update < 1_000_000_000) return;
        time_update = l;
        new Grass(0.63, (double) (width - 15) / 2 + 8.87, getNum(time_minutes)).render(gc_info);
        new Grass(0.63, (double) (width - 15) / 2 + 9.83, getNum(time_second1)).render(gc_info);
        new Grass(0.63, (double) (width - 15) / 2 + 10.35, getNum(time_second2)).render(gc_info);
        time_second2 -= 1;
        if (time_second2 < 0) {
            time_second2 = 9;
            time_second1 -= 1;
        }
        if (time_second1 < 0) {
            time_second1 = 5;
            time_minutes--;
        }
    }

    public void updateClock(long l) {
        if (l - time_clock < 500_000_000) return;
        time_clock = l;
        switch (i_clock) {
            case 0:
                new Grass(0.54, (double) (width - 15) / 2 + 7.56, Sprite.clock0.getFxImage()).render(gc_info);
                break;
            case 1:
                new Grass(0.54, (double) (width - 15) / 2 + 7.56, Sprite.clock1.getFxImage()).render(gc_info);
                break;
            case 2:
                new Grass(0.54, (double) (width - 15) / 2 + 7.56, Sprite.clock2.getFxImage()).render(gc_info);
                break;
            case 3:
                new Grass(0.54, (double) (width - 15) / 2 + 7.56, Sprite.clock3.getFxImage()).render(gc_info);
                break;
            case 4:
                new Grass(0.54, (double) (width - 15) / 2 + 7.56, Sprite.clock4.getFxImage()).render(gc_info);
                break;
            case 5:
                new Grass(0.54, (double) (width - 15) / 2 + 7.56, Sprite.clock5.getFxImage()).render(gc_info);
                break;
            case 6:
                new Grass(0.54, (double) (width - 15) / 2 + 7.56, Sprite.clock6.getFxImage()).render(gc_info);
                break;
            case 7:
                new Grass(0.54, (double) (width - 15) / 2 + 7.56, Sprite.clock7.getFxImage()).render(gc_info);
                break;
        }
        i_clock = (i_clock + 1) % 8;
    }

    public void updateRectangle(long l) {
        if (l - time_rectangle < 150_000_000) return;
        time_rectangle = l;
        if (i_rectangle == 0) {
            new Grass(1.5, (double) (width - 15) / 2 + 15, Sprite.rectangle_null.getFxImage()).render(gc_info);
            new Grass(1.55, (double) (width - 15) / 2 + 15.05, Sprite.rectangle_white0.getFxImage()).render(gc_info);
            new Grass(1.5, (double) (width - 15) / 2 + 0.5, Sprite.rectangle_black1.getFxImage()).render(gc_info);
            i_rectangle = (i_rectangle + 1) % 28;
            return;
        }
        if (i_rectangle == 14) {
            new Grass(1.5, (double) (width - 15) / 2 + 7, Sprite.rectangle_null.getFxImage()).render(gc_info);
            new Grass(1.55, (double) (width - 15) / 2 + 7.05, Sprite.rectangle_black0.getFxImage()).render(gc_info);
            new Grass(1.5, (double) (width - 15) / 2 + 8.5, Sprite.rectangle_white1.getFxImage()).render(gc_info);
            i_rectangle = (i_rectangle + 1) % 28;
            return;
        }
        if (i_rectangle < 14) {
            new Grass(1.5, (double) (width - 15) / 2 + .5 * i_rectangle, Sprite.rectangle_null.getFxImage()).render(gc_info);
            new Grass(1.55, (double) (width - 15) / 2 + .5 * i_rectangle + .05, Sprite.rectangle_black0.getFxImage()).render(gc_info);
            new Grass(1.5, (double) (width - 15) / 2 + 0.5 * i_rectangle + .5, Sprite.rectangle_black1.getFxImage()).render(gc_info);
            i_rectangle = (i_rectangle + 1) % 28;
            return;
        }
        new Grass(1.5, (double) (width - 15) / 2 + 8.5 + .5 * (i_rectangle - 15), Sprite.rectangle_null.getFxImage()).render(gc_info);
        new Grass(1.55, (double) (width - 15) / 2 + 8.55 + .5 * (i_rectangle - 15), Sprite.rectangle_white0.getFxImage()).render(gc_info);
        new Grass(1.5, (double) (width - 15) / 2 + 8.5 + .5 * (i_rectangle - 14), Sprite.rectangle_white1.getFxImage()).render(gc_info);
        i_rectangle = (i_rectangle + 1) % 28;
    }

    public void updateA_dead() {
        if (a_dead < 0) return;
        a_dead--;
        new Grass(0.58, (double) (width - 15) / 2 + 1.5, getNum(a_dead)).render(gc_info);
    }

    public Image getNum(int num) {
        javafx.scene.image.Image image;
        switch (num) {
            case 0:
                image = Sprite.num_0.getFxImage();
                break;
            case 1:
                image = Sprite.num_1.getFxImage();
                break;
            case 2:
                image = Sprite.num_2.getFxImage();
                break;
            case 3:
                image = Sprite.num_3.getFxImage();
                break;
            case 4:
                image = Sprite.num_4.getFxImage();
                break;
            case 5:
                image = Sprite.num_5.getFxImage();
                break;
            case 6:
                image = Sprite.num_6.getFxImage();
                break;
            case 7:
                image = Sprite.num_7.getFxImage();
                break;
            case 8:
                image = Sprite.num_8.getFxImage();
                break;
            default:
                image = Sprite.num_9.getFxImage();
        }
        return image;
    }

    public Image getNum_red(int num) {
        javafx.scene.image.Image image;
        switch (num) {
            case 0:
                image = Sprite.num_0_red.getFxImage();
                break;
            case 1:
                image = Sprite.num_1_red.getFxImage();
                break;
            case 2:
                image = Sprite.num_2_red.getFxImage();
                break;
            case 3:
                image = Sprite.num_3_red.getFxImage();
                break;
            case 4:
                image = Sprite.num_4_red.getFxImage();
                break;
            case 5:
                image = Sprite.num_5_red.getFxImage();
                break;
            case 6:
                image = Sprite.num_6_red.getFxImage();
                break;
            case 7:
                image = Sprite.num_7_red.getFxImage();
                break;
            case 8:
                image = Sprite.num_8_red.getFxImage();
                break;
            default:
                image = Sprite.num_9_red.getFxImage();
        }
        return image;
    }
}
