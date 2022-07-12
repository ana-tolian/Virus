package ru.ana_esi.GComponent;

import ru.ana_esi.constant.Constant;

import javax.swing.*;

public class GSlider extends JSlider {

    public GSlider () {
        setBackground(Constant.BACKGROUND_MENU_COLOR);
        setForeground(Constant.SLIDER_MENU_COLOR);
    }

    public GSlider (int min, int max, int value) {
        super(min, max, value);
        setBackground(Constant.BACKGROUND_MENU_COLOR);
        setForeground(Constant.SLIDER_MENU_COLOR);
    }
}
