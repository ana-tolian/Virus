package ru.ana_esi;

public class Player1111 {

    private int mapSize;
    private int id_main;
    private int id_conquered;
    private int id_possible;
    private int playerPossibleTurns [][];

    private boolean canMove = true;

    public Player1111(int id_main, int id_conquered, int id_possible, int mapSize) {
        this.id_main = id_main;
        this.id_conquered = id_conquered;
        this.id_possible = id_possible;
        this.mapSize = mapSize;
        this.playerPossibleTurns = new int [mapSize][mapSize];
    }

    public int getId_main() {
        return id_main;
    }

    public int getId_conquered() {
        return id_conquered;
    }

    public int getId_possible() {
        return id_possible;
    }

    public int[][] getPlayerPossibleTurns() {
        return playerPossibleTurns;
    }

    public void setPossibleTurn (int x, int y) {
        playerPossibleTurns[x][y] = id_possible;
        canMove = true;
    }

    public boolean isCanMove() {
        return canMove;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    public boolean isPossibleTurn (int x, int y) {
        return (playerPossibleTurns[x][y] == id_possible);
    }

    public void clearPossibleTurns () {
        playerPossibleTurns = new int [mapSize][mapSize];
        canMove = false;
    }

}
