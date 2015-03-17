package cavemenarena.undevined.com.cavemenarena.classes;

/**
 * Created by sleephead on 04.03.15.
 */
public class caveman {
    int health;
    stick weapon;
    int nextAction;

    public caveman() {
        this.health = 1;
        this.nextAction = 0;
        this.weapon = new stick();
    }

    public int getNextAction() {
        return this.nextAction;
    }

    public String getActionString() {
        return actions.getActionName(this.nextAction);
    }

    public Boolean setNextAction(int i) {
        if(actions.checkActionKey(i)) {
            this.nextAction = i;
            return true;
        }
        return false;
    }

    public Boolean setNextActionString(String ActionName) {
        int myAction;
        myAction = actions.getActionValue(ActionName);
        if(myAction == -1) {
            return false;
        }
        return true;
    }

    public int getStickSharpness() {
        return this.weapon.getSharpness();
    }
}
