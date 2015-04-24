package cavemenarena.undevined.com.cavemenarena.classes;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

/**
 * Created by oliver on 16.04.15.
 */
public class Sprite {

    Caveman caveman;
    Caveman caveman2;
    Bitmap spritesheet;
    String action;
    int state;

    public Sprite(Caveman self, Caveman other, Bitmap sheet) {
        this.caveman = self;
        this.caveman2 = other;
        this.spritesheet = sheet;
        this.action = "idle";
        this.state = 1;
    }

    public Bitmap getFrame() {
        // Updates sprite state and action
        this.update();

        // Deprecated way for single sprites
        //String path = "@drawable/" + this.action + "_" + (this.caveman.stickIsSword() ? "sword_" : "") + this.state.toString();
        //Bitmap img = BitmapFactory.decodeFile(path);

        // Size of a frame in pixels
        int px = 32;
        // Enlargement scale
        int scale = 5;
        // Specifies the row of the searched frame
        int x = 0;
        switch (action) {
            case "idle" : x = 0; break;
            case "lost" : x = 1; break;
            case "won" : x = 2; break;
            case "poke" : x = state <= 2 ? 3 : 4; break;
            case "block" : x = state <= 2 ? 5 : 6; break;
            case "sharpen" : x = state <= 2 ? 7 : 8; break;
        }
        // Use the second half of the spritesheet if sprite should have a sword
        if (caveman.stickIsSword()) {
            x += 9;
        }
        // Sets the column of the frame
        int y = (state - 1) % 2;

        // Crops frame out of spritesheet and resizes it
        Bitmap frame;
        frame = Bitmap.createBitmap(this.spritesheet, 15, 15, 32, 32);
        //frame = Bitmap.createBitmap(this.spritesheet, x * px, y * px, px, px);
        frame = Bitmap.createScaledBitmap(frame, px * scale, px * scale, false);
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