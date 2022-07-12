package ru.ana_esi.GComponent;

import ru.ana_esi.constant.Constant;

import javax.swing.*;

public class GLabel extends JLabel {

    public GLabel (String str) {
        super(str);
        setFont(Constant.GAME_FONT);
        setBackground(Constant.BUTTON_MENU_COLOR);
        setForeground(Constant.BUTTON_MENU_FONT_COLOR);
    }
}
