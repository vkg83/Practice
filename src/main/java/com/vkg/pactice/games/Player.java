package com.vkg.pactice.games;

class Player {
    int totalWins;

    public Piece playerChoice() {
        return Piece.getRandomPiece();
    }

    public int increaseWins() {
        return totalWins++;
    }
    public int getTotalWins() {
        return(totalWins);
    }

    boolean won(final int requiredWins) {
        return getTotalWins() >= requiredWins;
    }
}
