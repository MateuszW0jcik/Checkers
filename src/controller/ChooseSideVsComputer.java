package controller;

import gui.ChooseSide;
import logic.Color;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChooseSideVsComputer {
    public ChooseSideVsComputer(JFrame frame){
        ChooseSide chooseSide = new ChooseSide();
        frame.setContentPane(chooseSide.getMainPanel());

        chooseSide.getWhiteButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PlayWithComputer(frame, Color.White);
            }
        });

        chooseSide.getBlackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PlayWithComputer(frame, Color.Black);
            }
        });

        frame.revalidate();
    }
}
