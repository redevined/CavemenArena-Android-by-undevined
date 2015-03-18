package cavemenarena.undevined.com.cavemenarena.classes;

/**
 * Created by sleephead on 04.03.15.
 */
public class Caveman {
    /**
     * sharpen the weapon
     */
    public static final int SHARPEN = 0;
    /**
     * attack the enemy
     */
    public static final int POKE = 1;
    /**
     * block an attack
     */
    public static final int BLOCK = 2;
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

    //public String getActionString() {
    //    return Actions.getActionName(this.nextAction);
    //}

    public Boolean setNextAction(int i) {
        if(i == SHARPEN || i == POKE|| i==BLOCK) {
            this.nextAction = i;
            return true;
        }
        return false;
    }

    //public Boolean setNextActionString(String ActionName) {
    //    int myAction;
    //    myAction = Actions.getActionValue(ActionName);
    //    if(myAction == -1) {
    //        return false;
    //    }
    //    return true;
    //}

    public int getStickSharpness() {
        return this.weapon.getSharpness();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void sharpenStick(){
        this.weapon.sharpen();
    }
    public void abradeStick(){
        this.weapon.abrade();
    }

    public void loseHealth(){
        this.health--;
    }
    public boolean loseHealth(int i){
        if (i > 0) {
            this.health = (i > this.health) ? this.health - i : 0;
            return true;
        }
        return false;
    }
    public boolean isDead(){
        if(this.health <= 0){
            return true;
        }
        return false;
    }

    public void kill(){
        this.health = 0;
    }
    public boolean stickIsSword(){
        if(this.weapon.isSword()){
            return true;
        }
        return false;
    }
}
