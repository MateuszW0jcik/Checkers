package logic;

import javax.swing.*;
import java.util.Vector;

public interface Piece {
    Color getColor();
    ImageIcon getImg();
    Vector<Move> checkMove(Integer x, Integer y, Board board);
    Vector<Move> checkCapture(Integer x, Integer y, Board board);
}
