package cavemenarena.undevined.com.cavemenarena.classes;

/**
 * Created by sleephead on 04.03.15.
 */
public class Stick {
    private int sharpness;

    public Stick() {
        this.sharpness = 0;
    }

    public int getSharpness() {
        return this.sharpness;
    }

    public void sharpen() {
        this.sharpness++;
    }

    public void abrade() {
        if(this.sharpness > 0) {
            this.sharpness--;
        }
    }

    public boolean isSword() {
        if(this.sharpness > 4) {
            return true;
        }
        else {
            return false;
        }
    }
}
