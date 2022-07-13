package ru.ana_esi;

import ru.ana_esi.GComponent.GPanel;
import ru.ana_esi.constant.Constant;
import ru.ana_esi.gui.GameSettingMenu;
import ru.ana_esi.gui.MainMenu;
import ru.ana_esi.gui.PaintPanel;
import ru.ana_esi.gui.PauseMenu;

import javax.swing.*;

public class Main {

    public static boolean isGameStarted = false;

    public static GameSettingMenu gm = new GameSettingMenu();
    public static MainMenu mm = new MainMenu();
    public static PauseMenu ppane = new PauseMenu();
    public static GPanel root = new GPanel();
    public static PaintPanel pp;

    private static JFrame frame = new JFrame("Virus");


    private static void init () {
        changePane(mm);
        frame.setContentPane(root);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Constant.FRAME_WIDTH, Constant.FRAME_HEIGHT);
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }

    public static void changePane (JPanel pane) {
        if (!(pane instanceof PaintPanel)) {
            root.removeAll();
            root.add(pane);
            frame.revalidate();
            frame.repaint();
        } else {
            frame.remove(root);
            frame.setContentPane(pane);
            frame.revalidate();
            frame.repaint();
        }
    }

    public static void changePaneFromPaintPanel (JPanel pane) {
        frame.setContentPane(root);
        root.removeAll();
        root.add(pane);
        frame.revalidate();
        frame.repaint();
    }



    public static void main (String args []) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                init();
            }
        });
    }
}
