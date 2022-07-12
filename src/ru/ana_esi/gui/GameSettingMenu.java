package ru.ana_esi.gui;

import ru.ana_esi.Changable;
import ru.ana_esi.GComponent.GButton;
import ru.ana_esi.GComponent.GLabel;
import ru.ana_esi.GComponent.GPanel;
import ru.ana_esi.GComponent.GSlider;
import ru.ana_esi.listener.ButtonActionListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class GameSettingMenu extends GPanel {

    private GPanel numberOfPlayers;
    private GSlider players;
    private GLabel playersLabel;

    private GPanel mapSize;
    private GSlider sizeOfMap;
    private GLabel mapLabel;

    private GPanel playButtonPanel;
    private GButton playButton;
    private GButton backButton;



    public GameSettingMenu () {
        setLayout(new GridLayout(3, 1, 40, 40));

        numberOfPlayers = new GPanel(new FlowLayout());
        numberOfPlayers.setBorder(BorderFactory.createEmptyBorder(150, 0, 0, 0));

        playersLabel = new GLabel("Выберите количество игроков:    ");
        players = new GSlider(2, 4, 2);
        players.setMajorTickSpacing(1);
        players.setPaintTicks(true);
        players.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Changable.amountOfPlayers = ((JSlider) e.getSource()).getValue();
            }
        });
        numberOfPlayers.add(playersLabel);
        numberOfPlayers.add(players);

        /*          */
        /*          */

        mapSize = new GPanel(new FlowLayout());

        mapLabel = new GLabel("Выберите размер карты:              ");
        sizeOfMap = new GSlider(6, 30, 6);
        sizeOfMap.setMajorTickSpacing(6);
        sizeOfMap.setMinorTickSpacing(2);
        sizeOfMap.setPaintTicks(true);
        sizeOfMap.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Changable.mapSize = ((JSlider) e.getSource()).getValue();
            }
        });
        mapSize.add(mapLabel);
        mapSize.add(sizeOfMap);


        playButtonPanel = new GPanel(new FlowLayout(FlowLayout.RIGHT, 250, 10));

        playButton = new GButton("Играть!");
        playButton.setActionCommand("go");
        playButton.addActionListener(new ButtonActionListener());

        backButton = new GButton("Назад");
        backButton.setActionCommand("back");
        backButton.addActionListener(new ButtonActionListener());

        playButtonPanel.add(backButton);
        playButtonPanel.add(playButton);


        add(numberOfPlayers);
        add(mapSize);
        add(playButtonPanel);

    }

}
