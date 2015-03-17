package cavemenarena.undevined.com.cavemenarena.classes;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sleephead on 04.03.15.
 */
public final class Actions {
    private static Map<Integer, String> actionname = new HashMap<Integer, String>();

    private Actions() {
        actionname.put(new Integer(0), "sharpen");
        actionname.put(new Integer(1), "attack");
        actionname.put(new Integer(2), "defend");
    }

    public static void init(){
        actionname.put(new Integer(0), "sharpen");
        actionname.put(new Integer(1), "attack");
        actionname.put(new Integer(2), "defend");
     }

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
      }
    public static Boolean checkActionKey(int key) {

        if (actionname.containsKey(key)) {
            return true;
        } else {
            return false;
        }
    }
}

