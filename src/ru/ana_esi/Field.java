package ru.ana_esi;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import ru.ana_esi.constant.Colors;
import ru.ana_esi.constant.Constant;
import ru.ana_esi.constant.Figure;
import ru.ana_esi.structure.LinkedQueue;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Field {

    private Cell map[][];
    private Cell possibleStartPos [];
    private LinkedQueue<Player> players;

    private GameLogic engine;

    private BufferedImage bi;
    private Graphics g;

    boolean gameOver = false;

    private final int LENGTH;

    private final int TILE_DIM;
    private final int MODIF_WIDTH;
    private final int MODIF_HEIGHT;


    public Field () {
        this.players = new LinkedQueue<Player>();
        this.LENGTH = Changable.mapSize;

        TILE_DIM = Constant.WIDTH / LENGTH;
        MODIF_WIDTH = Constant.WIDTH - (Constant.WIDTH - LENGTH * TILE_DIM) + 1;
        MODIF_HEIGHT = Constant.HEIGHT - (Constant.HEIGHT - LENGTH * TILE_DIM) + 1;

        calculatePossibleStartPos();
        initMap();
        generatePlayers();

        bi = new BufferedImage(MODIF_WIDTH, MODIF_HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = bi.getGraphics();

        paintGameField();

    }



    private void calculatePossibleStartPos () {
        possibleStartPos = new Cell[] {
                new Cell(0, 0, TILE_DIM, TILE_DIM, 0, 0),

                new Cell(0, (LENGTH - 1)*TILE_DIM, TILE_DIM, (LENGTH - 1)*TILE_DIM+TILE_DIM, LENGTH - 1, 0),

                new Cell((LENGTH - 1)*TILE_DIM, 0, (LENGTH - 1)*TILE_DIM+TILE_DIM, TILE_DIM, 0, LENGTH - 1),

                new Cell((LENGTH - 1)*TILE_DIM, (LENGTH - 1)*TILE_DIM,
                        (LENGTH - 1)*TILE_DIM+TILE_DIM, (LENGTH - 1)*TILE_DIM+TILE_DIM,
                        LENGTH - 1, LENGTH - 1),
        };
    }

    private void initMap () {
        map = new Cell[LENGTH][LENGTH];

        for (int i = 0; i < LENGTH; i++) {
            for (int j = 0; j < LENGTH; j++) {
                map[i][j] = new Cell(i * TILE_DIM, j * TILE_DIM,
                        TILE_DIM * i + TILE_DIM, TILE_DIM * j + TILE_DIM, i, j);
            }
        }
    }


    private void paintGameField () {
        Graphics2D g = (Graphics2D) this.g;
        g.setStroke(new BasicStroke(1));
        g.setColor(Constant.GAME_FIELD_BACKGROUND_COLOR);
        g.fillRect(0,0, MODIF_WIDTH, MODIF_HEIGHT);

        g.setColor(Constant.LINE_COLOR);
        for (int i = 0; i < MODIF_WIDTH + 1; i += TILE_DIM) {
            g.drawLine(i, 0, i, MODIF_HEIGHT);
        }

        for (int i = 0; i < MODIF_HEIGHT + 1; i += TILE_DIM) {
            g.drawLine(0, i, MODIF_WIDTH, i);
        }
    }

    public void paint (int x, int y) {
        for (int i = 0; i < LENGTH; i++)
            for (int j = 0; j < LENGTH; j++) {
                if (map[i][j].contains(x,y)) {
                    makeAction(map[i][j]);
                    return;
                }
            }
    }

/////////////////////////////////////////////////////
//    private void paintStartPos (Player p) {
//        Iterator<Cell> it = p.getPossiblePos().iterator();
//
//        while (it.hasNext()) {
//            Cell c = it.next();
//            c.fill(g, Constant.POSSIBLE_POS_COLOR);
//        }
//    }
//
//    private void paintPlayerTurns (Player p) {
//        Iterator<Cell> it = p.getOccupiedPos().iterator();
//
//        while (it.hasNext()) {
//            Cell c = it.next();
//            c.paint(g, p);
//        }
//
//    }
///////////////////////////////////////////////////////


    private void makeAction (Cell cell) {
        int game_state = engine.move(cell.getRow(), cell.getCol());

        if (game_state == 0) {                  // Turn is permitted AND actionsLeft > 0
            cell.paint(g, players.peek());

        } else if (game_state == 1) {           // Turn is permitted AND actionsLeft == 0
            cell.paint(g, players.peek());
            nextPlayer();

        } else if (game_state == 2) {           // Turn is prohibited
            // TODO

        } else if (game_state == -1) {          // Game is over (no permitted turns for current player AND actionsLeft > 0)
            gameOver = true;
            // TODO
        }
    }

    private void nextPlayer () {
        Player p = players.dequeue();
        players.enqueue(p);
    }


    private void generatePlayers () {
//        for (int i = 0; i < Changable.amountOfPlayers; i++) {
        int fig = Figure.figures[Generator.random(0, Figure.figures.length)];
        Player p1 = new Player("Player " + (0+1), Colors.colors[0], Changable.maxActionsPerTurn, fig, getStartPos(0), LENGTH, (0+1)*3);
        players.enqueue(p1);

        fig = Figure.figures[Generator.random(0, Figure.figures.length)];
        Player p2 = new Player("Player " + (1+1), Colors.colors[2], Changable.maxActionsPerTurn, fig, getStartPos(3), LENGTH, (1+1)*3);
        players.enqueue(p2);

        engine = new GameLogic(players, p1, p2, map);
    }

    private Cell getStartPos (int i) {
        Cell cell = possibleStartPos[i];
        System.out.println(cell);
        return cell;
    }

    public int getNumberOfTurn () {
        int turn = 0;
        for (Player player : players)
            turn += player.getTurn();
        return turn / (Changable.maxActionsPerTurn * Changable.amountOfPlayers);
    }

    public boolean isGameOver () {
        return gameOver;
    }

    public Player getCurrentPlayer () {
        return players.peek();
    }

    public BufferedImage getImage () {
        return bi;
    }

}
