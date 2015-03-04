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
}
