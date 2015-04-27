package cavemenarena.undevined.com.cavemenarena.classes;

/**
 * Created by sleephead on 04.03.15.
 */
public class Caveman {

    private int health;
    private Stick weapon;
    private int nextAction;
    private String name;

    public Caveman(String playername) {
        this.health = 1;
        this.nextAction = 0;
        this.weapon = new Stick();
        this.name = playername;
    }

    public int getNextAction() {
        return this.nextAction;
    }

    public Boolean setNextAction(int i) {
        if(Actions.checkActionKey(i)) {
            this.nextAction = i;
            return true;
        }
        return false;
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

    public void sharpenStick(){
        this.weapon.sharpen();
    }

    public void abradeStick(){
        this.weapon.abrade();
    }

    public void loseHealth(){
        this.health--;
    }

    /*
    public boolean loseHealth(int i){
        if (i > 0) {
            this.health = (i > this.health) ? this.health - i : 0;
            return true;
        }
        return false;
    }
    */

    public boolean isDead(){
        return this.health <= 0;
    }

    public void kill(){
        this.health = 0;
    }

    public boolean stickIsSword(){
        return this.weapon.isSword();
    }

    public boolean weaponIsBlunt(){
        return this.weapon.getSharpness() == 0;
    }

}
