package com.vkg.pactice.games;

class GameState {
    private int roundsPlayed;
    private int p1Wins;
    private int p2Wins;
    private int draw;
    private Piece p1Choice;
    private Piece p2Choice;

    public GameState(final int roundsPlayed, final Player p1, final Player p2, final int draw) {
        this.roundsPlayed = roundsPlayed;
        this.p1Wins = p1.getTotalWins();
        this.p2Wins = p1.getTotalWins();
        this.draw = draw;
        updateChoices(p1, p2);
    }

    private void updateChoices(final Player p1, final Player p2) {
        p1Choice = p1.playerChoice();
        p2Choice = p2.playerChoice();
    }

    public GameState play(final Player p1, final Player p2) {
        updateChoices(p1, p2);
        if (p2Choice.canNotBreak(p1Choice))
        {
            p1Wins = p1.increaseWins();
            System.out.println("Player 1 " + " Wins");
        }
        else
        {
            p2Wins = p2.increaseWins();
            System.out.println("Player 2 Wins");
        }
        if(isDraw())
        {
            draw++;
            System.out.println("\n\t\t\t Draw \n");
        }
        roundsPlayed++;
        return this;
    }

    private boolean match(final Piece piece1, final Piece piece2) {
        return p1Choice == piece1 && p2Choice == piece2;
    }

    private boolean isDraw() {
        return p1Choice == p2Choice;
    }

    public void printState() {
        System.out.println("***** Round: " + roundsPlayed + " *********************\n");
        System.out.println("Number of Draws: " + draw + "\n");
        System.out.println("Player 1: " + p1Choice + "\t Player 1 Total Wins: " + p1Wins);
        System.out.println("Player 2: " + p2Choice+ "\t Player 2 Total Wins: " + p2Wins);
    }
}
