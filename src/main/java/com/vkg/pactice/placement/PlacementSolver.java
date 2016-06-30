package com.vkg.pactice.placement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlacementSolver {
    private Cell[][] grid;
    private Map<Card, List<Cell>> allowedCardCellMap = new HashMap<Card, List<Cell>>();

    public PlacementSolver(final int rowSize, final int columnSize) {
        this.grid = new Cell[rowSize][columnSize];
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < columnSize; j++) {
                 grid[i][j] = new Cell();
            }
        }
    }

    public PlacementSolver(final int sideSize) {
        this(sideSize, sideSize);
    }

    public void allowCard(final Card card) {
        List<Cell> cardCells = new ArrayList<Cell>();
        for (int i = 0; i <= grid.length - card.getRowSize(); i++) {
            for (int j = 0; j <= grid[i].length - card.getColumnSize(); j++) {
                cardCells.add(grid[i][j]);
            }
        }
        allowedCardCellMap.put(card, cardCells);
    }

    public boolean canPlace(final Card card, int row, int col) {
        int endRow = row + card.getRowSize();
        int endCol = col + card.getColumnSize();
        for (int i = row; i < endRow; i++) {
            for (int j = col; j < endCol; j++) {
                if (grid[i][j].isOccupied()) {
                    return false;
                }
            }
        }

        return true;
    }
    public List<Integer[]> getAllowedPlaces(final Card card) {
        List<Integer[]> places = new ArrayList<Integer[]>();
        for (int i = 0; i <= grid.length - card.getRowSize(); i++) {
            for (int j = 0; j <= grid[i].length - card.getColumnSize(); j++) {
                if (grid[i][j].isBlank() && canPlace(card, i, j)) {
                    places.add(new Integer[]{i, j});
                }
            }
        }

        return places;
    }
    public void place(final Card card, final int row, final int col) {
        makePlacement(card, true, row, col);
        card.setX(row);card.setY(col);
    }

    public void remove(final Card card, final int row, final int col) {
        makePlacement(card, false, row, col);
    }

    private void makePlacement(final Card card, final boolean occupy, final int row, final int col) {
        int endRow = row + card.getRowSize();
        int endCol = col + card.getColumnSize();
        for (int i = row; i < endRow; i++) {
            for (int j = col; j < endCol; j++) {
                grid[i][j].setOccupied(occupy);
            }
        }
    }
    public List<Card> solve(List<Card> cards) {
        Collections.sort(cards);
        return solve(cards, grid.length * grid[0].length);
    }
    private List<Card> solve(List<Card> cards, int remainingSpace) {
        List<Card> minRemainingAreaList = null;
        int minRArea = remainingSpace;
        //for (int i = 0; i < cards.size(); i++) {
        if(cards.isEmpty()) return new ArrayList<Card>();
            final Card card=cards.get(0);
            List<Integer[]> places = getAllowedPlaces(card);
        if(places.isEmpty()) {
            minRemainingAreaList = solve(cards.subList(1, cards.size()), remainingSpace);
        } else {
            for (Integer[] place : places) {
                int row = place[0], col = place[1];
                List<Card> cardSubList = null;
                if (cards.size() > 1) {
                    place(card, row, col);
                    cardSubList = solve(cards.subList(1, cards.size()), remainingSpace - card.getArea());
                    remove(card, row, col);
                }

                if (cardSubList == null) {
                    cardSubList = new ArrayList<Card>();
                }

                int area = getArea(card, cardSubList);
                if (remainingSpace - area == 0) {
                    cardSubList.add(card);
                    return cardSubList;
                } else if (minRArea > area) {
                    minRArea = area;
                    minRemainingAreaList = cardSubList;
                }
            }
            minRemainingAreaList.add(card);
        }
        if(places.isEmpty()) {
            //printGrid();
        }

        return minRemainingAreaList;
    }

    private void printGrid() {
        for (int i = 0; i < grid.length; i++) {
            Cell[] cells = grid[i];
            for (int j = 0; j < cells.length; j++) {
                Cell cell = cells[j];
                char ch = cell.isOccupied() ? '@' : 'O';
                System.out.print(ch);
            }
            System.out.println();
        }
        System.out.println();
    }

    private int getArea(final Card fCard, final List<Card> cardSubList) {
        int area = fCard.getArea();
        if (cardSubList != null) {
            for (Card card : cardSubList) {
                area += card.getArea();
            }
        }
        return area;
    }
}
