package ru.ana_esi.GComponent;

import ru.ana_esi.constant.Constant;

import javax.swing.*;
import java.awt.*;

public class GButton extends JButton {

    public GButton (String str) {
        super(str);

        setFocusPainted(false);
        setFont(Constant.GAME_FONT);
        setBackground(Constant.BUTTON_MENU_COLOR);
        setForeground(Constant.BUTTON_MENU_FONT_COLOR);
        setBorder(BorderFactory.createLineBorder(Constant.ELEMENT_MENU_COLOR, 2));
        setPreferredSize(new Dimension(Constant.BUTTON_WIDTH, Constant.BUTTON_HEIGHT));
    }
}
