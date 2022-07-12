package ru.ana_esi.gui;

import com.sun.org.apache.xalan.internal.res.XSLTErrorResources;
import ru.ana_esi.Changable;
import ru.ana_esi.Field;
import ru.ana_esi.GComponent.GLabel;
import ru.ana_esi.GComponent.GPanel;
import ru.ana_esi.Main;
import ru.ana_esi.constant.Constant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

public class PaintPanel extends JPanel implements MouseListener, Runnable {

    private Field tiles;
    private BufferedImage bi;

    private GLabel informationLabel;
    private boolean gameOver = false;

    public PaintPanel() {
        setPreferredSize(new Dimension(WIDTH + 1, HEIGHT + 1));
        addMouseListener(this);
        setBackground(Constant.BACKGROUND_MENU_COLOR);

        informationLabel = new GLabel ("");
        this.add(informationLabel);

        this.tiles = Changable.field;
        this.bi = tiles.getImage();

        start();
    }

    protected void paintComponent (Graphics g) {
        super.paintComponent(g);
        g.drawImage(bi, Constant.FIELD_SHEAR_X, Constant.FIELD_SHEAR_Y, this);

        if (tiles.isGameOver()) {
            informationLabel.setText("Game is over: " + tiles.getCurrentPlayer().getName() + " defeated. Click on the screen to continue.");
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.RED);
            g2.setStroke(new BasicStroke(2));
            g2.drawRect(Constant.FIELD_SHEAR_X, Constant.FIELD_SHEAR_Y, bi.getWidth() - 1, bi.getHeight() - 1);
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

        if (gameOver)
            Main.changeBackToMainMenu();

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


}
