package ru.ana_esi.GComponent;

import ru.ana_esi.constant.Constant;

import javax.swing.*;
import java.awt.*;

public class GPanel extends JPanel {

    public GPanel () {
        setBackground(Constant.BACKGROUND_MENU_COLOR);
    }

    public GPanel (LayoutManager lm) {
        super(lm);
        setBackground(Constant.BACKGROUND_MENU_COLOR);
    }

    public GPanel (LayoutManager2 lm) {
        super(lm);
        setBackground(Constant.BACKGROUND_MENU_COLOR);
    }
}
