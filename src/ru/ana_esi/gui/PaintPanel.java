package ru.ana_esi.gui;

import com.sun.org.apache.xalan.internal.res.XSLTErrorResources;
import ru.ana_esi.Changable;
import ru.ana_esi.Field;
import ru.ana_esi.GComponent.GPanel;
import ru.ana_esi.constant.Constant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

public class PaintPanel extends JPanel implements MouseListener, Runnable {

    private Field tiles;
    private BufferedImage bi;

    public PaintPanel() {
        setPreferredSize(new Dimension(WIDTH + 1, HEIGHT + 1));
        addMouseListener(this);

        this.tiles = Changable.field;
        this.bi = tiles.getImage();

        start();
    }

    protected void paintComponent (Graphics g) {
        super.paintComponent(g);
        g.drawImage(bi, 30, 30, this);
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
        int x = e.getX() - Constant.SHEAR;
        int y = e.getY() - Constant.SHEAR;

        tiles.paint(x, y);
        repaint();
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
