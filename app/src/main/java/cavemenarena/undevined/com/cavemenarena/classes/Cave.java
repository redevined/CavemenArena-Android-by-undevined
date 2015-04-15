package cavemenarena.undevined.com.cavemenarena.classes;

import java.util.ArrayList;
import java.util.List;

import cavemenarena.undevined.com.cavemenarena.StartActivity;

/**
 * Created by sleephead on 17.03.15.
 */
public class Cave {

    private Caveman player1;
    private Caveman player2;
    private AI cpu;

    private List<Integer> historyPlayer1;
    private List<Integer> historyPlayer2;

    /*
    public Cave(String player1Name, String player2Name){
        this.generalInit(player1Name, player2Name);
        this.player2 = new Caveman(player2Name);
    }
    */

    public Cave(String player1Name, String player2Name, int level){
        this.generalInit(player1Name, player2Name);
        this.cpu = new AI(level);
    }

    private void generalInit(String player1Name, String player2Name){
        this.player1 = new Caveman(player1Name);
        this.player2 = new Caveman(player2Name);
        this.historyPlayer1 = new ArrayList<Integer>();
        this.historyPlayer2 = new ArrayList<Integer>();
    }

    public Boolean setPlayerAction(int action){
        player1.setNextAction(action);
        if(this.playRound())
            return true;
        else
            return false;
    }

    private Boolean playRound() {
        int player1Action;
        int player2Action;

        player1Action = player1.getNextAction();
        if(this.cpu != null){
            player2.setNextAction(cpu.getAction(historyPlayer1, historyPlayer2));
        }
        player2Action = player2.getNextAction();

        historyPlayer1.add(player1Action);
        historyPlayer2.add(player2Action);

        switch (player1Action) {
            case Actions.SHARPEN:
                switch (player2Action) {
                    case Actions.SHARPEN:
                        this.player1.sharpenStick();
                        this.getPlayer2().sharpenStick();
                        break;
                    case Actions.POKE:
                        this.sharpenPoke(player1, player2);
                        break;
                    case Actions.BLOCK:
                        this.sharpenBlock(player1, player2);
                        break;
                }
                break;
            case Actions.POKE:
                switch (player2Action) {
                    case Actions.SHARPEN:
                        this.sharpenPoke(player2, player1);
                        break;
                    case Actions.POKE:
                        this.player1.abradeStick();
                        this.player2.abradeStick();
                        if ((player1.stickIsSword() && !player2.stickIsSword())) {
                            player2.kill();

                        }
                        if ((player2.stickIsSword() && !player1.stickIsSword())) {
                            player1.kill();

                        }
                        break;
                    case Actions.BLOCK:
                        this.pokeBlock(player1, player2);
                        break;
                }
                break;
            case Actions.BLOCK:
                switch (player2Action) {
                    case Actions.SHARPEN:
                        this.sharpenBlock(player2, player1);
                        break;
                    case Actions.POKE:
                        this.pokeBlock(player2, player1);
                        break;

                    case Actions.BLOCK:
                        break;

                }
                break;

        }
        if(player1.isDead() || player2.isDead())
            return true;
        else
            return false;
    }

    private void sharpenPoke(Caveman one, Caveman other){

        if (other.stickIsSword()) {
            one.kill();
        } else if(!other.weaponIsBlunt()){
            one.loseHealth();
        }

        one.sharpenStick();

        other.abradeStick();
    }

    private void sharpenBlock(Caveman one, Caveman other) {
        one.sharpenStick();
    }

    private void pokeBlock(Caveman one, Caveman other) {
        one.abradeStick();
        if (one.stickIsSword()) {
            other.kill();
        }
    }

    public Caveman getPlayer1() {
        return player1;
    }


    public Caveman getPlayer2() {
        return player2;
    }


}
