package ru.ana_esi;

import ru.ana_esi.structure.LinkedQueue;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;


public class GameLogic {

    private Player player1;
    private Player player2;

    private Cell map [][];

    private boolean gameOver = false;

    private ArrayList<Point> floodNodes;
    private LinkedQueue<Player> playerStack;


    public GameLogic (LinkedQueue<Player> playerStack, Player p1, Player p2, Cell map[][]) {
        this.map = map; //new Cell [size][size];

        floodNodes = new ArrayList<>();
        this.playerStack = playerStack;

        player1 = p1;
        player2 = p2;
    }

    private void findAllFloodNodes (Player p) {
        for (int i = 0; i < map.length; i++)
            for (int j = 0; j < map.length; j++) {
                if (map[i][j].getPlayer_state_id() == p.getId_main())
                    floodNodes.add(new Point(i, j));
            }
    }

    private ArrayList<Point> flood (int x, int y, Player player) {
        ArrayList<Point> possibleNodes = new ArrayList<>();
        Player playerAnother = ((player == player1) ? player2 : player1);
        int dir [] = new int [4];
        Cell tempMap [][] = cloneMap();
        int px;
        int py;
        Point p;

        for (int i = 0; i < floodNodes.size(); i++) {
            p = floodNodes.get(i);
            px = p.x;
            py = p.y;

            if (tempMap[px][py].getPlayer_state_id() == player.getId_main() - 1) {
                continue;
            } else {
                tempMap[px][py].setPlayer_state_id(player.getId_main() - 1);
            }

            dir[0] = py - 1;    // up
            dir[1] = py + 1;    // down
            dir[2] = px - 1;    // left
            dir[3] = px + 1;    // right

            for (int j = 0; j < dir.length; j++) {
                if (dir[j] == -1)
                    dir[j]++;
                else if (dir[j] == tempMap.length)
                    dir[j]--;
            }

            for (int j = 0; j < dir.length / 2; j++) {
                if (tempMap[px][dir[j]].getPlayer_state_id() == player.getId_main()
                        || tempMap[px][dir[j]].getPlayer_state_id() == player.getId_main() - 1
                            || tempMap[px][dir[j]].getPlayer_state_id() == player.getId_conquered()) {
                    floodNodes.add(new Point(px, dir[j]));

                } else if (tempMap[px][dir[j]].getPlayer_state_id() != playerAnother.getId_conquered())
                    possibleNodes.add(new Point(px, dir[j]));

                if (tempMap[dir[j + 2]][py].getPlayer_state_id() == player.getId_main()
                        || tempMap[dir[j + 2]][py].getPlayer_state_id() == player.getId_main() - 1
                            || tempMap[dir[j + 2]][py].getPlayer_state_id() == player.getId_conquered()) {
                    floodNodes.add(new Point(dir[j + 2], py));

                } else  if (tempMap[dir[j + 2]][py].getPlayer_state_id() != playerAnother.getId_conquered())
                    possibleNodes.add(new Point(dir[j + 2], py));
            }

        }

        floodNodes.clear();
        return possibleNodes;
    }

    private Cell [][] cloneMap () {
        Cell a [][] =  new Cell [map.length][map.length];

        for (int i = 0; i < map.length; i++)
            for (int j = 0; j < map.length; j++) {
                a[i][j] = map[i][j].clone();
            }
        return a;
    }

    private void writePossibleNodes(ArrayList<Point> possibleNodes, Player player) {
        for (Point p : possibleNodes) {
            player.setPossibleTurn(p.x, p.y);
        }
        possibleNodes.clear();
    }

    public int move (int x, int y) {
        Player p = playerStack.peek();
        int state;

        if (p.getTurn() != 0)
            refreshPlayerState(x, y, p);

        if (isGameOver(p)) {
            endGame(p);
            state = -1;
            return state;
        }

        state = move(x, y, p);
        if (state != 2)
            incrementTurn(p);
        return state;
    }

    private int move (int x, int y, Player player) {
        if (permitted(x, y, player)) {
            if (setMove(x, y, player) == 2)
                return 2;

//            printArray(map);          // debug

            if (player.makeAction() == 1)
                return  1;
            return 0;

        } else {
            System.out.println("Невозможный ход.");
            return 2;

        }
    }

    private boolean isGameOver (Player p) {
        if (p.isLost())
            return true;
        return false;
    }

    private void endGame(Player p) {
        System.out.println("Игрок id_" + p.getName() + " проиграл. Игра окончена.");
        gameOver = true;
    }

    private boolean permitted (int x, int y, Player player) {
        return player.isPossibleTurn(x, y);
    }

    private int setMove (int x, int y, Player player) {
        int cell_state = map[x][y].getPlayer_state_id();
        Player playerAnother = ((player == player1) ? player2 : player1);

        if (cell_state == 0)
            map[x][y].setPlayer_state_id(player.getId_main());
        else if (cell_state == playerAnother.getId_main())
            map[x][y].setPlayer_state_id(player.getId_conquered());
        else
            return 2;
        return 0;
    }


    private void refreshPlayerState (int x, int y, Player p) {
        p.clearPossibleTurns();
        findAllFloodNodes(p);
        writePossibleNodes(flood(x, y, p), p);
    }

    private void incrementTurn (Player p) {
        p.incrementTurn();
    }

    public void printArray (Cell a [][]) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                System.out.print(a[i][j].getPlayer_state_id() + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
