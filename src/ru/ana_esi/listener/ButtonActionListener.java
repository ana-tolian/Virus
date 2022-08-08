package ru.ana_esi.listener;

import ru.ana_esi.*;
import ru.ana_esi.gui.PaintPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonActionListener implements ActionListener {

    private void createGameField () {
        Changeable.field = new Field();
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

        else if (e.getActionCommand().equals("resume"))
            Main.changePane(Main.pp);

        else if (e.getActionCommand().equals("menu"))
            Main.changePane(Main.mm);

    }
}
