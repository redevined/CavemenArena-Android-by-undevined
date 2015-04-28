package cavemenarena.undevined.com.cavemenarena.classes;

/**
 * Created by sleephead on 04.03.15.
 */
public final class Actions {

    /**
     * sharpen the weapon
     */
    public static final int SHARPEN = 0;
    /**
     * attack the enemy
     */
    public static final int POKE = 1;
    /**
     * block an atack
     */
    public static final int BLOCK = 2;

    private Actions() { }

    public static String getActionName(int key) {
        switch(key) {
            case SHARPEN:
                return "sharpen";
            case POKE:
                return "poke";
            case BLOCK:
                return "block";
        }
        return null;
    }

    public static int getActionInt(String name) {
        if (name.equals("sharpen")) {
            return SHARPEN;
        } else if (name.equals("poke")) {
            return POKE;
        } else if (name.equals("block")) {
            return BLOCK;
        }
        return 0;
    }

    public static Boolean checkActionKey(String key) {
        return key.equals("sharpen") || key.equals("poke") || key.equals("block");
    }

    public static Boolean checkActionKey(int key) {
        return key == SHARPEN || key == POKE || key == BLOCK;
    }

}

