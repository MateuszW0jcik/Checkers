package gui;

import logic.Color;

import javax.swing.*;
import java.awt.*;

public class EndGamePopUp extends JDialog {
    private JButton playAgainButton;
    private JButton menuButton;
    private JLabel text;
    private JPanel mainPanel;

    public EndGamePopUp(JFrame frame, Color winner){
        super(frame,"Game ended",true);
        text.setText(winner + " wins!");
        text.setFont(new Font("Inter",Font.PLAIN,20));
        this.setSize(300,200);
        this.add(mainPanel);
        this.setLocationRelativeTo(null);
    }

    public JButton getPlayAgainButton() {
        return playAgainButton;
    }

    public JButton getMenuButton() {
        return menuButton;
    }
}
