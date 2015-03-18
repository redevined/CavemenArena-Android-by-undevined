package cavemenarena.undevined.com.cavemenarena.classes;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sleephead on 04.03.15.
 */
public final class Actions {
    //private static Map<Integer, String> actionname = new HashMap<Integer, String>();

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


    private Actions() {
    }

    //public static void init(){
    //    actionname.put(new Integer(0), "sharpen");
    //    actionname.put(new Integer(1), "poke");
    //    actionname.put(new Integer(2), "block");
    // }
    /*
    public static String getActionName(int i) {

        return actionname.get(new Integer(i));
    }

    public static int getActionValue(String Name) {


        for (Map.Entry<Integer, String> entry : actionname.entrySet())
        {
            if(entry.getValue().equals(Name))
            return (int)entry.getKey();
        }
        return -1;
      }*/
    public static Boolean checkActionKey(int key) {
        switch(key) {
            case SHARPEN:
                return true;
            case POKE:
                return true;
            case BLOCK:
                return true;
        }
        return false;

    }
}

