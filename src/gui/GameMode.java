package gui;

import javax.swing.*;

public class GameMode{

    private JPanel mainPanel;
    private JButton playOfflineButton;
    private JButton playWithComputerButton;
    private JButton playOnLANButton;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JButton getPlayOfflineButton() {
        return playOfflineButton;
    }

    public JButton getPlayWithComputerButton() {
        return playWithComputerButton;
    }

    public JButton getPlayOnLANButton() {
        return playOnLANButton;
    }
}
