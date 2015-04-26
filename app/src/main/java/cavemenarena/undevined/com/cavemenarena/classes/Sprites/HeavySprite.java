package cavemenarena.undevined.com.cavemenarena.classes.Sprites;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.HashMap;

import cavemenarena.undevined.com.cavemenarena.R;
import cavemenarena.undevined.com.cavemenarena.classes.Actions;
import cavemenarena.undevined.com.cavemenarena.classes.Caveman;

/**
 * Memory heavier Sprite class, uses single Bitmaps as sprites
 * Created by oliver on 16.04.15.
 */
public class HeavySprite {

    Caveman caveman;
    Caveman caveman2;
    String action;
    int state;

    HashMap<String,Bitmap> frames;

    public HeavySprite(Caveman self, Caveman other, final Resources resources) {
        this.caveman = self;
        this.caveman2 = other;
        this.action = "idle";
        this.state = 1;

        this.frames = new HashMap<String,Bitmap>() {{
            put("idle_1", BitmapFactory.decodeResource(resources, R.drawable.idle_1));
            put("idle_2", BitmapFactory.decodeResource(resources, R.drawable.idle_2));
            put("lost_1", BitmapFactory.decodeResource(resources, R.drawable.lost_1));
            put("lost_2", BitmapFactory.decodeResource(resources, R.drawable.lost_2));
            put("won_1", BitmapFactory.decodeResource(resources, R.drawable.won_1));
            put("won_2", BitmapFactory.decodeResource(resources, R.drawable.won_2));
            put("poke_1", BitmapFactory.decodeResource(resources, R.drawable.poke_1));
            put("poke_2", BitmapFactory.decodeResource(resources, R.drawable.poke_2));
            put("poke_3", BitmapFactory.decodeResource(resources, R.drawable.poke_3));
            put("poke_4", BitmapFactory.decodeResource(resources, R.drawable.poke_4));
            put("block_1", BitmapFactory.decodeResource(resources, R.drawable.block_1));
            put("block_2", BitmapFactory.decodeResource(resources, R.drawable.block_2));
            put("block_3", BitmapFactory.decodeResource(resources, R.drawable.block_3));
            put("block_4", BitmapFactory.decodeResource(resources, R.drawable.block_4));
            put("sharpen_1", BitmapFactory.decodeResource(resources, R.drawable.sharpen_1));
            put("sharpen_2", BitmapFactory.decodeResource(resources, R.drawable.sharpen_2));
            put("sharpen_3", BitmapFactory.decodeResource(resources, R.drawable.sharpen_3));
            put("sharpen_4", BitmapFactory.decodeResource(resources, R.drawable.sharpen_4));
            put("idle_sword_1", BitmapFactory.decodeResource(resources, R.drawable.idle_sword_1));
            put("idle_sword_2", BitmapFactory.decodeResource(resources, R.drawable.idle_sword_2));
            put("lost_sword_1", BitmapFactory.decodeResource(resources, R.drawable.lost_sword_1));
            put("lost_sword_2", BitmapFactory.decodeResource(resources, R.drawable.lost_sword_2));
            put("won_sword_1", BitmapFactory.decodeResource(resources, R.drawable.won_sword_1));
            put("won_sword_2", BitmapFactory.decodeResource(resources, R.drawable.won_sword_2));
            put("poke_sword_1", BitmapFactory.decodeResource(resources, R.drawable.poke_sword_1));
            put("poke_sword_2", BitmapFactory.decodeResource(resources, R.drawable.poke_sword_2));
            put("poke_sword_3", BitmapFactory.decodeResource(resources, R.drawable.poke_sword_3));
            put("poke_sword_4", BitmapFactory.decodeResource(resources, R.drawable.poke_sword_4));
            put("block_sword_1", BitmapFactory.decodeResource(resources, R.drawable.block_sword_1));
            put("block_sword_2", BitmapFactory.decodeResource(resources, R.drawable.block_sword_2));
            put("block_sword_3", BitmapFactory.decodeResource(resources, R.drawable.block_sword_3));
            put("block_sword_4", BitmapFactory.decodeResource(resources, R.drawable.block_sword_4));
            put("sharpen_sword_1", BitmapFactory.decodeResource(resources, R.drawable.sharpen_sword_1));
            put("sharpen_sword_2", BitmapFactory.decodeResource(resources, R.drawable.sharpen_sword_2));
            put("sharpen_sword_3", BitmapFactory.decodeResource(resources, R.drawable.sharpen_sword_3));
            put("sharpen_sword_4", BitmapFactory.decodeResource(resources, R.drawable.sharpen_sword_4));
        }};
    }

    public Bitmap getFrame() {
        // Update sprite state and action
        this.update();

        // Retrieve frame from HashMap
        String key = this.action + "_" + (this.caveman.stickIsSword() ? "sword_" : "") + this.state;
        Bitmap frame = this.frames.get(key);

        // Return frame
        return frame;
    }

    private void update() {
        this.state++;

        if (this.action.equals("poke") || this.action.equals("block") || this.action.equals("sharpen")) {
            if (this.state > 4) {
                this.state = 1;
                if (this.caveman.isDead()) {
                    this.action = "lost";
                } else if (this.caveman2.isDead()) {
                    this.action = "won";
                } else {
                    this.action = "idle";
                }
            }
        } else {
            if (this.state > 2) {
                this.state = 1;
            }
        }
    }

    /**
     * Call this method after a button has been pressed and the caveman's action is set.
     */
    public void triggerAction() {
        int ac = this.caveman.getNextAction();

        if (ac == Actions.POKE) {
            this.action = "poke";
        } else if (ac == Actions.BLOCK) {
            this.action = "block";
        } else if (ac == Actions.SHARPEN) {
            this.action = "sharpen";
        }

        this.state = 1;
    }

}