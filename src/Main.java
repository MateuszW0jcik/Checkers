import controller.Menu;

import javax.swing.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("Checkers");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setSize(700, 550);
        frame.setLocationRelativeTo(null);

        new Menu(frame);
    }
}
