package cavemenarena.undevined.com.cavemenarena.classes;

/**
 * Created by sleephead on 04.03.15.
 */
public class Caveman {
    int health;
    Stick weapon;
    int nextAction;
    String name;

    public Caveman(String playername) {
        this.health = 1;
        this.nextAction = 0;
        this.weapon = new Stick();
        this.name = playername;
    }

    public int getNextAction() {
        return this.nextAction;
    }

    public String getActionString() {
        return Actions.getActionName(this.nextAction);
    }

    public Boolean setNextAction(int i) {
        if(Actions.checkActionKey(i)) {
            this.nextAction = i;
            return true;
        }
        return false;
    }

    public Boolean setNextActionString(String ActionName) {
        int myAction;
        myAction = Actions.getActionValue(ActionName);

        if(myAction == -1) {
            return false;
        }
        return true;
    }

    public int getStickSharpness() {
        return this.weapon.getSharpness();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
