package com.vkg.pactice.games;

/**
 * Simulate a mockgame of Rock, Paper, Scissors
 */
public class Game
{

    public static final int MAX_WINS = 3;

    public static void main(String args[])
    {
        new Game().play();
    }

    private void play() {
        Player p1 = new Player();
        Player p2 = new Player();
        // Game Loop
        GameState gameState = new GameState(0, p1, p2, 0);
        do
        {
            gameState.printState();
            gameState = gameState.play(p1, p2);
            System.out.println();
        } while(isNotOver(p1, p2));

        System.out.println("GAME WON");
    }

    private boolean isNotOver(final Player p1, final Player p2) {
        return stillPlaying(p1) && stillPlaying(p2);
    }

    private boolean stillPlaying(final Player p1) {
        return !p1.won(MAX_WINS);
    }

}
