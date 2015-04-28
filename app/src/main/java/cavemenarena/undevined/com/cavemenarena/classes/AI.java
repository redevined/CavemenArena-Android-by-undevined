package cavemenarena.undevined.com.cavemenarena.classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Artificial Intelligence class.
 * Created by redevined on 17.03.2015.
 */
public class AI {

    /**
     * Template class for different levels of difficulty
     */
    public abstract class Brain {
        public abstract int thinkAboutIt(List<Integer> ownHist, List<Integer> enemyHist);

        protected int calcSharpness(List<Integer> hist) {
            return Collections.frequency(hist, Actions.SHARPEN) - Collections.frequency(hist, Actions.POKE);
        }

        protected Integer calcLastMove(List<Integer> hist) {
            if (hist.size() > 0) {
                return hist.get(hist.size() - 1);
            } else {
                return 0;
            }
        }
    }

    /**
     * Simple AI, lowest difficulty
     */
    public class SimpleBrain extends Brain {
        @Override
        public int thinkAboutIt(List<Integer> ownHist, List<Integer> enemyHist) {
            int ownSharpness = calcSharpness(ownHist);
            int enemySharpness = calcSharpness(enemyHist);
            int ownLastMove = calcLastMove(ownHist);

            int action = Actions.SHARPEN;

            if (ownSharpness != 0) {
                if (enemySharpness == 0) {
                    action = Actions.POKE;
                } else if (enemySharpness > 0 && ownLastMove != 2) {
                    action = Actions.BLOCK;
                }
            }

            return action;
        }
    }

    /**
     * Medium AI, average difficulty
     */
    public class MediumBrain extends Brain {
        @Override
        public int thinkAboutIt(List<Integer> ownHist, List<Integer> enemyHist) {
            int ownSharpness = calcSharpness(ownHist);
            int enemySharpness = calcSharpness(enemyHist);
            int ownLastMove = calcLastMove(ownHist);
            Random randGen = new Random();

            int action = Actions.SHARPEN;

            if (ownSharpness == 0) {
                if (0 < enemySharpness && enemySharpness < 5) {
                    if (randGen.nextBoolean()) {
                        action = Actions.BLOCK;
                    }
                }
            } else if (0 < ownSharpness && ownSharpness < 5){
                if (randGen.nextBoolean()) {
                    action = Actions.POKE;
                }
                if (0 < enemySharpness && enemySharpness < 5) {
                    if (ownSharpness < enemySharpness) {
                        if (randGen.nextBoolean()) {
                            action = Actions.BLOCK;
                        }
                    }
                }
            } else if (ownSharpness >= 5) {
                if (randGen.nextBoolean()) {
                    action = Actions.POKE;
                }
            }

            return action;
        }
    }

    /**
     * Hard AI, highest difficulty
     */
    public class HardBrain extends Brain {
        @Override
        public int thinkAboutIt(List<Integer> ownHist, List<Integer> enemyHist) {
            int ownSharpness = calcSharpness(ownHist);
            int enemySharpness = calcSharpness(enemyHist);
            int ownLastMove = calcLastMove(ownHist);

            int action = Actions.BLOCK;

            if (ownHist.size() == 0) {
                action = Actions.SHARPEN;
            } else if (ownSharpness > 0 || new Random().nextInt(4) == 0) {
                action = Actions.POKE;
            } else if (enemySharpness == 0) {
                action = Actions.SHARPEN;
            } else if (isStereotype(enemyHist, Actions.BLOCK) && ownLastMove != Actions.SHARPEN) {
                action = Actions.SHARPEN;
            } else if (enemySharpness == 4 || ownSharpness >= 5) {
                action = Actions.POKE;
            } else if (isStereotype(enemyHist, Actions.SHARPEN)) {
                action = Actions.SHARPEN;
            }

            return action;
        }

        private boolean isStereotype(List<Integer> hist, int action) {
            if (hist.size() < 4) {
                return false;
            }
            for (int i = hist.size() - 1; i > hist.size() - 4; i--) {
                if (hist.get(i) != action) {
                    return false;
                }
            }
            return true;
        }
    }

    // Actual AI core
    Brain brain;
    // Just in case...
    boolean becomeSkynet = false;

    /**
     * Constructor chooses AI depending on difficulty level (1 - 3)
     */
    public AI(int iq) {
        switch (iq) {
            case 1:
                brain = new SimpleBrain();
                break;
            case 2:
                brain = new MediumBrain();
                break;
            case 3:
                brain = new HardBrain();
                break;
        }
    }

    /**
     * Public method getAction() for invoking action generation
     */
    public int getAction(List<Integer> enm, List<Integer> own) {
        return brain.thinkAboutIt(own, enm);
    }
}
