package com.vkg.pactice.backtrack;

import java.util.ArrayList;
import java.util.Arrays;

public class NQueen {

    private char[][] board;
    private int count;
    private ArrayList<ArrayList<String>> result;

    public NQueen(final int count) {
        this.count = count;
        init();
    }

    private void init() {
        board = new char[count][count];
        for (int i = 0; i < count; i++) {
            Arrays.fill(board[i], '.');
        }
    }
    public static void main(String[] args) {
        NQueen q = new NQueen(8);
        final ArrayList<ArrayList<String>> arrayLists = q.solveNQueens();
        for (ArrayList<String> list: arrayLists){
            for (String str :
                    list) {
                System.out.println(str);
            }
            System.out.println();
        }
    }

    public ArrayList<ArrayList<String>> solveNQueens() {
        result = new ArrayList<ArrayList<String>>();
        solve(0);
        return result;
    }

    private void solve(final int row) {
        if(row >=  count) {
            addBoard();
        }

        for (int i = 0; i < count; i++) {
            if(isSafe(board, row, i)) {
                board[row][i] = 'Q';
                solve(row + 1);
                board[row][i] = '.';
            }
        }
    }

    private void addBoard() {
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < count; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < count; j++) {
                sb.append(board[i][j]);
            }
            list.add(sb.toString());
        }
        result.add(list);
    }

    private boolean isSafe(final char[][] board, final int row, final int col) {
        for (int i = 0; i < row; i++) {
            if(board[i][col] == 'Q')
                return false;
        }
        for (int i = 0; i < col; i++) {
            if(board[row][i] == 'Q')
                return false;
        }
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if(board[i][j] == 'Q')
                return false;
        }
        for (int i = row - 1, j = col + 1; i >= 0 && j < board.length; i--, j++) {
            if(board[i][j] == 'Q')
                return false;
        }
        return true;
    }
}
