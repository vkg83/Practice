package com.vkg.pactice.games;

public enum Piece {
    ROCK {
        @Override
        public boolean canNotBreak(Piece piece) {
            return PAPER == piece;
        }
    },
    PAPER {
        @Override
        public boolean canNotBreak(Piece piece) {
            return SCISSORS == piece;
        }
    },
    SCISSORS {
        @Override
        public boolean canNotBreak(Piece piece) {
            return ROCK == piece;
        }
    };

    public abstract boolean canNotBreak(Piece piece);
    static Piece getRandomPiece() {
        return Piece.values()[(int)(Math.random()*3)];
    }

}
