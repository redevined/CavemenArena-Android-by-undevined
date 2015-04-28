package cavemenarena.undevined.com.cavemenarena.classes.Sprites;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import cavemenarena.undevined.com.cavemenarena.R;
import cavemenarena.undevined.com.cavemenarena.classes.Actions;
import cavemenarena.undevined.com.cavemenarena.classes.Caveman;

import java.util.HashMap;

/**
 * Demo Sprite class, shown on the starting page view.
 * Created by redevined on 16.04.15.
 */
public class DemoSprite extends Sprite {

    int counter;
    HashMap<String,Bitmap> frames;

    public DemoSprite(Resources resources) {
        this.counter = 0;
        this.frames = loadFrames(resources);
    }

    @Override
    protected void update() { }

    @Override
    public Bitmap getFrame() {
        String action = "idle";
        int state = 0;

        switch (this.counter / 4) {
            case 1:
                action = "poke";
                break;
            case 3:
                action = "block";
                break;
            case 5:
                action = "sharpen";
                break;
        }

        if (action.equals("idle")) {
            state = (this.counter % 2) + 1;
        } else {
            state = (this.counter % 4) + 1;
        }

        this.counter = this.counter++ % 24;

        String key = action + "_" + state;
        Bitmap frame = this.frames.get(key);

        return frame;
    }

}