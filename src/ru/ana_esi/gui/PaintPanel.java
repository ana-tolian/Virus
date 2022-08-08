package ru.ana_esi.gui;

import ru.ana_esi.Changeable;
import ru.ana_esi.Field;
import ru.ana_esi.GComponent.GLabel;
import ru.ana_esi.Main;
import ru.ana_esi.constant.Constant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class PaintPanel extends JPanel implements MouseListener, KeyListener, Runnable {

    private Field tiles;
    private BufferedImage bi;

    private GLabel informationLabel;
    private boolean gameOver = false;

    public PaintPanel() {
        setFocusable(true);
        setPreferredSize(new Dimension(WIDTH + 1, HEIGHT + 1));
        addMouseListener(this);
        addKeyListener(this);
        setBackground(Constant.BACKGROUND_MENU_COLOR);

        informationLabel = new GLabel ("");
        informationLabel.setFocusable(false);
        this.add(informationLabel);

        this.tiles = Changeable.field;
        this.bi = tiles.getImage();

        start();
    }

    protected void paintComponent (Graphics g) {
        super.paintComponent(g);
        g.drawImage(bi, Constant.FIELD_SHEAR_X, Constant.FIELD_SHEAR_Y, this);
    }

    private void refreshLabel () {
        if (tiles.isGameOver()) {
            informationLabel.setText("Game is over: " + tiles.getCurrentPlayer().getName() + " defeated. Click on the screen to continue.");
            gameOver = true;

        } else
            informationLabel.setText("Turn: " + tiles.getNumberOfTurn() + " | " + tiles.getCurrentPlayer().getName() + " turn");
    }


    private void start () {
        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(60);
                refreshLabel();
                repaint();
            } catch (Exception e) {}

        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX()  - Constant.FIELD_SHEAR_X;
        int y = e.getY()  - Constant.FIELD_SHEAR_Y;

        tiles.paint(x, y);

        requestFocusInWindow();

        if (gameOver)
            Main.changePaneFromPaintPanel(Main.mm);

    }

    @Override
    public void mouseReleased(MouseEvent e) { }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            Main.changePaneFromPaintPanel(Main.ppane);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { }
}
