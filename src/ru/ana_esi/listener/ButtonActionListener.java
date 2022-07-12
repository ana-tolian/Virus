package ru.ana_esi.listener;

import ru.ana_esi.*;
import ru.ana_esi.constant.Colors;
import ru.ana_esi.constant.Figure;
import ru.ana_esi.gui.PaintPanel;
import ru.ana_esi.structure.LinkedQueue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonActionListener implements ActionListener {

    private void createGameField () {
        Changable.field = new Field();
        Main.pp = new PaintPanel();
        Main.changePane(Main.pp);
        Main.isGameStarted = true;

    }


    private void exit () {
        System.out.println("Process terminated.");
        System.exit(0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("play"))
            Main.changePane(Main.gm);

        else if (e.getActionCommand().equals("exit"))
            exit();

        else if (e.getActionCommand().equals("back"))
            Main.changePane(Main.mm);

        else if (e.getActionCommand().equals("go"))
            createGameField();

    }
}
