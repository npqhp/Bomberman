package uet.oop.bomberman.Level;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.JavaSound.Sound;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class CreateLevel {
    private int level;
    private int width;
    private int height;
    private char[][] map;
    private GraphicsContext gc;
    private Bomber bomber;
    private boolean win = false;
    private boolean nextLevel = false;

    private Bom[] boms = new Bom[20];
    private int[][] bom;
    private int a_bom;
    private int bom_length;
    private int l_bom;
    private int r_bom;

    private int a_dead;
    private boolean gameOver = false;

    private char[][] item = new char[50][50];
    private List<Item> items;

    private List<Dinosaurs> dinosaurs;
    private List<Aborigines> aborigines;
    private List<Pikes> pikes;
    private List<Deep> deeps;
    private List<Deep> deeps1;
    private List<Cute> cutes;
    private List<Score> scores;
    private List<Lightning> lightnings;
    private List<Lightning_Start> lightning_starts;
    private boolean[] run_Pike;
    private int n_deeps = 0;
    private int n_deeps1 = 0;
    private int n_aborigines = 0;
    private int n_dinosaurs = 0;
    private int n_cutes = 0;
    private int cant_dead = 0;
    private int status = 0;
    private int status1 = 0;
    private long time_cant_dead;
    private int speed_deeps;
    private int speed_deeps1;
    private int score = 0;

    private List<Exploded> explodeds = new ArrayList<>();

    Sound footstep = new Sound("footstep2.wav");
    Sound sound_dead = new Sound("Life Lost.wav");
    Sound game_over = new Sound("Game Over.wav");
    Sound[] bom_exploded = new Sound[20];
    Sound[] bom_countdown = new Sound[20];
    Sound stage_theme = new Sound("Stage Theme.wav");
    Sound stage_complete = new Sound("Stage Complete.wav");

    public void ReadLevel() throws FileNotFoundException {
        File file = new File("C:\\Users\\Admin\\Desktop\\bomberman-starter-starter-2\\res\\levels\\level" + this.level + ".txt");
        Scanner sc = new Scanner(file);
        sc.nextInt();
        height = sc.nextInt();
        width = sc.nextInt();
        map = new char[height][width];
        sc.nextLine();
        for (int i = 0; i < height; i++) {
            String s = sc.nextLine();
            for (int j = 0; j < width; j++) {
                bom[i][j] = -1;
                map[i][j] = s.charAt(j);
                char c = map[i][j];
                if (c == 'b' || c == 'f' || c == 's' || c == 'x') {
                    item[i][j] = c;
                    map[i][j] = '*';
                } else item[i][j] = ' ';
                if (c == 'p') {
                    bomber = new Bomber(i, j, Sprite.player_right.getFxImage());
                    map[i][j] = ' ';
                }
                if (c == '1') {
                    n_dinosaurs++;
                    dinosaurs.add(new Dinosaurs(i, j, Sprite.dinosaurs_right.getFxImage()));
                    map[i][j] = ' ';
                }
                if (c == '2') {
                    aborigines.add(new Aborigines(i, j, Sprite.aborigines_left.getFxImage(), n_aborigines));
                    n_aborigines++;
                    map[i][j] = ' ';
                }
                if (c == '3') {
                    n_cutes++;
                    cutes.add(new Cute(i, j, Sprite.cute_down.getFxImage()));
                    map[i][j] = ' ';
                }
                if (c == '4') {
                    lightnings.add(new Lightning(i, j, Sprite.lightning.getFxImage()));
                    map[i][j] = ' ';
                }
            }
        }
    }

    public void renderLevel() {
        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++)
                switch (map[i][j]) {
                    case '#':
                        printWall(i, j);
                        break;
                    case ' ':
                        if (map[i - 1][j] == '#') new Grass(i - .5, j + .5, Sprite.grass_up.getFxImage()).render(gc);
                        else new Grass(i - .5, j + .5, Sprite.grass.getFxImage()).render(gc);
                        break;
                    case '*':
                        if (map[i - 1][j] == '#') new Grass(i - .5, j + .5, Sprite.brick_up.getFxImage()).render(gc);
                        else new Grass(i - .5, j + .5, Sprite.brick.getFxImage()).render(gc);
                }
    }

    public void printWall(int i, int j) {
        if (i < 0 || i >= height || j < 0 || j >= width) return;
        if (i == height - 1 && (j == 0 || j == width - 1)) return;
        if (j == 1) {
            if (i == 0) {
                new Grass(i, j, Sprite.wall_left_up.getFxImage()).render(gc);
                return;
            }
            if (i == height - 1) {
                new Grass(i, j, Sprite.wall_left_down.getFxImage()).render(gc);
                return;
            }
        }
        if (i == height - 2) {
            if (j == 0) {
                new Grass(i, j, Sprite.wall_left1.getFxImage()).render(gc);
                new Grass(i, j + 1, Sprite.wall_left_down.getFxImage()).render(gc);
                return;
            }
            if (j == width - 1) {
                new Grass(i, j + 1, Sprite.wall_right1.getFxImage()).render(gc);
                new Grass(i, j, Sprite.wall_right_down.getFxImage()).render(gc);
                return;
            }
        }
        if (i == 0) {
            if (j == 0) {
                new Grass(i, j, Sprite.wall_left.getFxImage()).render(gc);
                new Grass(i, j + 1, Sprite.wall_left_up.getFxImage()).render(gc);
                return;
            }
            if (j == width - 1) {
                new Grass(i, j, Sprite.wall_right_up.getFxImage()).render(gc);
                new Grass(i, j + 1, Sprite.wall_right.getFxImage()).render(gc);
                return;
            }
            new Grass(i, j, Sprite.wall_up.getFxImage()).render(gc);
            return;
        }
        if (i == height - 1) {
            new Grass(i - 1, j, Sprite.wall_down.getFxImage()).render(gc);
            return;
        }
        if (j == 0) {
            new Grass(i, j, Sprite.wall_left.getFxImage()).render(gc);
            new Grass(i, j + 1, Sprite.wall_left2.getFxImage()).render(gc);
            return;
        }
        if (j == width - 1) {
            new Grass(i, j, Sprite.wall_right2.getFxImage()).render(gc);
            new Grass(i, j + 1, Sprite.wall_right.getFxImage()).render(gc);
            return;
        }
        new Grass(i - .5, j + .5, Sprite.wall.getFxImage()).render(gc);
    }

    public void createMap() {
        dinosaurs = new ArrayList<>();
        aborigines = new ArrayList<>();
        explodeds = new ArrayList<>();
        scores = new ArrayList<>();
        cutes = new ArrayList<>();
        pikes = new ArrayList<>();
        bom = new int[50][50];
        deeps = new ArrayList<>();
        deeps1 = new ArrayList<>();
        lightnings = new ArrayList<>();
        lightning_starts = new ArrayList<>();

        items = new ArrayList<>();
        run_Pike = new boolean[30];

        n_dinosaurs = 0;
        n_aborigines = 0;
        n_cutes = 0;
        a_bom = 1;
        bom_length = 1;
        l_bom = 0;
        r_bom = 0;
        sound_dead = new Sound("Life Lost.wav");
        game_over = new Sound("Game Over.wav");
        stage_complete = new Sound("Stage Complete.wav");
        a_dead = 8;
        cant_dead = 0;
        for (int i = 0; i < 20; i++) {
            bom_exploded[i] = new Sound("Explosion.wav");
            bom_countdown[i] = new Sound("countdown_bom.wav");
        }
        try {
            ReadLevel();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        win = false;
        nextLevel = false;
    }

    public void update_time_up(long l, String k, boolean run) {
        if (cant_dead < 2) {
            bomber.setDead(false);
            if (l - time_cant_dead > 2_000_000_000) {
                time_cant_dead = l;
                cant_dead++;
            }
        }
        if (n_dinosaurs == 0 && n_aborigines == 0) win = true;
        int key = convert(k);
        if (bomber.getDead()) {
            if (bomber.getStatus() == 0) sound_dead.start();
            clear(bomber.getX() - .5, bomber.getY(), bomber.getHeight());
            bomber.update_dead(l);
            if (bomber.getStatus() == 5) {
                a_dead--;
                if (a_dead > 0) {
                    bomber.setDead(false);
                    bomber = new Bomber(1, 1, Sprite.player_down.getFxImage());
                    cant_dead = 0;
                    time_cant_dead = 0;
                } else {
                    gameOver = true;
                    stage_theme.stop();
                    game_over.start1();
                }
            }
        } else if (run && key >= 1 && key <= 4) update_bomber(l, key);

        if (n_deeps == 0) {
            if (status == 0) {
                for (int i = 1; i < height - 1; i++)
                    if (i % 2 == 1) {
                        deeps.add(new Deep(i, 1, Sprite.deep_right.getFxImage(), 1));
                        n_deeps++;
                    }
            } else {
                for (int i = 1; i < height - 1; i++)
                    if (i % 2 == 1) {
                        deeps.add(new Deep(i, width - 2, Sprite.deep_right3.getFxImage(), 0));
                        n_deeps++;
                    }
            }
            status = 1 - status;
            speed_deeps = speed_deeps / 2;
        }

        if (n_deeps1 == 0) {
            if (status1 == 0) {
                for (int i = 1; i < width - 1; i++)
                    if (i % 2 == 1) {
                        deeps1.add(new Deep(1, i, Sprite.deep_up3.getFxImage(), 3));
                        n_deeps1++;
                    }
            } else {
                for (int i = 1; i < width - 1; i++)
                    if (i % 2 == 1) {
                        deeps1.add(new Deep(height - 2, i, Sprite.deep_up.getFxImage(), 2));
                        n_deeps1++;
                    }
            }
            status1 = 1 - status1;
            speed_deeps1 = speed_deeps1 / 2;
        }

        int i_s = 0;
        while (i_s < deeps.size()) {
            Deep s = deeps.get(i_s);
            i_s++;
            double x = s.getX();
            double y = s.getY();
            clear_deep(x - .5, y, 1.5);
            if (x < 1 || x > height - 2 || y < 1 || y > width - 2) {
                deeps.remove(s);
                n_deeps--;
                continue;
            }
            if (bomber.getDead() && a_dead == 1) {
                s.update_win(l);
                continue;
            }
            if (!bomber.getDead()) bomber.setDead(collision(bomber.getX(), bomber.getY(), x, y));
            s.update(l, speed_deeps);
        }

        i_s = 0;
        while (i_s < deeps1.size()) {
            Deep s = deeps1.get(i_s);
            i_s++;
            double x = s.getX();
            double y = s.getY();
            clear(x - .5, y, 1.5);
            if (x < 1 || x > height - 2 || y < 1 || y > width - 2) {
                deeps1.remove(s);
                n_deeps1--;
                continue;
            }
            if (bomber.getDead() && a_dead == 1) {
                s.update_win(l);
                continue;
            }
            if (!bomber.getDead()) bomber.setDead(collision(bomber.getX(), bomber.getY(), x, y));
            s.update(l, speed_deeps1);
        }

        for (Deep s : deeps) s.render(gc);
        for (Deep s : deeps1) s.render(gc);
        bomber.render(gc);
    }

    public void update(long l, String k, boolean run) {
        if (cant_dead < 20) {
            bomber.setDead(false);
            if (l - time_cant_dead > 100_000_000) {
                cant_dead++;
                time_cant_dead = l;
                if (cant_dead == 20) bomber.setCant_dead(false);
            }
        }
        if (n_dinosaurs == 0 && n_aborigines == 0 && n_cutes == 0) win = true;
        int key = convert(k);
        if (bomber.getDead()) {
            if (bomber.getStatus() == 0) sound_dead.start();
            clear(bomber.getX() - .5, bomber.getY(), bomber.getHeight());
            bomber.update_dead(l);
            if (bomber.getStatus() == 5) {
                a_dead--;
                if (a_dead > 0) {
                    bomber.setDead(false);
                    bomber = new Bomber(1, 1, Sprite.player_down.getFxImage());
                    cant_dead = 0;
                    time_cant_dead = 0;
                    bomber.setCant_dead(true);
                } else {
                    gameOver = true;
                    stage_theme.stop();
                    game_over.start1();
                }
            }
        } else if (run && key >= 1 && key <= 4) update_bomber(l, key);

        update_enemy(l);
        update_bomb(l);
        update_bomb_exploded(l);
        update_scores(l);
        update_item(l);
    }

    public void update_bomber(long l, int key) {
        if (!bomber.check_time(l)) return;
        footstep.start();
        clear(bomber.getX() - .5, bomber.getY(), bomber.getHeight());
        check_run(bomber.getX(), bomber.getY(), key);
        bomber.update(key);
    }

    public void add_bom() {
        if (bomber.getDead()) return;
        if (a_bom > 0) {
            if (bom[(int) round(bomber.getX())][(int) round(bomber.getY())] == -1) {
                boms[r_bom] = new Bom(round(bomber.getX()), round(bomber.getY()), Sprite.bomb.getFxImage(), 0, 0, bom_length);
                bom[(int) boms[r_bom].getX()][(int) boms[r_bom].getY()] = r_bom;
                map[(int) boms[r_bom].getX()][(int) boms[r_bom].getY()] = 'b';
                bom_countdown[r_bom].startnew();
                r_bom = (r_bom + 1) % 20;
                a_bom--;
            }
        }
    }

    public void update_bomb(long l) {
        if (l_bom != r_bom) {
            int i = l_bom;
            while (i != r_bom) {
                if (boms[i].getRun()) {
                    boms[i].update(l, map[(int) boms[i].getX() - 1][(int) boms[i].getY()] == ' ', map[(int) boms[i].getX() + 1][(int) boms[i].getY()] == ' ');
                    if (!boms[i].getRun()) {
                        map[(int) boms[i].getX()][(int) boms[i].getY()] = ' ';
                        a_bom++;
                        explodeds.add(new Exploded(boms[i].getX(), boms[i].getY(), boms[i].getLength(), i, 0, l));
                        bom_exploded[i].start();
                    }
                }
                if (!boms[i].getRun()) {
                    map[(int) boms[i].getX()][(int) boms[i].getY()] = ' ';
                    if (i == l_bom) l_bom = (l_bom + 1) % 20;
                }
                i = (i + 1) % 20;
            }
        }
    }

    public void update_bomb_exploded(long l) {
        int i_s = 0;
        while (i_s < explodeds.size()) {
            Exploded s = explodeds.get(i_s);
            if (!s.setTime(l)) {
                i_s++;
                new Exploded((int) s.getX(), (int) s.getY(), 0, s.getI_img()).render(gc);
                continue;
            }

            //left , right , up, down = 1 , 2 , 3 , 4
            int x = (int) s.getX();
            int y = (int) s.getY();
            int z = s.getI_img();
            bom[x][y] = s.getIndex();
            if (z == 5) {
                clear(x, y, 1);
                bom[x][y] = -1;
            } else {
                if (!bomber.getDead()) bomber.setDead(collision(bomber.getX(), bomber.getY(), x, y));

            }
            for (int i = 1; i <= 4; i++)
                for (int j = 1; j <= s.getLength(); j++) {
                    int u = x;
                    int v = y;
                    switch (i) {
                        case 1:
                            u = x - j;
                            v = y;
                            break;
                        case 2:
                            u = x + j;
                            v = y;
                            break;
                        case 3:
                            u = x;
                            v = y - j;
                            break;
                        case 4:
                            u = x;
                            v = y + j;
                    }
                    if (map[u][v] == '*') {
                        if (z == 5) {
                            map[u][v] = ' ';
                            clear(u, v, 1);
                            if (item[u][v] != ' ') {
                                switch (item[u][v]) {
                                    case 'b':
                                        items.add(new Item(u, v, Sprite.powerup_bombs.getFxImage()));
                                        break;
                                    case 'f':
                                        items.add(new Item(u, v, Sprite.powerup_flames.getFxImage()));
                                        break;
                                    case 's':
                                        items.add(new Item(u, v, Sprite.powerup_speed.getFxImage()));
                                        break;
                                    case 'x':
                                        items.add(new Item(u, v, Sprite.portal.getFxImage()));
                                        break;
                                }
                            }
                        } else new Brick(u, v, z, map[u - 1][v] == ' ').render(gc);
                        break;
                    }
                    if (bom[u][v] != -1 && bom[u][v] != s.getIndex()) {
                        int t = bom[u][v];
                        if (boms[t].getRun()) {
                            bom_countdown[t].stop();
                            boms[t].setRun(false);
                            explodeds.add(new Exploded(boms[t].getX(), boms[t].getY(), boms[t].getLength(), t, z, 0));
                            a_bom++;
                        }
                        break;
                    }
                    if (map[u][v] == '#') break;
                    bom[u][v] = s.getIndex();
                    if (z == 5) clear(u, v, 1);
                    else {
                        if (j == s.getLength())
                            new Exploded(u, v, i, z).render(gc);
                        else new Exploded(u, v, i + 4, z).render(gc);
                    }
                    if (z == 5) bom[u][v] = -1;
                    else if (!bomber.getDead()) bomber.setDead(collision(bomber.getX(), bomber.getY(), u, v));
                }
            if (z == 5) explodeds.remove(i_s);
            else {
                s.setI_img(z + 1);
                explodeds.set(i_s, s);
            }
            i_s++;
        }
    }

    public void update_item(long l) {
        int i_s = 0;
        while (i_s < items.size()) {
            Item s = items.get(i_s);
            if (collision(bomber.getX(), bomber.getY(), s.getX(), s.getY())) {
                switch (item[(int) s.getX()][(int) s.getY()]) {
                    case 'f':
                        bom_length++;
                        items.remove(s);
                        item[(int) s.getX()][(int) s.getY()] = ' ';
                        break;
                    case 'b':
                        a_bom++;
                        items.remove(s);
                        item[(int) s.getX()][(int) s.getY()] = ' ';
                        break;
                    case 's':
                        bomber.update_speed();
                        items.remove(s);
                        item[(int) s.getX()][(int) s.getY()] = ' ';
                        break;
                    case 'x':
                        if (win) {
                            nextLevel = true;
                            stage_theme.stop();
                            stage_complete.start1();
                        } else s.update(l, item[(int) s.getX()][(int) s.getY()], win);
                        break;
                }
            } else {
                s.update(l, item[(int) s.getX()][(int) s.getY()], win);
            }
            i_s++;
        }
    }

    public void update_enemy(long l) {
        update_dinosaurs(l);
        update_aborigines(l);
        update_pikes(l);
        update_cutes(l);
        update_lightings(l);
        update_lightning_starts(l);
    }

    public void update_aborigines(long l) {
        int i_s = 0;
        while (i_s < aborigines.size()) {
            Aborigines s = aborigines.get(i_s);
            i_s++;
            double x = s.getX();
            double y = s.getY();
            clear(x - .5, y, 1.5);
            if (s.getDead()) {
                if (s.getI_img() == 4) {
                    aborigines.remove(s);
                    score += 200;
                    scores.add(new Score(x, y, Sprite.sc_200.getFxImage()));
                }
                s.next_dead(l);
                continue;
            }
            if (bomber.getDead() && a_dead == 1) {
                s.update_win(l);
                continue;
            }
            if (check_bom(s.getX(), s.getY())) {
                s.setDead(true);
                n_aborigines--;
                continue;
            }
            if (x != (int) x || y != (int) y) {
                if (s.next(l, !run_Pike[s.getIndex()] && check_Pike(x, y, s.getDirection()))) {
                    run_Pike[s.getIndex()] = true;
                    pikes.add(new Pikes(x, y, Sprite.aborigines_throw_down.getFxImage(), s.getDirection(), s.getIndex()));
                }
            } else {
                int t = find_the_way(x, y);
                if (t == 5) {
                    s.not_way(l);
                } else {
                    s.setDirection(t);
                    if (s.next(l, !run_Pike[s.getIndex()] && check_Pike(x, y, s.getDirection()))) {
                        run_Pike[s.getIndex()] = true;
                        pikes.add(new Pikes(x, y, Sprite.aborigines_throw_down.getFxImage(), s.getDirection(), s.getIndex()));
                    }
                }
            }
            if (!s.getDead() && !bomber.getDead()) bomber.setDead(collision(bomber.getX(), bomber.getY(), x, y));
        }
    }

    public void update_pikes(long l) {
        int i_s = 0;
        while (i_s < pikes.size()) {
            Pikes s = pikes.get(i_s);
            i_s++;
            double x = s.getX();
            double y = s.getY();
            int u = (int) x;
            int v = (int) y;
            clear(x, y, 1.5);
            boolean next = true;
            if (map[u][v] != ' ') next = false;
            if (!next) {
                run_Pike[s.getIndex()] = false;
                pikes.remove(s);
                continue;
            }
            s.update(l);
            if (!bomber.getDead()) {
                if (collision(bomber.getX(), bomber.getY(), x, y)) {
                    bomber.setDead(true);
                    pikes.remove(s);
                }
            }
        }
    }

    public void update_dinosaurs(long l) {
        int i_s = 0;
        while (i_s < dinosaurs.size()) {
            Dinosaurs s = dinosaurs.get(i_s);
            i_s++;
            double x = s.getX();
            double y = s.getY();
            clear(x - .5, y, 1.5);
            if (s.getDead()) {
                if (s.getI_img() == 4) {
                    dinosaurs.remove(s);
                    score += 100;
                    scores.add(new Score(x, y, Sprite.sc_100.getFxImage()));
                }
                s.next_dead(l);
                continue;
            }
            if (bomber.getDead() && a_dead == 1) {
                s.update_win(l);
                continue;
            }
            if (check_bom(s.getX(), s.getY())) {
                s.setDead(true);
                n_dinosaurs--;
                continue;
            }
            if (x != (int) x || y != (int) y) s.next(l);
            else {
                Random direction = new Random();
                for (int i = 1; i <= 5; i++) {
                    if (i == 5) {
                        s.not_way(l);
                        break;
                    }
                    int t = direction.nextInt(4);
                    // left , right, up , down = 0, 1 ,2 ,3
                    s.setDirection(t);
                    int key;
                    switch (t) {
                        case 0:
                            key = 3;
                            break;
                        case 1:
                            key = 4;
                            break;
                        case 2:
                            key = 1;
                            break;
                        default:
                            key = 2;
                    }
                    if (check_Enemy(s.getX(), s.getY(), key)) {
                        s.next(l);
                        break;
                    }
                }
            }
            if (!s.getDead() && !bomber.getDead()) bomber.setDead(collision(bomber.getX(), bomber.getY(), x, y));
        }
    }

    public void update_lightings(long l) {
        int i_s = 0;
        while (i_s < lightnings.size()) {
            Lightning s = lightnings.get(i_s);
            i_s++;
            double x = s.getX();
            double y = s.getY();
            clear(x - .5, y, 1.5);
            if (x != (int) x || y != (int) y) s.next(l);
            else {
                Random direction = new Random();
                int t = direction.nextInt(100);
                if (t == 50) {
                    new Sound("warning.wav").start();
                    lightnings.remove(s);
                    lightning_starts.add(new Lightning_Start(x, y, Sprite.lightning_start.getFxImage()));
                    continue;
                }
                for (int i = 1; i <= 3; i++) {
                    t = direction.nextInt(4);
                    // left , right, up , down = 0, 1 ,2 ,3
                    s.setDirection(t);
                    int u = (int) x;
                    int v = (int) y;
                    switch (t) {
                        case 0:
                            v--;
                            break;
                        case 1:
                            v++;
                            break;
                        case 2:
                            u--;
                            break;
                        default:
                            u++;
                    }
                    if (u > 0 && u < height - 1 && v > 0 && v < width - 1) {
                        s.next(l);
                        break;
                    }
                }
            }
            if (!bomber.getDead()) bomber.setDead(collision(bomber.getX(), bomber.getY(), x, y));
        }
    }

    public void update_lightning_starts(long l) {
        int i_s = 0;
        while (i_s < lightning_starts.size()) {
            Lightning_Start s = lightning_starts.get(i_s);
            i_s++;
            int x = (int) s.getX();
            int y = (int) s.getY();
            clear_lightning_starts(x, y);
            s.next(l);
            if (s.getI_img() == 6) {
                for (int i = 1; i < height - 1; i++) {
                    if (i != x) clear(i, y, 1.5);
                }
                for (int i = 1; i < width - 1; i++) {
                    if (i < y) clear(x, i, 1.5);
                    if (i > y) clear(x, i, 1.5);
                }
            }
            if (s.getI_img() == 7) lightning_starts.remove(s);
        }
    }

    public void update_scores(long l) {
        int i = 0;
        while (i < scores.size()) {
            Score s = scores.get(i);
            i++;
            s.update(l);
            if (s.getI() == 2) scores.remove(s);
            clear_deep(s.getX() - .5, s.getY(), 1.5);
        }
    }

    private void update_cutes(long l) {
        int i_s = 0;
        while (i_s < cutes.size()) {
            Cute s = cutes.get(i_s);
            i_s++;
            double x = s.getX();
            double y = s.getY();
            clear_cute(x - .5, y, 1.5);
            if (s.getDead()) {
                if (s.getI_img() == 4) {
                    cutes.remove(s);
                    score += 300;
                    scores.add(new Score(x, y, Sprite.sc_200.getFxImage()));
                }
                s.next_dead(l);
                continue;
            }
            if (bomber.getDead() && a_dead == 1) {
                s.update_win(l);
                continue;
            }
            if (check_bom(s.getX(), s.getY())) {
                s.setDead(true);
                n_cutes--;
                continue;
            }
            if (s.getI_img() != 0) {
                if (Math.abs(s.getRunX()) == .5 || Math.abs(s.getRunY()) == .5) s.next_jump(l, -1);
                else s.next(l, -1);
            } else {
                Random direction = new Random();
                for (int i = 1; i <= 5; i++) {
                    if (i == 5) {
                        s.not_way(l);
                        break;
                    }
                    int t = direction.nextInt(4);
                    // left , right, up , down = 0, 1 ,2 ,3

                    int key;
                    switch (t) {
                        case 0:
                            key = 3;
                            break;
                        case 1:
                            key = 4;
                            break;
                        case 2:
                            key = 1;
                            break;
                        default:
                            key = 2;
                    }
                    if (check_Enemy(s.getX(), s.getY(), key)) {
                        s.next(l, t);
                        break;
                    } else if (check_Jump(s.getX(), s.getY(), key)) {
                        s.next_jump(l, t);
                        break;
                    }
                }
            }
            if (!bomber.getDead()) bomber.setDead(collision(bomber.getX(), bomber.getY(), x, y));
        }
    }

    public void render() {
        render_bomb_exploded();

        render_item();

        render_bomb();

        render_enemy();

        render_scores();

        render_bomber();

    }

    public void render_bomber() {
        if (cant_dead < 20 && cant_dead % 4 == 0) {
            clear_deep(bomber.getX() - .5, bomber.getY(), 1.5);
            return;
        }
        bomber.render(gc);
    }

    public void render_bomb() {
        if (l_bom != r_bom) {
            int i = l_bom;
            while (i != r_bom) {
                clear(boms[i].getX(), boms[i].getY(), 1.5);
                if (boms[i].getRun()) boms[i].render(gc);
                i = (i + 1) % 20;
            }
        }
    }

    public void render_bomb_exploded() {
        int i_s = 0;
        while (i_s < explodeds.size()) {
            Exploded s = explodeds.get(i_s);
            //left , right , up, down = 1 , 2 , 3 , 4
            int x = (int) s.getX();
            int y = (int) s.getY();
            int z = s.getI_img();
            bom[x][y] = s.getIndex();
            if (z == 5) clear(x, y, 1);
            for (int i = 1; i <= 4; i++)
                for (int j = 1; j <= s.getLength(); j++) {
                    int u = x;
                    int v = y;
                    switch (i) {
                        case 1:
                            u = x - j;
                            v = y;
                            break;
                        case 2:
                            u = x + j;
                            v = y;
                            break;
                        case 3:
                            u = x;
                            v = y - j;
                            break;
                        case 4:
                            u = x;
                            v = y + j;
                    }
                    if (map[u][v] == '*') break;
                    if (bom[u][v] != -1 && bom[u][v] != s.getIndex()) break;
                    if (map[u][v] == '#') break;
                    bom[u][v] = s.getIndex();
                    if (z == 5) clear(u, v, 1);
                    else {
                        if (j == s.getLength())
                            new Exploded(u, v, i, z).render(gc);
                        else new Exploded(u, v, i + 4, z).render(gc);
                    }
                }
            i_s++;
        }
    }

    public void render_item() {
        int i_s = 0;
        while (i_s < items.size()) {
            Item s = items.get(i_s);
            s.render(gc);
            i_s++;
        }
    }

    public void render_enemy() {
        render_dinosaurs();
        render_aborigines();
        render_pikes();
        render_cutes();
        render_lightnings();
        render_lightning_starts();
    }

    public void render_scores() {
        for (Score s : scores) s.render(gc);
    }

    public void render_aborigines() {
        for (Aborigines s : aborigines) s.render(gc);
    }

    public void render_pikes() {
        for (Pikes s : pikes) s.render(gc);
    }

    public void render_dinosaurs() {
        for (Dinosaurs s : dinosaurs) s.render(gc);
    }

    public void render_cutes() {
        for (Cute s : cutes) s.render(gc);
    }

    public void render_lightnings() {
        for (Lightning s : lightnings) s.render(gc);
    }

    public void render_lightning_starts() {
        for (Lightning_Start s : lightning_starts) {
            s.render(gc);
            if (s.getI_img() == 5) {
                int x = (int) s.getX();
                int y = (int) s.getY();
                if (!bomber.getDead()) bomber.setDead(collision(bomber.getX(), bomber.getY(), x, y));
                for (int i = 1; i < height - 1; i++) {
                    if (!bomber.getDead()) bomber.setDead(collision(bomber.getX(), bomber.getY(), i, y));
                    if (i != x) new Brick(i, y, Sprite.lightning_down.getFxImage()).render(gc);
                }
                for (int i = 1; i < width - 1; i++) {
                    if (!bomber.getDead()) bomber.setDead(collision(bomber.getX(), bomber.getY(), x, i));
                    if (i < y) new Brick(x, i, Sprite.lightning_left.getFxImage()).render(gc);
                    if (i > y) new Brick(x, i, Sprite.lightning_right.getFxImage()).render(gc);
                }
            }
        }
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setGc(GraphicsContext gc) {
        this.gc = gc;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int convert(String k) {
        int i = 0;
        switch (k) {
            case "UP":
                i = 1;
                break;
            case "DOWN":
                i = 2;
                break;
            case "LEFT":
                i = 3;
                break;
            case "RIGHT":
                i = 4;
                break;
            case "SPACE":
                i = 5;
                break;
        }
        return i;
    }

    public void clear(double x, double y, double h) {
        print(x, (int) y, h);
        print(x, (int) y + 1, h);
    }

    public void clear_deep(double x, double y, double h) {
        print(x, (int) y, h);
        print(x, (int) y + 1, h);
        print(x, (int) y + 2, h);
    }

    public void clear_cute(double x, double y, double h) {
        print(x, (int) y, h);
        print(x, (int) y + 1, h);
        print(x, (int) y + 2, h);
        print(x + 1, (int) y, h);
    }

    public void clear_lightning_starts(int x, int y) {
        print(x, y, 1.5);
        print(x, y + 1, 1.5);
        print(x, y - 1, 1.5);
        print(x - 1, y, 1.5);
        print(x - 1, y + 1, 1.5);
        print(x - 1, y - 1, 1.5);
        print(x + 1, y, 1.5);
        print(x + 1, y + 1, 1.5);
        print(x + 1, y - 1, 1.5);
    }

    public void print(double x, int y, double h) {
        if (h == 1) {
            printBackGround((int) x, y);
        } else {
            printBackGround((int) x, y);
            printBackGround((int) x + 1, y);
            printBackGround((int) x, y + 1);
            if (x - (int) x == .75) printBackGround((int) x + 2, y);
        }
    }

    public void printBackGround(int x, int y) {
        if (x < 0 || x >= height || y < 0 || y >= width) return;
        if (map[x][y] == ' ') {
            if (map[x - 1][y] == '#') new Grass(x - .5, y + .5, Sprite.grass_up.getFxImage()).render(gc);
            else new Grass(x - .5, y + .5, Sprite.grass.getFxImage()).render(gc);
            return;
        }
        if (map[x][y] == '*') {
            if (map[x - 1][y] == '#') new Grass(x - .5, y + .5, Sprite.brick_up.getFxImage()).render(gc);
            else new Grass(x - .5, y + .5, Sprite.brick.getFxImage()).render(gc);
            return;
        }
        if (map[x][y] == '#') {
            printWall(x, y);
            return;
        }

    }

    public void check_run(double x, double y, int key) {
        //key = 1,2,3,4 ~ up , right, left, down
        if (key == 1 || key == 2) {
            double a = y - (int) y;
            double ix;
            if (key == 1) ix = -0.25;
            else ix = .25;
            if (a <= .5) {
                if (check_run1(x, (int) y, key)) {
                    bomber.run(ix, (int) y - y);
                    return;
                }
            } else {
                if (check_run1(x, (int) y + 1, key)) {
                    bomber.run(ix, (int) y + 1 - y);
                }
            }
            return;
        }
        double a = x - (int) x;
        double iy;
        if (key == 3) iy = -0.25;
        else iy = .25;
        if (a <= .5) {
            if (check_run1((int) x, y, key)) {
                bomber.run((int) x - x, iy);
                return;
            }
        } else {
            if (check_run1((int) x + 1, y, key)) {
                bomber.run((int) x + 1 - x, iy);
            }
        }
        return;

    }

    public boolean check_run1(double x, double y, int key) {
        if (x <= 0 || x >= height - 1 || y <= 0 || y >= width - 1) return false;
        int i_x = (int) x;
        int i_y = (int) y;
        if (key == 1 || key == 2) {
            if (x != i_x) return true;
            if (key == 1) {
                if (map[i_x - 1][i_y] == ' ') return true;
                else return false;
            } else {
                if (map[i_x + 1][i_y] == ' ') return true;
                else return false;
            }
        }
        if (y != i_y) return true;
        if (key == 3) {
            if (map[i_x][i_y - 1] == ' ') return true;
            else return false;
        } else {
            if (map[i_x][i_y + 1] == ' ') return true;
            else return false;
        }
    }

    public boolean check_bom(double x, double y) {
        int ix = (int) x;
        int iy = (int) y;
        if (bom[ix][iy] != -1 && !boms[bom[ix][iy]].getRun()) return true;
        if (x > ix && bom[ix + 1][iy] != -1 && !boms[bom[ix + 1][iy]].getRun()) return true;
        if (y > iy && bom[ix][iy + 1] != -1 && !boms[bom[ix][iy + 1]].getRun()) return true;
        if (x > ix && y > iy && bom[ix + 1][iy + 1] != -1 && !boms[bom[ix + 1][iy + 1]].getRun()) return true;
        return false;
    }

    public boolean check_Enemy(double x, double y, int key) {
        //key = 1,2,3,4 ~ up , down, left, right
        if (key == 1 || key == 2) {
            double a = y - (int) y;
            if (a <= .5) {
                if (check_run1(x, (int) y, key)) {
                    return true;
                }
            } else {
                if (check_run1(x, (int) y + 1, key)) {
                    return true;
                }
            }
            return false;
        }
        double a = x - (int) x;
        if (a <= .5) {
            if (check_run1((int) x, y, key)) {
                return true;
            }
        } else {
            if (check_run1((int) x + 1, y, key)) {
                return true;
            }
        }
        return false;
    }

    public boolean check_Jump(double x, double y, int key) {
        switch (key) {
            case 1:
                return check_Enemy(x - 1, y, key);
            case 2:
                return check_Enemy(x + 1, y, key);
            case 3:
                return check_Enemy(x, y - 1, key);
            default:
                return check_Enemy(x, y + 1, key);
        }
    }

    public int find_the_way(double x, double y) {
        int u = (int) x;
        int v = (int) y;
        int m = round_to_int(bomber.getX());
        int n = round_to_int(bomber.getY());
        int[] p = new int[width * height];
        int[] q = new int[width * height];
        int[] d = new int[width * height];
        boolean check_map[][] = new boolean[height][width];
        int l = 0;
        int r = 0;
        p[0] = u;
        q[0] = v;
        d[0] = -1;
        while (l <= r) {
            u = p[l];
            v = q[l];
            if (u == m && v == n) break;
            if (!check_map[u - 1][v] && check_Enemy(u, v, 1)) {
                r++;
                p[r] = u - 1;
                q[r] = v;
                check_map[u - 1][v] = true;
                if (d[l] == -1) d[r] = 2;
                else d[r] = d[l];
            }
            if (!check_map[u + 1][v] && check_Enemy(u, v, 2)) {
                r++;
                p[r] = u + 1;
                q[r] = v;
                check_map[u + 1][v] = true;
                if (d[l] == -1) d[r] = 3;
                else d[r] = d[l];
            }
            if (!check_map[u][v - 1] && check_Enemy(u, v, 3)) {
                r++;
                p[r] = u;
                q[r] = v - 1;
                check_map[u][v - 1] = true;
                if (d[l] == -1) d[r] = 0;
                else d[r] = d[l];
            }
            if (!check_map[u][v + 1] && check_Enemy(u, v, 4)) {
                r++;
                p[r] = u;
                q[r] = v + 1;
                check_map[u][v + 1] = true;
                if (d[l] == -1) d[r] = 1;
                else d[r] = d[l];
            }
            l++;
        }
        if (r == 0) return 5;
        if (l > r) {
            Random direction = new Random();
            int t = direction.nextInt(r) + 1;
            return d[t];
        } else return d[l];
    }

    public boolean collision(double x, double y, double u, double v) {
        if (x <= u && u < x + 1 && y <= v && v < y + 1) return true;
        if (u <= x && x < u + 1 && v <= y && y < v + 1) return true;
        return false;
    }

    public int round_to_int(double x) {
        if (x - (int) x <= 0.5) return (int) x;
        return (int) x + 1;
    }

    public boolean check_Pike(double x, double y, int direction) {
        int u = (int) x;
        int v = (int) y;
        switch (direction) {
            case 0:
                if (v < 3 || map[u][v - 1] != ' ' || map[u][v - 2] != ' ') return false;
                if (collision(bomber.getX(), bomber.getY(), u, v - 1) ||
                        collision(bomber.getX(), bomber.getY(), u, v - 2)) return false;
                return true;
            case 1:
                if (v < width - 3 || map[u][v + 1] != ' ' || map[u][v + 2] != ' ') return false;
                if (collision(bomber.getX(), bomber.getY(), u, v + 1) ||
                        collision(bomber.getX(), bomber.getY(), u, v + 2)) return false;
                return true;
            case 2:
                if (u < 3 || map[u - 1][v] != ' ' || map[u - 2][v] != ' ') return false;
                if (collision(bomber.getX(), bomber.getY(), u - 1, v) ||
                        collision(bomber.getX(), bomber.getY(), u - 2, v)) return false;
                return true;
            default:
                if (u < height - 3 || map[u + 1][v] != ' ' || map[u + 2][v] != ' ') return false;
                if (collision(bomber.getX(), bomber.getY(), u - 1, v) ||
                        collision(bomber.getX(), bomber.getY(), u - 2, v)) return false;
                return true;
        }
    }

    public double round(double x) {
        if (x - (int) x <= 0.5) return (int) x;
        return (int) x + 1;
    }

    public boolean isNextLevel() {
        return nextLevel;
    }

    public int getA_dead() {
        return a_dead;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void start_theme() {
        stage_theme.repeat(3);
    }

    public void setWin() {
        win = true;
    }

    public void setNextLevel() {
        nextLevel = true;
        stage_theme.stop();
    }

    public int getScore() {
        return score;
    }

    public void clearmap() {
        speed_deeps = 16;
        speed_deeps1 = 16;
        for (int i = l_bom; i < r_bom; i++) {
            clear(boms[i].getX(), boms[i].getY(), 1.5);
            map[(int) boms[i].getX()][(int) boms[i].getY()] = ' ';
        }
        for (Dinosaurs s : dinosaurs) clear(s.getX() - .5, s.getY(), 1.5);
        for (Aborigines s : aborigines) clear(s.getX() - .5, s.getY(), 1.5);
        for (Pikes s : pikes) clear(s.getX(), s.getY(), 1.5);
    }

    public void stop() {
        //footstep.pause();
    }
}
