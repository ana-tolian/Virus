package ru.ana_esi.GComponent;

import ru.ana_esi.constant.Constant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GButton extends JButton {

    public GButton (String str) {
        super(str);
        
        setFocusPainted(false);
        setFont(Constant.GAME_FONT);
        setBackground(Constant.BUTTON_MENU_COLOR);
        setForeground(Constant.BUTTON_MENU_FONT_COLOR);
        setBorder(BorderFactory.createLineBorder(Constant.ELEMENT_MENU_COLOR, Constant.BUTTON_BORDER_THICKNESS));
        setPreferredSize(new Dimension(Constant.BUTTON_WIDTH, Constant.BUTTON_HEIGHT));

        addMouseListener(new MouseListener() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(Constant.BUTTON_MENU_COLOR_CURSOR_ON_BUTTON);
                setForeground(Constant.BUTTON_MENU_FONT_COLOR_CURSOR_ON_BUTTON);
                setBorder(BorderFactory.createLineBorder(Constant.ELEMENT_MENU_COLOR_CURSOR_ON_BUTTON, Constant.BUTTON_BORDER_THICKNESS));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(Constant.BUTTON_MENU_COLOR);
                setForeground(Constant.BUTTON_MENU_FONT_COLOR);
                setBorder(BorderFactory.createLineBorder(Constant.ELEMENT_MENU_COLOR, Constant.BUTTON_BORDER_THICKNESS));
            }

            @Override
            public void mousePressed(MouseEvent e) { }

            @Override
            public void mouseClicked(MouseEvent e) { }

            @Override
            public void mouseReleased(MouseEvent e) { }
        });
    }
}
