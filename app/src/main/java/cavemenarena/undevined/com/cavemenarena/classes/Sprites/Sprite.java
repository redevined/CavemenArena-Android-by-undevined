package cavemenarena.undevined.com.cavemenarena.classes.Sprites;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.util.HashMap;

import cavemenarena.undevined.com.cavemenarena.R;
import cavemenarena.undevined.com.cavemenarena.classes.Actions;
import cavemenarena.undevined.com.cavemenarena.classes.Caveman;

/**
 * Memory heavy Sprite class, uses single Bitmaps as sprites.
 * Created by redevined on 16.04.15.
 */
public class Sprite {

    Caveman caveman;
    Caveman caveman2;
    String action;
    int state;

    HashMap<String,Bitmap> frames;

    /**
     * Parameterless constructor for inheriting classes.
     */
    public Sprite() { }

    /**
     * Constructor
     * @param self: Caveman that is resembled by the Sprite
     * @param other: Caveman's opponent
     * @param resources: Return values of getResources() call
     */
    public Sprite(Caveman self, Caveman other, Resources resources) {
        this.caveman = self;
        this.caveman2 = other;
        this.action = "idle";
        this.state = 1;

        this.frames = loadFrames(resources);
    }

    /**
     * Creates HashMap of all frames so the frames can be retrieved and returned in getFrame().
     * Advantage is, that Bitmaps only need to be rendered once.
     */
    protected HashMap<String,Bitmap> loadFrames(final Resources resources) {
        return new HashMap<String,Bitmap>() {{
            put("idle_1", BitmapFactory.decodeResource(resources, R.drawable.sprite_idle_1));
            put("idle_2", BitmapFactory.decodeResource(resources, R.drawable.sprite_idle_2));
            put("lost_1", BitmapFactory.decodeResource(resources, R.drawable.sprite_lost_1));
            put("lost_2", BitmapFactory.decodeResource(resources, R.drawable.sprite_lost_2));
            put("won_1", BitmapFactory.decodeResource(resources, R.drawable.sprite_won_1));
            put("won_2", BitmapFactory.decodeResource(resources, R.drawable.sprite_won_2));
            put("poke_1", BitmapFactory.decodeResource(resources, R.drawable.sprite_poke_1));
            put("poke_2", BitmapFactory.decodeResource(resources, R.drawable.sprite_poke_2));
            put("poke_3", BitmapFactory.decodeResource(resources, R.drawable.sprite_poke_3));
            put("poke_4", BitmapFactory.decodeResource(resources, R.drawable.sprite_poke_4));
            put("block_1", BitmapFactory.decodeResource(resources, R.drawable.sprite_block_1));
            put("block_2", BitmapFactory.decodeResource(resources, R.drawable.sprite_block_2));
            put("block_3", BitmapFactory.decodeResource(resources, R.drawable.sprite_block_3));
            put("block_4", BitmapFactory.decodeResource(resources, R.drawable.sprite_block_4));
            put("sharpen_1", BitmapFactory.decodeResource(resources, R.drawable.sprite_sharpen_1));
            put("sharpen_2", BitmapFactory.decodeResource(resources, R.drawable.sprite_sharpen_2));
            put("sharpen_3", BitmapFactory.decodeResource(resources, R.drawable.sprite_sharpen_3));
            put("sharpen_4", BitmapFactory.decodeResource(resources, R.drawable.sprite_sharpen_4));
            put("idle_sword_1", BitmapFactory.decodeResource(resources, R.drawable.sprite_idle_sword_1));
            put("idle_sword_2", BitmapFactory.decodeResource(resources, R.drawable.sprite_idle_sword_2));
            put("lost_sword_1", BitmapFactory.decodeResource(resources, R.drawable.sprite_lost_sword_1));
            put("lost_sword_2", BitmapFactory.decodeResource(resources, R.drawable.sprite_lost_sword_2));
            put("won_sword_1", BitmapFactory.decodeResource(resources, R.drawable.sprite_won_sword_1));
            put("won_sword_2", BitmapFactory.decodeResource(resources, R.drawable.sprite_won_sword_2));
            put("poke_sword_1", BitmapFactory.decodeResource(resources, R.drawable.sprite_poke_sword_1));
            put("poke_sword_2", BitmapFactory.decodeResource(resources, R.drawable.sprite_poke_sword_2));
            put("poke_sword_3", BitmapFactory.decodeResource(resources, R.drawable.sprite_poke_sword_3));
            put("poke_sword_4", BitmapFactory.decodeResource(resources, R.drawable.sprite_poke_sword_4));
            put("block_sword_1", BitmapFactory.decodeResource(resources, R.drawable.sprite_block_sword_1));
            put("block_sword_2", BitmapFactory.decodeResource(resources, R.drawable.sprite_block_sword_2));
            put("block_sword_3", BitmapFactory.decodeResource(resources, R.drawable.sprite_block_sword_3));
            put("block_sword_4", BitmapFactory.decodeResource(resources, R.drawable.sprite_block_sword_4));
            put("sharpen_sword_1", BitmapFactory.decodeResource(resources, R.drawable.sprite_sharpen_sword_1));
            put("sharpen_sword_2", BitmapFactory.decodeResource(resources, R.drawable.sprite_sharpen_sword_2));
            put("sharpen_sword_3", BitmapFactory.decodeResource(resources, R.drawable.sprite_sharpen_sword_3));
            put("sharpen_sword_4", BitmapFactory.decodeResource(resources, R.drawable.sprite_sharpen_sword_4));
        }};
    }

    /**
     * Changes internal state of the Sprite.
     */
    protected void update() {
        this.state++;

        if (Actions.checkActionKey(this.action)) {
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
     * Returns current frame.
     */
    public Bitmap getFrame() {
        String key = this.action + "_" + (this.caveman.stickIsSword() ? "sword_" : "") + this.state;
        Bitmap frame = this.frames.get(key);

        //Log.d("Sprite", "[Action: " + this.action + "] [State: " + this.state + "] [Key: " + key +"] [Current Frame: " + frame + "] [Sharpen Frame: " + this.frames.get("sharpen_1") + "]");
        this.update();
        return frame;
    }

    /**
     * Sets next Sprite animation.
     * Call this method after a button has been pressed and the caveman's action is set.
     */
    public void animate(int act) {
        this.action = Actions.getActionName(act);
        this.state = 1;
    }

}