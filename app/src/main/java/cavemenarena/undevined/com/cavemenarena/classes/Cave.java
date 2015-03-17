package cavemenarena.undevined.com.cavemenarena.classes;

/**
 * Created by sleephead on 17.03.15.
 */
public class Cave {

    private Caveman player1;
    private Caveman player2;
    public Cave(String player1Name, String player2Name){
        this.player1 = new Caveman(player1Name);
        this.player2 = new Caveman(player2Name);
    }

    public void playRound(){

    }

    public Caveman getPlayer1() {
        return player1;
    }

    public void setPlayer1(Caveman player1) {
        this.player1 = player1;
    }

    public Caveman getPlayer2() {
        return player2;
    }

    public void setPlayer2(Caveman player2) {
        this.player2 = player2;
    }
}
