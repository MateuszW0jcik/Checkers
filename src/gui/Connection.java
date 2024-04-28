package gui;

import javax.swing.*;

public class Connection {
    private JPanel mainPanel;
    private JTextField IPTextField;
    private JButton createGameButton;
    private JButton joinGameButton;
    private JLabel Error;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JButton getCreateGameButton() {
        return createGameButton;
    }

    public JButton getJoinGameButton() {
        return joinGameButton;
    }

    public JTextField getIPTextField() {
        return IPTextField;
    }

    public JLabel getError() {
        return Error;
    }
}
