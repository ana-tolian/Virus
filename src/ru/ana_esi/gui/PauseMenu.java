package ru.ana_esi.gui;

import ru.ana_esi.GComponent.GButton;
import ru.ana_esi.GComponent.GLabel;
import ru.ana_esi.GComponent.GPanel;
import ru.ana_esi.constant.Constant;
import ru.ana_esi.listener.ButtonActionListener;

import javax.swing.*;
import java.awt.*;

public class PauseMenu extends GPanel {

    private GLabel logoLabel;

    private GButton playButton;
    private GButton exitToMenuButton;
    private GButton exitButton;

    private GPanel buttonPanel;
    private GPanel logoPanel;


    public PauseMenu() {
        logoLabel = new GLabel("The Virus");
        logoLabel.setFont(Constant.LOGO_FONT);
        logoLabel.setForeground(Constant.LOGO_FONT_COLOR);

        logoPanel = new GPanel();
        logoPanel.setBorder(BorderFactory.createEmptyBorder(80, 10, 10, 10));
        logoPanel.add(logoLabel);

        playButton = new GButton("Продолжить");
        playButton.setActionCommand("resume");
        playButton.addActionListener(new ButtonActionListener());

        exitToMenuButton = new GButton("Выйти в меню");
        exitToMenuButton.setActionCommand("menu");
        exitToMenuButton.addActionListener(new ButtonActionListener());

        exitButton = new GButton("Выход из игры");
        exitButton.setActionCommand("exit");
        exitButton.addActionListener(new ButtonActionListener());

        buttonPanel = new GPanel();
        buttonPanel.setPreferredSize(new Dimension(Constant.BUTTON_PANEL_WIDTH, Constant.BUTTON_PANEL_HEIGHT));
        buttonPanel.setLayout(new FlowLayout(0,0,20));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(100, 65, 10, 10));
        buttonPanel.add(playButton);
        buttonPanel.add(exitToMenuButton);
        buttonPanel.add(exitButton);

        setLayout(new BorderLayout());
        add(logoPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

    }
}
