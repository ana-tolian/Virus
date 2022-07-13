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

    private GPanel numberOfActions;
    private GSlider actions;
    private GLabel actionsLabel;
    private GLabel actionsInfo;

    private GPanel mapSize;
    private GSlider sizeOfMap;
    private GLabel mapLabel;
    private GLabel mapInfo;

    private GPanel playButtonPanel;
    private GButton playButton;
    private GButton backButton;


    public GameSettingMenu () {
        setLayout(new GridLayout(3, 1, 40, 40));

        numberOfActions = new GPanel(new FlowLayout());
        numberOfActions.setBorder(BorderFactory.createEmptyBorder(150, 0, 0, 0));

        actionsLabel = new GLabel("Количество действий в ход:    ");
        actions = new GSlider(3, 6, 3);
        actions.setMajorTickSpacing(1);
        actions.setPaintTicks(true);
        actions.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Changable.maxActionsPerTurn = ((JSlider) e.getSource()).getValue();
                actionsInfo.setText("   " +actions.getValue());
            }
        });

        actionsInfo = new GLabel ("   " + actions.getValue());

        numberOfActions.add(actionsLabel);
        numberOfActions.add(actions);
        numberOfActions.add(actionsInfo);

        /*          */
        /*          */

        mapSize = new GPanel(new FlowLayout());

        mapLabel = new GLabel("Размер карты:                     ");
        sizeOfMap = new GSlider(6, 30, 6);
        sizeOfMap.setMajorTickSpacing(6);
        sizeOfMap.setMinorTickSpacing(2);
        sizeOfMap.setPaintTicks(true);
        sizeOfMap.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Changable.mapSize = ((JSlider) e.getSource()).getValue();
                mapInfo.setText("   " + sizeOfMap.getValue() + "x" + sizeOfMap.getValue());
            }
        });

        mapInfo = new GLabel ("   " + sizeOfMap.getValue() + " x " + sizeOfMap.getValue());

        mapSize.add(mapLabel);
        mapSize.add(sizeOfMap);
        mapSize.add(mapInfo);


        playButtonPanel = new GPanel(new FlowLayout(FlowLayout.RIGHT, 250, 10));

        playButton = new GButton("Играть!");
        playButton.setActionCommand("go");
        playButton.addActionListener(new ButtonActionListener());

        backButton = new GButton("Назад");
        backButton.setActionCommand("back");
        backButton.addActionListener(new ButtonActionListener());

        playButtonPanel.add(backButton);
        playButtonPanel.add(playButton);


        add(numberOfActions);
        add(mapSize);
        add(playButtonPanel);

    }

}
