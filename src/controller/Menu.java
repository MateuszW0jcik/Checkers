package controller;

import gui.GameMode;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu {
    public Menu(JFrame frame) {
        GameMode gameMode = new GameMode();
        frame.setContentPane(gameMode.getMainPanel());
        frame.setVisible(true);

        gameMode.getPlayOfflineButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PlayOffline(frame);
            }
        });
        gameMode.getPlayWithComputerButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ChooseSideVsComputer(frame);
            }
        });
        gameMode.getPlayOnLANButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ConnectionManagement(frame);
            }
        });

        frame.revalidate();
    }
}
