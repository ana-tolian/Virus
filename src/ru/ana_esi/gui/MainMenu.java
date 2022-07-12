package ru.ana_esi.gui;

import ru.ana_esi.GComponent.GButton;
import ru.ana_esi.GComponent.GPanel;
import ru.ana_esi.constant.Constant;
import ru.ana_esi.listener.ButtonActionListener;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends GPanel {

    private GButton playButton;
    private GButton exitButton;

    private GPanel buttonPanel;


    public MainMenu() {
        playButton = new GButton("Играть");
        playButton.setActionCommand("play");
        playButton.addActionListener(new ButtonActionListener());

        exitButton = new GButton("Выход");
        exitButton.setActionCommand("exit");
        exitButton.addActionListener(new ButtonActionListener());


        buttonPanel = new GPanel();
        buttonPanel.setPreferredSize(new Dimension(Constant.BUTTON_PANEL_WIDTH, Constant.BUTTON_PANEL_HEIGHT));
        buttonPanel.setLayout(new FlowLayout(0,0,20));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(250, 10, 10, 10));
        buttonPanel.add(playButton);
        buttonPanel.add(exitButton);


        setLayout(new BorderLayout());
        add(buttonPanel, BorderLayout.CENTER);

    }

}
