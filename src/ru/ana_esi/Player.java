package ru.ana_esi;

import ru.ana_esi.structure.EditableLinkedStack;
import ru.ana_esi.structure.LinkedDeque;
import ru.ana_esi.structure.LinkedQueue;
import ru.ana_esi.structure.LinkedStack;

import java.awt.*;

public class Player {

    private boolean isLost;

    private String name;
    private Color color;

    private int figure;
    private int maxActionPerTurn;
    private int actionsLeft;
    private int mapSize;
    private int turn = 0;

    private final int ID_MAIN;
    private final int ID_CONQUERED;
    private final int ID_POSSIBLE;

    private Cell possiblePos [][];


    public Player (String name, Color color, int maxActions, int figure, Cell startPos, int size, int id) {
        this.name = name;
        this.color = color;
        this.figure = figure;
        this.maxActionPerTurn = maxActions;
        this.isLost = false;
        this.actionsLeft = maxActionPerTurn;
        this.mapSize = size;

        ID_MAIN = id;
        ID_CONQUERED = ID_MAIN + 1;
        ID_POSSIBLE = ID_MAIN + 2;

        possiblePos = new Cell[size][size];
        initPossiblePosArray();

        possiblePos[startPos.getRow()][startPos.getCol()].setPlayer_state_id(ID_POSSIBLE);
    }


    public Color getColor () {
        return color;
    }

    public int getFigure () {
        return figure;
    }

    public String getName() {
        return name;
    }

    public int getId_main() {
        return ID_MAIN;
    }

    public int getId_conquered() {
        return ID_CONQUERED;
    }

    public int getId_possible() {
        return ID_POSSIBLE;
    }

    public int getTurn() {
        return turn;
    }

    public void incrementTurn () {
        turn++;
    }

    public Cell [][] getPlayerPossibleTurns() {
        return possiblePos;
    }

    public void setPossibleTurn (int x, int y) {
        possiblePos[x][y].setPlayer_state_id(ID_POSSIBLE);
        isLost = false;
    }

    private void initPossiblePosArray () {
        for (int i = 0; i < mapSize; i++)
            for (int j = 0; j < mapSize; j++)
                possiblePos[i][j] = new Cell(1, 1, 2 ,2, i, j);
    }

    public void clearPossibleTurns () {
        possiblePos = new Cell [mapSize][mapSize];
        initPossiblePosArray();
        isLost = true;
    }

    public boolean isLost() {
        return isLost;
    }

    public boolean isPossibleTurn (int x, int y) {
        return (possiblePos[x][y].getPlayer_state_id() == ID_POSSIBLE);
    }

    public boolean isPossibleTurn (Cell cell) {
        return (possiblePos[cell.getRow()][cell.getCol()].getPlayer_state_id() == cell.getPlayer_state_id());
    }

    public int makeAction () {
            actionsLeft--;

            if (actionsLeft > 0)
                return 0;
            else {
                actionsLeft = maxActionPerTurn;
                return 1;
            }

    }

    @Override
    public String toString () {
        return "" + color;
    }

    @Override
    public boolean equals (Object o) {
        if (o == null)
            return false;

        if (o.getClass() != this.getClass())
            return false;

        Player p = (Player) o;

        if (color.equals(p.color) && name.equals(p.name) && figure == p.figure)
            return true;
        return false;
    }

}
