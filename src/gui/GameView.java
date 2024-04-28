package gui;

import logic.Board;

import javax.swing.*;
import java.awt.*;

public class GameView {
    private JPanel mainPanel;
    private JPanel blackTimerPanel;
    private JPanel whiteTimerPanel;
    private JPanel boardPanel;
    private JLabel timerBlack;
    private JLabel timerWhite;


    public void drawBoard(Board board, logic.Color color) {
        boardPanel.removeAll();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        int[] whiteIndexes = {0, 1, 2, 3, 4, 5, 6, 7};
        int[] blackIndexes = {7, 6, 5, 4, 3, 2, 1, 0};
        int[] indexes;

        if (color == logic.Color.White) {
            indexes = whiteIndexes;
        } else {
            indexes = blackIndexes;
        }


        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board.getBoard()[i][j] != null) {
                    JLabel pieceLabel = new JLabel(board.getBoard()[i][j].getImg());
                    pieceLabel.setPreferredSize(new Dimension(64, 64));
                    gbc.gridx = indexes[j];
                    gbc.gridy = indexes[i];
                    boardPanel.add(pieceLabel, gbc);
                }
            }
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                JPanel square = new JPanel();
                square.setPreferredSize(new Dimension(64, 64));
                if ((i + j) % 2 == 0) {
                    square.setBackground(new Color(233, 217, 201));
                } else {
                    square.setBackground(new Color(130, 112, 98));
                }
                gbc.gridx = j;
                gbc.gridy = i;
                boardPanel.add(square, gbc);
            }
        }
        boardPanel.revalidate();
        boardPanel.repaint();
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JPanel getBoardPanel() {
        return boardPanel;
    }

    public JLabel getTimerBlack() {
        return timerBlack;
    }

    public JLabel getTimerWhite() {
        return timerWhite;
    }
}
