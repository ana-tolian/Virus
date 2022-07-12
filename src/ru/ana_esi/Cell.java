package ru.ana_esi;

import java.awt.*;

public class Cell {

    private Player occupiedBy = null;
    private Player strokedBy = null;

    private int player_state_id = 0;

    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private int row;
    private int col;
    private final int SH;
    private final int STEP;


    public Cell (int x1, int y1, int x2, int y2, int i, int j) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.row = j;
        this.col = i;
        this.SH = (int) ((x2 - x1) * 0.1);          // Отступ от границ ячейки
        this.STEP = (int) ((x2 - x1) * 0.25);       // Расстояние между штрихами (при закрашивании фигуры другого игрока)
    }

    public Cell (int x1, int y1, int x2, int y2, int i, int j, int player_state) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.row = j;
        this.col = i;
        this.SH = (int) ((x2 - x1) * 0.1);
        this.STEP = (int) ((x2 - x1) * 0.25);
        this.player_state_id = player_state;
    }



    public boolean contains (int x, int y) {
        if (x >= x1 && x < x2 && y >= y1 && y < y2)
            return true;
        return false;
    }

    public int paint (Graphics g, Player p) {
        if (strokedBy != null && occupiedBy != null)
            return -2;

        if (occupiedBy != null && occupiedBy.equals(p))
            return -1;

        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(p.getColor());
        g2.setStroke(new BasicStroke(4));

        if (occupiedBy == null) {
            paintFigure(g2, p);
            occupiedBy = p;
            return 1;

        } else {
            stroke(g2, p);
            strokedBy = p;
            return 2;
        }
    }

    public void fill (Graphics g, Color c) {
        Graphics2D g2 = (Graphics2D) g;
        if (occupiedBy != null) {
            paintFigure(g2, occupiedBy);
            return;
        }

        g2.setColor(c);
        g2.fillRect(x1 + 2, y1 + 2, x2 - x1 - 3, y2 - y1 - 3);
    }

    private void paintFigure (Graphics2D g2, Player p) {
        switch (p.getFigure()) {
            case 0:                         // Cross
                g2.drawLine(x1 + SH, y1 + SH, x2 - SH, y2 - SH);
                g2.drawLine(x1 + SH, y2 - SH, x2 - SH, y1 + SH);
                break;

            case 1:                         // Circle
                g2.drawOval(x1 + SH, y1 + SH, x2 - x1 - SH * 2, y2 - y1 - SH * 2);
                break;

            case 2:                         // Square
                g2.drawRect(x1 + SH, y1 + SH, x2 - x1 - SH * 2, y2 - y1 - SH * 2);
                break;
        }

    }

    private void stroke (Graphics2D g2, Player p) {
        g2.drawLine(x1 + SH + 1 * STEP, y1 + SH, x2 - SH, y1 - SH + 3 * STEP);
        g2.drawLine(x1 + SH + 2 * STEP, y1 + SH, x2 - SH, y1 - SH + 2 * STEP);
        g2.drawLine(x1 + SH + 3 * STEP, y1 + SH, x2 - SH, y1 - SH + 1 * STEP);

        g2.drawLine(x1 + SH, y1 + SH, x2 - SH, y2 - SH);

        g2.drawLine(x1 + SH, y1 + SH + 1 * STEP, x1 - SH + 3 * STEP, y2 - SH);
        g2.drawLine(x1 + SH, y1 + SH + 2 * STEP, x1 - SH + 2 * STEP, y2 - SH);
        g2.drawLine(x1 + SH, y1 + SH + 3 * STEP, x1 - SH + 1 * STEP, y2 - SH);
    }


    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    private int getWidth () {
        return (x2 - x1);
    }

    private int getHeight () {
        return (y2 - y1);
    }

    public Player getOccupiedBy() {
        return occupiedBy;
    }

    public Player getStrokedBy() {
        return strokedBy;
    }

    public void setOccupiedBy(Player occupiedBy) {
        this.occupiedBy = occupiedBy;
    }

    public void setStrokedBy(Player strokedBy) {
        this.strokedBy = strokedBy;
    }

    public int getPlayer_state_id() {
        return player_state_id;
    }

    public void setPlayer_state_id(int player_state_id) {
        this.player_state_id = player_state_id;
    }

    @Override
    public boolean equals (Object o) {
        if (o == null)
            return false;

        if (!(o instanceof Cell))
            return false;

        Cell c = (Cell) o;

        if (x1 == c.x1 && x2 == c.x2 && y1 == c.y1 && y2 == c.y2)
            return true;
        return false;

    }

    @Override
    public String toString () {
        return "(" + x1 + ";" + y1 + "), (" + x2 + ";" + y2 + ") | rc(" + (col) + ";" + (row) +")";
    }

    @Override
    public Cell clone () {
        return new Cell (x1, y1, x2, y2, row, col, player_state_id);    // player_state_id is VERY IMPORTANT!!!
    }

}
