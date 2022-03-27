package uet.oop.bomberman.graphics;

import javafx.scene.image.*;

import java.awt.image.BufferedImage;
import java.nio.IntBuffer;

/**
 * Lưu trữ thông tin các pixel của 1 sprite (hình ảnh game)
 */
public class Sprite {

    public static final int DEFAULT_SIZE = 16;
    public static final int SCALED_SIZE = DEFAULT_SIZE * 2;
    private static final int TRANSPARENT_COLOR = 0xffff00ff;
    public final int SIZE;
    private int _x, _y;
    public int[] _pixels;
    protected int _realWidth;
    protected int _realHeight;
    private SpriteSheet _sheet;

	/*
	|--------------------------------------------------------------------------
	| Board sprites
	|--------------------------------------------------------------------------
	 */

    public static Sprite null_24 = new Sprite(24, 30, 33, SpriteSheet.tiles, 16, 16);

    // Grass
    public static Sprite grass = new Sprite(DEFAULT_SIZE, 118, 24, SpriteSheet.tiles, 16, 16);
    public static Sprite grass_left = new Sprite(DEFAULT_SIZE, 152, 24, SpriteSheet.tiles, 16, 16);
    public static Sprite grass_right = new Sprite(DEFAULT_SIZE, 135, 24, SpriteSheet.tiles, 16, 16);
    public static Sprite grass_up = new Sprite(DEFAULT_SIZE, 101, 24, SpriteSheet.tiles, 16, 16);
    public static Sprite grass_down = new Sprite(DEFAULT_SIZE, 152, 74, SpriteSheet.tiles, 16, 16);
    public static Sprite grass_left_up = new Sprite(DEFAULT_SIZE, 170, 74, SpriteSheet.tiles, 16, 16);
    public static Sprite grass_left_down = new Sprite(DEFAULT_SIZE, 217, 74, SpriteSheet.tiles, 16, 16);
    public static Sprite grass_right_up = new Sprite(DEFAULT_SIZE, 252, 74, SpriteSheet.tiles, 16, 16);
    public static Sprite grass_right_down = new Sprite(DEFAULT_SIZE, 235, 74, SpriteSheet.tiles, 16, 16);

    //Bricks
    public static Sprite brick_up = new Sprite(DEFAULT_SIZE, 152, 24, SpriteSheet.tiles, 16, 16);
    public static Sprite brick = new Sprite(DEFAULT_SIZE, 135, 24, SpriteSheet.tiles, 16, 16);

    //Walls
    public static Sprite wall = new Sprite(DEFAULT_SIZE, 84, 24, SpriteSheet.tiles, 16, 16);
    public static Sprite wall_left = new Sprite(DEFAULT_SIZE, 0, 24, SpriteSheet.tiles, 16, 16);
    public static Sprite wall_left1 = new Sprite(DEFAULT_SIZE, 0, 58, SpriteSheet.tiles, 16, 16);
    public static Sprite wall_left2 = new Sprite(DEFAULT_SIZE, 17, 41, SpriteSheet.tiles, 16, 16);
    public static Sprite wall_right = new Sprite(DEFAULT_SIZE, 68, 24, SpriteSheet.tiles, 16, 16);
    public static Sprite wall_right1 = new Sprite(DEFAULT_SIZE, 68, 58, SpriteSheet.tiles, 16, 16);
    public static Sprite wall_right2 = new Sprite(DEFAULT_SIZE, 51, 41, SpriteSheet.tiles, 16, 16);
    public static Sprite wall_left_up = new Sprite(DEFAULT_SIZE, 17, 24, SpriteSheet.tiles, 16, 16);
    public static Sprite wall_left_down = new Sprite(DEFAULT_SIZE, 17, 58, SpriteSheet.tiles, 16, 16);
    public static Sprite wall_right_up = new Sprite(DEFAULT_SIZE, 51, 24, SpriteSheet.tiles, 16, 16);
    public static Sprite wall_right_down = new Sprite(DEFAULT_SIZE, 51, 58, SpriteSheet.tiles, 16, 16);
    public static Sprite wall_up = new Sprite(DEFAULT_SIZE, 34, 24, SpriteSheet.tiles, 16, 16);
    public static Sprite wall_down = new Sprite(DEFAULT_SIZE, 34, 58, SpriteSheet.tiles, 16, 16);

    //Portals
    public static Sprite portal = new Sprite(DEFAULT_SIZE, 0, 0, SpriteSheet.tiles, 14, 14);
    public static Sprite portal1 = new Sprite(DEFAULT_SIZE, 16, 0, SpriteSheet.tiles, 14, 14);
    public static Sprite portal2 = new Sprite(DEFAULT_SIZE, 32, 0, SpriteSheet.tiles, 14, 14);
    public static Sprite portal3 = new Sprite(DEFAULT_SIZE, 48, 0, SpriteSheet.tiles, 14, 14);
    public static Sprite portal4 = new Sprite(DEFAULT_SIZE, 64, 0, SpriteSheet.tiles, 14, 14);

    public static Sprite portal_next = new Sprite(DEFAULT_SIZE, 80, 0, SpriteSheet.tiles, 14, 14);
    public static Sprite portal_next1 = new Sprite(DEFAULT_SIZE, 96, 0, SpriteSheet.tiles, 14, 14);
    public static Sprite portal_next2 = new Sprite(DEFAULT_SIZE, 112, 0, SpriteSheet.tiles, 14, 14);
    public static Sprite portal_next3 = new Sprite(DEFAULT_SIZE, 128, 0, SpriteSheet.tiles, 14, 14);
    public static Sprite portal_next4 = new Sprite(DEFAULT_SIZE, 144, 0, SpriteSheet.tiles, 14, 14);
    public static Sprite portal_next5 = new Sprite(DEFAULT_SIZE, 160, 0, SpriteSheet.tiles, 14, 14);
    public static Sprite portal_next6 = new Sprite(DEFAULT_SIZE, 176, 0, SpriteSheet.tiles, 14, 14);
    public static Sprite portal_next7 = new Sprite(DEFAULT_SIZE, 192, 0, SpriteSheet.tiles, 14, 14);
    public static Sprite portal_next8 = new Sprite(DEFAULT_SIZE, 208, 0, SpriteSheet.tiles, 14, 14);
    public static Sprite portal_next9 = new Sprite(DEFAULT_SIZE, 224, 0, SpriteSheet.tiles, 14, 14);
    public static Sprite portal_next10 = new Sprite(DEFAULT_SIZE, 240, 0, SpriteSheet.tiles, 14, 14);
    public static Sprite portal_next11 = new Sprite(DEFAULT_SIZE, 256, 0, SpriteSheet.tiles, 14, 14);
    public static Sprite portal_next12 = new Sprite(DEFAULT_SIZE, 272, 0, SpriteSheet.tiles, 14, 14);
    public static Sprite portal_next13 = new Sprite(DEFAULT_SIZE, 288, 0, SpriteSheet.tiles, 14, 14);
    public static Sprite portal_next14 = new Sprite(DEFAULT_SIZE, 304, 0, SpriteSheet.tiles, 14, 14);
    public static Sprite portal_next15 = new Sprite(DEFAULT_SIZE, 320, 0, SpriteSheet.tiles, 14, 14);
    public static Sprite portal_next16 = new Sprite(DEFAULT_SIZE, 336, 0, SpriteSheet.tiles, 14, 14);


    //Information
    public static Sprite infomation = new Sprite(DEFAULT_SIZE * 16, 116, 157, SpriteSheet.tiles, 256, 256);
    public static Sprite infomation_i = new Sprite(32, 364, 157, SpriteSheet.tiles, 1, 1);

    /*
    |--------------------------------------------------------------------------
    | Number
    |--------------------------------------------------------------------------
     */
    public static Sprite num_null = new Sprite(14, 475, 221, SpriteSheet.tiles, 14, 8);
    public static Sprite num_0 = new Sprite(14, 475, 242, SpriteSheet.tiles, 14, 8);
    public static Sprite num_1 = new Sprite(14, 475, 261, SpriteSheet.tiles, 14, 8);
    public static Sprite num_2 = new Sprite(14, 475, 280, SpriteSheet.tiles, 14, 8);
    public static Sprite num_3 = new Sprite(14, 475, 298, SpriteSheet.tiles, 14, 8);
    public static Sprite num_4 = new Sprite(14, 475, 316, SpriteSheet.tiles, 14, 8);
    public static Sprite num_5 = new Sprite(14, 475, 333, SpriteSheet.tiles, 14, 8);
    public static Sprite num_6 = new Sprite(14, 475, 351, SpriteSheet.tiles, 14, 8);
    public static Sprite num_7 = new Sprite(14, 475, 368, SpriteSheet.tiles, 14, 8);
    public static Sprite num_8 = new Sprite(14, 475, 384, SpriteSheet.tiles, 14, 8);
    public static Sprite num_9 = new Sprite(14, 475, 400, SpriteSheet.tiles, 14, 8);

    public static Sprite num_0_red = new Sprite(14, 528, 242, SpriteSheet.tiles, 14, 8);
    public static Sprite num_1_red = new Sprite(14, 528, 261, SpriteSheet.tiles, 14, 8);
    public static Sprite num_2_red = new Sprite(14, 528, 280, SpriteSheet.tiles, 14, 8);
    public static Sprite num_3_red = new Sprite(14, 528, 298, SpriteSheet.tiles, 14, 8);
    public static Sprite num_4_red = new Sprite(14, 528, 316, SpriteSheet.tiles, 14, 8);
    public static Sprite num_5_red = new Sprite(14, 528, 333, SpriteSheet.tiles, 14, 8);
    public static Sprite num_6_red = new Sprite(14, 528, 351, SpriteSheet.tiles, 14, 8);
    public static Sprite num_7_red = new Sprite(14, 528, 368, SpriteSheet.tiles, 14, 8);
    public static Sprite num_8_red = new Sprite(14, 528, 384, SpriteSheet.tiles, 14, 8);
    public static Sprite num_9_red = new Sprite(14, 528, 400, SpriteSheet.tiles, 14, 8);

    /*
    |--------------------------------------------------------------------------
    | Bomber Sprites
    |--------------------------------------------------------------------------
     */
    public static Sprite player_down2 = new Sprite(24, 67, 190, SpriteSheet.tiles, 24, 16);
    public static Sprite player_down1 = new Sprite(24, 0, 190, SpriteSheet.tiles, 24, 16);
    public static Sprite player_down = new Sprite(24, 0, 214, SpriteSheet.tiles, 24, 16);
    public static Sprite player_down3 = new Sprite(24, 0, 238, SpriteSheet.tiles, 24, 16);

    public static Sprite player_up2 = new Sprite(24, 67, 263, SpriteSheet.tiles, 12, 15);
    public static Sprite player_up1 = new Sprite(24, 33, 262, SpriteSheet.tiles, 12, 15);
    public static Sprite player_up = new Sprite(24, 33, 286, SpriteSheet.tiles, 12, 15);
    public static Sprite player_up3 = new Sprite(24, 33, 310, SpriteSheet.tiles, 12, 15);

    public static Sprite player_left2 = new Sprite(24, 67, 214, SpriteSheet.tiles, 10, 15);
    public static Sprite player_left1 = new Sprite(24, 0, 263, SpriteSheet.tiles, 10, 15);
    public static Sprite player_left = new Sprite(24, 0, 286, SpriteSheet.tiles, 10, 15);
    public static Sprite player_left3 = new Sprite(24, 0, 310, SpriteSheet.tiles, 10, 15);

    public static Sprite player_right2 = new Sprite(24, 67, 238, SpriteSheet.tiles, 10, 16);
    public static Sprite player_right1 = new Sprite(24, 33, 190, SpriteSheet.tiles, 10, 16);
    public static Sprite player_right = new Sprite(24, 33, 214, SpriteSheet.tiles, 10, 16);
    public static Sprite player_right3 = new Sprite(24, 33, 238, SpriteSheet.tiles, 10, 16);

    public static Sprite player_dead = new Sprite(24, 69, 287, SpriteSheet.tiles, 10, 16);
    public static Sprite player_dead1 = new Sprite(24, 68, 312, SpriteSheet.tiles, 14, 16);
    public static Sprite player_dead2 = new Sprite(24, 16, 343, SpriteSheet.tiles, 13, 15);
    public static Sprite player_dead3 = new Sprite(24, 10, 371, SpriteSheet.tiles, 16, 16);

    /*
    |--------------------------------------------------------------------------
    | Character
    |--------------------------------------------------------------------------
     */
    //Dinasarus
    public static Sprite dinosaurs_left = new Sprite(24, 5, 455, SpriteSheet.tiles, 16, 16);
    public static Sprite dinosaurs_left1 = new Sprite(24, 58, 455, SpriteSheet.tiles, 16, 16);
    public static Sprite dinosaurs_left2 = new Sprite(24, 127, 455, SpriteSheet.tiles, 16, 16);

    public static Sprite dinosaurs_right = new Sprite(24, 5, 427, SpriteSheet.tiles, 16, 16);
    public static Sprite dinosaurs_right1 = new Sprite(24, 58, 427, SpriteSheet.tiles, 16, 16);
    public static Sprite dinosaurs_right2 = new Sprite(24, 127, 427, SpriteSheet.tiles, 16, 16);

    public static Sprite dinosaurs_up = new Sprite(24, 194, 455, SpriteSheet.tiles, 16, 16);
    public static Sprite dinosaurs_up1 = new Sprite(24, 250, 455, SpriteSheet.tiles, 16, 16);
    public static Sprite dinosaurs_up2 = new Sprite(24, 316, 455, SpriteSheet.tiles, 16, 16);

    public static Sprite dinosaurs_down = new Sprite(24, 194, 427, SpriteSheet.tiles, 16, 16);
    public static Sprite dinosaurs_down1 = new Sprite(24, 250, 427, SpriteSheet.tiles, 16, 16);
    public static Sprite dinosaurs_down2 = new Sprite(24, 316, 427, SpriteSheet.tiles, 16, 16);

    public static Sprite dinosaurs_dead = new Sprite(24, 370, 437, SpriteSheet.tiles, 16, 16);
    public static Sprite dinosaurs_dead2 = new Sprite(24, 409, 437, SpriteSheet.tiles, 16, 16);
    public static Sprite dinosaurs_dead1 = new Sprite(24, 370, 463, SpriteSheet.tiles, 16, 16);

    //ONEAL
    public static Sprite aborigines_up = new Sprite(24, 217, 72, SpriteSheet.tiles, 16, 16);
    public static Sprite aborigines_up1 = new Sprite(24, 251, 72, SpriteSheet.tiles, 16, 16);
    public static Sprite aborigines_up2 = new Sprite(24, 301, 72, SpriteSheet.tiles, 16, 16);

    public static Sprite aborigines_down = new Sprite(24, 217, 46, SpriteSheet.tiles, 16, 16);
    public static Sprite aborigines_down1 = new Sprite(24, 251, 45, SpriteSheet.tiles, 16, 16);
    public static Sprite aborigines_down2 = new Sprite(24, 301, 45, SpriteSheet.tiles, 16, 16);

    public static Sprite aborigines_left = new Sprite(24, 217, 19, SpriteSheet.tiles, 16, 16);
    public static Sprite aborigines_left1 = new Sprite(24, 250, 18, SpriteSheet.tiles, 16, 16);
    public static Sprite aborigines_left2 = new Sprite(24, 301, 18, SpriteSheet.tiles, 16, 16);

    public static Sprite aborigines_right = new Sprite(24, 545, 111, SpriteSheet.tiles, 16, 16);
    public static Sprite aborigines_right1 = new Sprite(24, 511, 110, SpriteSheet.tiles, 16, 16);
    public static Sprite aborigines_right2 = new Sprite(24, 461, 110, SpriteSheet.tiles, 16, 16);

    public static Sprite aborigines_throw_left = new Sprite(24, 333, 18, SpriteSheet.tiles, 16, 16);
    public static Sprite aborigines_throw_right = new Sprite(24, 429, 110, SpriteSheet.tiles, 16, 16);
    public static Sprite aborigines_throw_up = new Sprite(24, 333, 72, SpriteSheet.tiles, 16, 16);
    public static Sprite aborigines_throw_down = new Sprite(24, 333, 45, SpriteSheet.tiles, 16, 16);

    public static Sprite aborigines_pike_up = new Sprite(16, 441, 139, SpriteSheet.tiles, 16, 16);
    public static Sprite aborigines_pike_down = new Sprite(16, 426, 139, SpriteSheet.tiles, 16, 16);
    public static Sprite aborigines_pike_left = new Sprite(16, 458, 139, SpriteSheet.tiles, 16, 16);
    public static Sprite aborigines_pike_right = new Sprite(16, 475, 139, SpriteSheet.tiles, 16, 16);

    public static Sprite aborigines_dead = new Sprite(24, 368, 20, SpriteSheet.tiles, 16, 16);
    public static Sprite aborigines_dead1 = new Sprite(24, 367, 49, SpriteSheet.tiles, 16, 16);
    public static Sprite aborigines_dead2 = new Sprite(24, 409, 437, SpriteSheet.tiles, 16, 16);

    public static Sprite aborigines_win = new Sprite(24, 428, 162, SpriteSheet.tiles, 16, 16);

    //Deep
    public static Sprite deep_up = new Sprite(24, 577, 6, SpriteSheet.tiles, 16, 16);
    public static Sprite deep_up1 = new Sprite(24, 602, 6, SpriteSheet.tiles, 16, 16);
    public static Sprite deep_up2 = new Sprite(24, 627, 6, SpriteSheet.tiles, 16, 16);
    public static Sprite deep_up3 = new Sprite(24, 652, 6, SpriteSheet.tiles, 16, 16);

    public static Sprite deep_right = new Sprite(24, 573, 33, SpriteSheet.tiles, 16, 16);
    public static Sprite deep_right1 = new Sprite(24, 598, 33, SpriteSheet.tiles, 16, 16);
    public static Sprite deep_right2 = new Sprite(24, 623, 33, SpriteSheet.tiles, 16, 16);
    public static Sprite deep_right3 = new Sprite(24, 648, 33, SpriteSheet.tiles, 16, 16);

    public static Sprite deep_win = new Sprite(24, 633, 59, SpriteSheet.tiles, 16, 16);
    public static Sprite deep_win1 = new Sprite(24, 632, 85, SpriteSheet.tiles, 16, 16);

    //Minvo
    public static Sprite cute_up = new Sprite(24, 518, 192, SpriteSheet.tiles, 16, 16);
    public static Sprite cute_up1 = new Sprite(24, 548, 192, SpriteSheet.tiles, 16, 16);
    public static Sprite cute_up2 = new Sprite(24, 577, 192, SpriteSheet.tiles, 16, 16);
    public static Sprite cute_up3 = new Sprite(24, 603, 192, SpriteSheet.tiles, 16, 16);

    public static Sprite cute_down = new Sprite(16, 564, 260, SpriteSheet.tiles, 16, 16);
    public static Sprite cute_down1 = new Sprite(16, 582, 260, SpriteSheet.tiles, 16, 16);
    public static Sprite cute_down2 = new Sprite(16, 600, 260, SpriteSheet.tiles, 16, 16);
    public static Sprite cute_down3 = new Sprite(16, 618, 260, SpriteSheet.tiles, 16, 16);

    public static Sprite cute_right = new Sprite(24, 562, 220, SpriteSheet.tiles, 16, 16);
    public static Sprite cute_right1 = new Sprite(24, 589, 220, SpriteSheet.tiles, 16, 16);
    public static Sprite cute_right2 = new Sprite(24, 624, 220, SpriteSheet.tiles, 16, 16);
    public static Sprite cute_right3 = new Sprite(24, 653, 220, SpriteSheet.tiles, 16, 16);

    public static Sprite cute_dead = new Sprite(16, 637, 260, SpriteSheet.tiles, 16, 16);

    //Kondoria
    public static Sprite lightning = new Sprite(16, 4, 503, SpriteSheet.tiles, 16, 16);
    public static Sprite lightning1 = new Sprite(16, 42, 505, SpriteSheet.tiles, 16, 16);
    public static Sprite lightning2 = new Sprite(16, 133, 503, SpriteSheet.tiles, 16, 16);

    public static Sprite lightning_start = new Sprite(32, 186, 497, SpriteSheet.tiles, 16, 16);
    public static Sprite lightning_start1 = new Sprite(32, 219, 496, SpriteSheet.tiles, 16, 16);
    public static Sprite lightning_start2 = new Sprite(32, 254, 496, SpriteSheet.tiles, 16, 16);
    public static Sprite lightning_start3 = new Sprite(32, 287, 496, SpriteSheet.tiles, 16, 16);
    public static Sprite lightning_start4 = new Sprite(16, 328, 504, SpriteSheet.tiles, 16, 16);
    public static Sprite lightning_start5 = new Sprite(16, 362, 504, SpriteSheet.tiles, 16, 16);

    public static Sprite lightning_dead = new Sprite(DEFAULT_SIZE, 85, 507, SpriteSheet.tiles, 16, 16);

    public static Sprite lightning_left = new Sprite(DEFAULT_SIZE, 421, 496, SpriteSheet.tiles, 16, 16);
    public static Sprite lightning_right = new Sprite(DEFAULT_SIZE, 421, 512, SpriteSheet.tiles, 16, 16);
    public static Sprite lightning_up = new Sprite(DEFAULT_SIZE, 437, 496, SpriteSheet.tiles, 16, 16);
    public static Sprite lightning_down = new Sprite(DEFAULT_SIZE, 437, 512, SpriteSheet.tiles, 16, 16);
    public static Sprite lightning_left_up = new Sprite(DEFAULT_SIZE, 405, 496, SpriteSheet.tiles, 16, 16);
    public static Sprite lightning_right_up = new Sprite(DEFAULT_SIZE, 389, 496, SpriteSheet.tiles, 16, 16);
    public static Sprite lightning_left_down = new Sprite(DEFAULT_SIZE, 405, 512, SpriteSheet.tiles, 16, 16);
    public static Sprite lightning_right_down = new Sprite(DEFAULT_SIZE, 389, 512, SpriteSheet.tiles, 16, 16);


    //ALL
    public static Sprite mob_dead1 = new Sprite(DEFAULT_SIZE, 15, 0, SpriteSheet.tiles, 16, 16);
    public static Sprite mob_dead2 = new Sprite(DEFAULT_SIZE, 15, 1, SpriteSheet.tiles, 16, 16);
    public static Sprite mob_dead3 = new Sprite(DEFAULT_SIZE, 15, 2, SpriteSheet.tiles, 16, 16);

    /*
    |--------------------------------------------------------------------------
    | Bomb Sprites
    |--------------------------------------------------------------------------
     */
    public static Sprite bomb = new Sprite(DEFAULT_SIZE, 84, 41, SpriteSheet.tiles, 15, 15);
    public static Sprite bomb_1 = new Sprite(DEFAULT_SIZE, 101, 41, SpriteSheet.tiles, 13, 15);
    public static Sprite bomb_2 = new Sprite(DEFAULT_SIZE, 118, 41, SpriteSheet.tiles, 12, 14);
    public static Sprite bomb_3 = new Sprite(DEFAULT_SIZE, 135, 41, SpriteSheet.tiles, 12, 14);
    public static Sprite bomb_4 = new Sprite(DEFAULT_SIZE, 409, 22, SpriteSheet.tiles, 12, 14);
    public static Sprite bomb_5 = new Sprite(DEFAULT_SIZE, 426, 22, SpriteSheet.tiles, 12, 14);
    public static Sprite bomb_6 = new Sprite(DEFAULT_SIZE, 443, 22, SpriteSheet.tiles, 12, 14);
    public static Sprite bomb_7 = new Sprite(DEFAULT_SIZE, 460, 22, SpriteSheet.tiles, 12, 14);
    public static Sprite bomb_8 = new Sprite(19, 394, 79, SpriteSheet.tiles, 19, 19);
    public static Sprite bomb_9 = new Sprite(19, 419, 79, SpriteSheet.tiles, 19, 19);
    public static Sprite bomb_10 = new Sprite(19, 444, 79, SpriteSheet.tiles, 19, 19);
    public static Sprite bomb_11 = new Sprite(19, 467, 79, SpriteSheet.tiles, 19, 19);
    public static Sprite bomb_12 = new Sprite(19, 394, 2, SpriteSheet.tiles, 19, 19);
    public static Sprite bomb_13 = new Sprite(19, 419, 2, SpriteSheet.tiles, 19, 19);
    public static Sprite bomb_14 = new Sprite(19, 444, 2, SpriteSheet.tiles, 19, 19);
    public static Sprite bomb_15 = new Sprite(19, 467, 2, SpriteSheet.tiles, 19, 19);

    /*
    |--------------------------------------------------------------------------
    | FlameSegment Sprites
    |--------------------------------------------------------------------------
     */
    public static Sprite bomb_exploded = new Sprite(DEFAULT_SIZE, 31, 109, SpriteSheet.tiles, 16, 16);
    public static Sprite bomb_exploded1 = new Sprite(DEFAULT_SIZE, 112, 109, SpriteSheet.tiles, 16, 16);
    public static Sprite bomb_exploded2 = new Sprite(DEFAULT_SIZE, 195, 109, SpriteSheet.tiles, 16, 16);
    public static Sprite bomb_exploded3 = new Sprite(DEFAULT_SIZE, 280, 109, SpriteSheet.tiles, 16, 16);
    public static Sprite bomb_exploded4 = new Sprite(DEFAULT_SIZE, 364, 109, SpriteSheet.tiles, 16, 16);

    public static Sprite explosion_up = new Sprite(DEFAULT_SIZE, 31, 93, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_up1 = new Sprite(DEFAULT_SIZE, 112, 93, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_up2 = new Sprite(DEFAULT_SIZE, 195, 93, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_up3 = new Sprite(DEFAULT_SIZE, 280, 93, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_up4 = new Sprite(DEFAULT_SIZE, 364, 93, SpriteSheet.tiles, 16, 16);

    public static Sprite explosion_down = new Sprite(DEFAULT_SIZE, 31, 125, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_down1 = new Sprite(DEFAULT_SIZE, 112, 125, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_down2 = new Sprite(DEFAULT_SIZE, 195, 125, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_down3 = new Sprite(DEFAULT_SIZE, 280, 125, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_down4 = new Sprite(DEFAULT_SIZE, 364, 125, SpriteSheet.tiles, 16, 16);

    public static Sprite explosion_left = new Sprite(DEFAULT_SIZE, 15, 109, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_left1 = new Sprite(DEFAULT_SIZE, 96, 109, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_left2 = new Sprite(DEFAULT_SIZE, 179, 109, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_left3 = new Sprite(DEFAULT_SIZE, 264, 109, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_left4 = new Sprite(DEFAULT_SIZE, 349, 109, SpriteSheet.tiles, 16, 16);

    public static Sprite explosion_right = new Sprite(DEFAULT_SIZE, 47, 109, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_right1 = new Sprite(DEFAULT_SIZE, 128, 109, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_right2 = new Sprite(DEFAULT_SIZE, 211, 109, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_right3 = new Sprite(DEFAULT_SIZE, 296, 109, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_right4 = new Sprite(DEFAULT_SIZE, 380, 109, SpriteSheet.tiles, 16, 16);

    public static Sprite explosion_left_last = new Sprite(DEFAULT_SIZE, 0, 109, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_left_last1 = new Sprite(DEFAULT_SIZE, 80, 109, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_left_last2 = new Sprite(DEFAULT_SIZE, 163, 109, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_left_last3 = new Sprite(DEFAULT_SIZE, 248, 109, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_left_last4 = new Sprite(DEFAULT_SIZE, 333, 109, SpriteSheet.tiles, 16, 16);

    public static Sprite explosion_right_last = new Sprite(DEFAULT_SIZE, 63, 109, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_right_last1 = new Sprite(DEFAULT_SIZE, 144, 109, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_right_last2 = new Sprite(DEFAULT_SIZE, 227, 109, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_right_last3 = new Sprite(DEFAULT_SIZE, 312, 109, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_right_last4 = new Sprite(DEFAULT_SIZE, 396, 109, SpriteSheet.tiles, 16, 16);

    public static Sprite explosion_up_last = new Sprite(DEFAULT_SIZE, 31, 77, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_up_last1 = new Sprite(DEFAULT_SIZE, 112, 77, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_up_last2 = new Sprite(DEFAULT_SIZE, 195, 77, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_up_last3 = new Sprite(DEFAULT_SIZE, 280, 77, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_up_last4 = new Sprite(DEFAULT_SIZE, 364, 77, SpriteSheet.tiles, 16, 16);

    public static Sprite explosion_down_last = new Sprite(DEFAULT_SIZE, 31, 141, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_down_last1 = new Sprite(DEFAULT_SIZE, 112, 141, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_down_last2 = new Sprite(DEFAULT_SIZE, 195, 141, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_down_last3 = new Sprite(DEFAULT_SIZE, 280, 141, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_down_last4 = new Sprite(DEFAULT_SIZE, 364, 141, SpriteSheet.tiles, 16, 16);

    /*
    |--------------------------------------------------------------------------
    | Brick FlameSegment
    |--------------------------------------------------------------------------
     */
    public static Sprite brick_exploded = new Sprite(DEFAULT_SIZE, 135, 24, SpriteSheet.tiles, 16, 16);
    public static Sprite brick_exploded1 = new Sprite(DEFAULT_SIZE, 135, 78, SpriteSheet.tiles, 16, 16);
    public static Sprite brick_exploded2 = new Sprite(DEFAULT_SIZE, 135, 78, SpriteSheet.tiles, 16, 16);
    public static Sprite brick_exploded3 = new Sprite(DEFAULT_SIZE, 170, 74, SpriteSheet.tiles, 16, 16);
    public static Sprite brick_exploded4 = new Sprite(DEFAULT_SIZE, 170, 74, SpriteSheet.tiles, 16, 16);

    public static Sprite brick_up_exploded = new Sprite(DEFAULT_SIZE, 152, 24, SpriteSheet.tiles, 16, 16);
    public static Sprite brick_up_exploded1 = new Sprite(DEFAULT_SIZE, 135, 78, SpriteSheet.tiles, 16, 16);
    public static Sprite brick_up_exploded2 = new Sprite(DEFAULT_SIZE, 152, 74, SpriteSheet.tiles, 16, 16);
    public static Sprite brick_up_exploded3 = new Sprite(DEFAULT_SIZE, 152, 41, SpriteSheet.tiles, 16, 16);
    public static Sprite brick_up_exploded4 = new Sprite(DEFAULT_SIZE, 152, 41, SpriteSheet.tiles, 16, 16);

    /*
    |--------------------------------------------------------------------------
    | Powerups
    |--------------------------------------------------------------------------
     */
    public static Sprite powerup_bombs = new Sprite(DEFAULT_SIZE, 0, 157, SpriteSheet.tiles, 16, 16);
    public static Sprite powerup_flames = new Sprite(DEFAULT_SIZE, 68, 157, SpriteSheet.tiles, 16, 16);
    public static Sprite powerup_speed = new Sprite(DEFAULT_SIZE, 17, 157, SpriteSheet.tiles, 16, 16);
    public static Sprite powerup_wallpass = new Sprite(DEFAULT_SIZE, 3, 10, SpriteSheet.tiles, 16, 16);
    public static Sprite powerup_detonator = new Sprite(DEFAULT_SIZE, 4, 10, SpriteSheet.tiles, 16, 16);
    public static Sprite powerup_bombpass = new Sprite(DEFAULT_SIZE, 5, 10, SpriteSheet.tiles, 16, 16);
    public static Sprite powerup_flamepass = new Sprite(DEFAULT_SIZE, 6, 10, SpriteSheet.tiles, 16, 16);

    public static Sprite powerup_bombs1 = new Sprite(DEFAULT_SIZE, 0, 174, SpriteSheet.tiles, 16, 16);
    public static Sprite powerup_flames1 = new Sprite(DEFAULT_SIZE, 68, 174, SpriteSheet.tiles, 16, 16);
    public static Sprite powerup_speed1 = new Sprite(DEFAULT_SIZE, 17, 174, SpriteSheet.tiles, 16, 16);
    public static Sprite powerup_wallpass1 = new Sprite(DEFAULT_SIZE, 3, 10, SpriteSheet.tiles, 16, 16);
    public static Sprite powerup_detonator1 = new Sprite(DEFAULT_SIZE, 4, 10, SpriteSheet.tiles, 16, 16);
    public static Sprite powerup_bombpass1 = new Sprite(DEFAULT_SIZE, 5, 10, SpriteSheet.tiles, 16, 16);
    public static Sprite powerup_flamepass1 = new Sprite(DEFAULT_SIZE, 6, 10, SpriteSheet.tiles, 16, 16);

    /*
    |--------------------------------------------------------------------------
    | Score
    |--------------------------------------------------------------------------
     */
    public static Sprite sc_100 = new Sprite(24, 686, 4, SpriteSheet.tiles, 16, 16);
    public static Sprite sc_200 = new Sprite(24, 716, 4, SpriteSheet.tiles, 16, 16);
    public static Sprite sc_300 = new Sprite(24, 687, 41, SpriteSheet.tiles, 16, 16);
    public static Sprite sc_400 = new Sprite(24, 718, 41, SpriteSheet.tiles, 16, 16);

    /*
    |--------------------------------------------------------------------------
    | Clock
    |--------------------------------------------------------------------------
     */
    public static Sprite clock0 = new Sprite(22, 771, 0, SpriteSheet.tiles, 16, 16);
    public static Sprite clock1 = new Sprite(22, 771, 22, SpriteSheet.tiles, 16, 16);
    public static Sprite clock2 = new Sprite(22, 771, 44, SpriteSheet.tiles, 16, 16);
    public static Sprite clock3 = new Sprite(22, 771, 66, SpriteSheet.tiles, 16, 16);
    public static Sprite clock4 = new Sprite(22, 771, 88, SpriteSheet.tiles, 16, 16);
    public static Sprite clock5 = new Sprite(22, 771, 110, SpriteSheet.tiles, 16, 16);
    public static Sprite clock6 = new Sprite(22, 771, 132, SpriteSheet.tiles, 16, 16);
    public static Sprite clock7 = new Sprite(22, 771, 154, SpriteSheet.tiles, 16, 16);

    /*
    |--------------------------------------------------------------------------
    | Rectangle
    |--------------------------------------------------------------------------
     */
    public static Sprite rectangle_black0 = new Sprite(6, 125, 182, SpriteSheet.tiles, 16, 16);
    public static Sprite rectangle_black1 = new Sprite(8, 148, 141, SpriteSheet.tiles, 16, 16);
    public static Sprite rectangle_white0 = new Sprite(6, 253, 182, SpriteSheet.tiles, 16, 16);
    public static Sprite rectangle_white1 = new Sprite(8, 163, 141, SpriteSheet.tiles, 16, 16);
    public static Sprite rectangle_null = new Sprite(8, 177, 141, SpriteSheet.tiles, 16, 16);


    public Sprite(int size, int x, int y, SpriteSheet sheet, int rw, int rh) {
        SIZE = size;
        _pixels = new int[SIZE * SIZE];
        _x = x;
        _y = y;
        _sheet = sheet;
        _realWidth = rw;
        _realHeight = rh;
        load();
    }

    public Sprite(int size, int color) {
        SIZE = size;
        _pixels = new int[SIZE * SIZE];
        setColor(color);
    }

    private void setColor(int color) {
        for (int i = 0; i < _pixels.length; i++) {
            _pixels[i] = color;
        }
    }

    private void load() {
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                _pixels[x + y * SIZE] = _sheet._pixels[(x + _x) + (y + _y) * _sheet.SIZE];
            }
        }
    }

    public static Sprite movingSprite(Sprite normal, Sprite x1, Sprite x2, int animate, int time) {
        int calc = animate % time;
        int diff = time / 3;

        if (calc < diff) {
            return normal;
        }

        if (calc < diff * 2) {
            return x1;
        }

        return x2;
    }

    public static Sprite movingSprite(Sprite x1, Sprite x2, int animate, int time) {
        int diff = time / 2;
        return (animate % time > diff) ? x1 : x2;
    }

    public int getSize() {
        return SIZE;
    }

    public int getPixel(int i) {
        return _pixels[i];
    }

    public Image getFxImage() {
        WritableImage wr = new WritableImage(SIZE, SIZE);
        PixelWriter pw = wr.getPixelWriter();
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                if (_pixels[x + y * SIZE] == TRANSPARENT_COLOR) {
                    pw.setArgb(x, y, 0);
                } else {
                    pw.setArgb(x, y, _pixels[x + y * SIZE]);
                }
            }
        }
        Image input = new ImageView(wr).getImage();
        return resample(input, SCALED_SIZE / DEFAULT_SIZE);
    }

    private Image resample(Image input, int scaleFactor) {
        final int W = (int) input.getWidth();
        final int H = (int) input.getHeight();
        final int S = scaleFactor;

        WritableImage output = new WritableImage(
                W * S,
                H * S
        );

        PixelReader reader = input.getPixelReader();
        PixelWriter writer = output.getPixelWriter();

        for (int y = 0; y < H; y++) {
            for (int x = 0; x < W; x++) {
                final int argb = reader.getArgb(x, y);
                for (int dy = 0; dy < S; dy++) {
                    for (int dx = 0; dx < S; dx++) {
                        writer.setArgb(x * S + dx, y * S + dy, argb);
                    }
                }
            }
        }

        return output;
    }
}
