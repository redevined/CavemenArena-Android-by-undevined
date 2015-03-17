package cavemenarena.undevined.com.cavemenarena.classes;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sleephead on 04.03.15.
 */
public final class actions {
    private Map<Integer, String> actionname = new HashMap<Integer, String>();

    private actions() {
        this.actionname.put(new Integer(0), "sharpen");
        this.actionname.put(new Integer(1), "attack");
        this.actionname.put(new Integer(2), "defend");
    }

    public static String getActionName(int i) {

        return this.actionname.get(new Integer(i));
    }

    public static int getActionValue(String Name) {


        for (Map.Entry<Integer, String> entry : this.actionname.entrySet())
        {
            if(entry.getValue().equals(Name))
            return (int)entry.getKey();
        }
        return -1;
      }
    public static Boolean checkActionKey(int key) {

        if (this.actionname.containsKey(key)) {
            return true;
        } else {
            return false;
        }
    }
}
