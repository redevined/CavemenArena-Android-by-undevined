package cavemenarena.undevined.com.cavemenarena.classes.Sprites;

import android.graphics.Bitmap;

import cavemenarena.undevined.com.cavemenarena.classes.Actions;
import cavemenarena.undevined.com.cavemenarena.classes.Caveman;

/**
 * Original Sprite class, uses spritesheet to crop and enlarge actual sprites
 * Created by redevined on 16.04.15.
 */
public class CompactSprite extends Sprite {

    Caveman caveman;
    Caveman caveman2;
    String action;
    int state;

    Bitmap spritesheet;
    final int dim = 32;
    final int scale = 5;

    public CompactSprite(Caveman self, Caveman other, Bitmap sheet) {
        this.caveman = self;
        this.caveman2 = other;
        this.action = "idle";
        this.state = 2;

        this.spritesheet = sheet;
    }

    @Override
    public Bitmap getFrame() {
        this.update();

        int y = 0;
        if (action.equals("idle")) {
            y = 0;
        } else if (action.equals("lost")) {
            y = 1;
        } else if (action.equals("won")) {
            y = 2;
        } else if (action.equals("poke")) {
            y = state <= 2 ? 3 : 4;
        } else if (action.equals("block")) {
            y = state <= 2 ? 5 : 6;
        } else if (action.equals("sharpen")) {
            y = state <= 2 ? 7 : 8;
        }
        if (caveman.stickIsSword()) {
            y += 9;
        }
        int x = (state - 1) % 2;

        Bitmap frame;
        frame = Bitmap.createBitmap(this.spritesheet, x * dim, y * dim, dim, dim);
        frame = Bitmap.createScaledBitmap(frame, dim * scale, dim * scale, false);

        return frame;
    }

}