package cavemenarena.undevined.com.cavemenarena.classes.Sprites;

import android.graphics.Bitmap;

import cavemenarena.undevined.com.cavemenarena.classes.Actions;
import cavemenarena.undevined.com.cavemenarena.classes.Caveman;

/**
 * Original Sprite class, uses spritesheet to crop and enlarge actual sprites
 * Created by oliver on 16.04.15.
 */
public class Sprite {

    Caveman caveman;
    Caveman caveman2;
    String action;
    int state;

    Bitmap spritesheet;
    final int dim = 32;
    final int scale = 5;

    public Sprite(Caveman self, Caveman other, Bitmap sheet) {
        this.caveman = self;
        this.caveman2 = other;
        this.spritesheet = sheet;
        this.action = "idle";
        this.state = 2;
    }

    public Bitmap getFrame() {
        // Update sprite state and action
        this.update();

        // Specify the row of the searched frame
        int y = 0;
        switch (action) {
            case "idle" : y = 0; break;
            case "lost" : y = 1; break;
            case "won" : y = 2; break;
            case "poke" : y = state <= 2 ? 3 : 4; break;
            case "block" : y = state <= 2 ? 5 : 6; break;
            case "sharpen" : y = state <= 2 ? 7 : 8; break;
        }
        // Use the second half of the spritesheet if sprite should have a sword
        if (caveman.stickIsSword()) {
            y += 9;
        }
        // Set the column of the frame
        int x = (state - 1) % 2;

        // Crop frame out of spritesheet and resizes it
        Bitmap frame;
        frame = Bitmap.createBitmap(this.spritesheet, x * dim, y * dim, dim, dim);
        frame = Bitmap.createScaledBitmap(frame, dim * scale, dim * scale, false);

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