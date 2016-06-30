package com.vkg.pactice.placement;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PlacementSolverTest {
    @Test
    public void shouldPlace() throws Exception {
        int size = 4;
        PlacementSolver solver = new PlacementSolver(size);
        List<Card> cards = new ArrayList<Card>();
        cards.add(new Card(2, 1, 4));
        cards.add(new Card(2, 1, 4));
        cards.add(new Card(4, 1, 1));
        cards.add(new Card(3, 1, 1));
        cards.add(new Card(2, 2, 2));
        cards.add(new Card(2, 1, 2));
        cards.add(new Card(1, 1, 2));
        cards.add(new Card(2, 3, 2));
        cards.add(new Card(2, 2, 3));
        cards.add(new Card(3, 3, 3));
        cards.add(new Card(2, 1, 3));
        cards.add(new Card(1, 2, 3));

        int grid[][] = new int[size][size];
        List<Card> rCards = solver.solve(cards);
        for (int x = rCards.size() - 1; x >= 0;x--) {
            Card card = rCards.get(x);
            System.out.println(card.getRowSize() + " X " + card.getColumnSize() + " (" + card.getPriority() + ") [" + card.getX() + " , " + card.getY() + "]");
            for (int i = 0; i < card.getRowSize(); i++) {
                for (int j = 0; j < card.getColumnSize(); j++) {
                    grid[i+card.getX()][j+card.getY()] = x+1;
                }
            }
        }
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(String.format("%2d",grid[i][j]));
            }
            System.out.println();
        }
        System.out.println();
    }
}