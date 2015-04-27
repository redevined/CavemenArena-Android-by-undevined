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
        switch (action) {
            case "idle" : y = 0; break;
            case "lost" : y = 1; break;
            case "won" : y = 2; break;
            case "poke" : y = state <= 2 ? 3 : 4; break;
            case "block" : y = state <= 2 ? 5 : 6; break;
            case "sharpen" : y = state <= 2 ? 7 : 8; break;
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