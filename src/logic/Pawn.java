package logic;

import javax.swing.*;
import java.util.Objects;
import java.util.Vector;

public class Pawn implements Piece {
    Color color;

    public ImageIcon getImg() {
        if (color == Color.White) {
            return new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/WhitePawn.png")));
        } else {
            return new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/BlackPawn.png")));
        }
    }

    public Color getColor() {
        return color;
    }

    public Pawn(Color color) {
        this.color = color;
    }

    public Vector<Move> checkMove(Integer x, Integer y, Board board) {
        Vector<Move> vector = new Vector<>();
        for (int i = -1; i <= 1; i += 2) {
            if (color == Color.White && i == 1) {
                continue;
            } else if (color == Color.Black && i == -1) {
                continue;
            }
            for (int j = -1; j <= 1; j += 2) {
                if (y + i < 0 || y + i >= board.height || x + j < 0 || x + j >= board.width) {
                    continue;
                }
                if (board.board[y + i][x + j] == null) {
                    vector.add(new Move(new Position(x, y), new Position(x + j, y + i), false, null));
                }
            }
        }
        return vector;
    }

    public Vector<Move> checkCapture(Integer x, Integer y, Board board) {
        Vector<Move> vector = new Vector<>();
        for (int i = -1; i <= 1; i += 2) {
            for (int j = -1; j <= 1; j += 2) {
                if (y + i < 0 || y + i >= board.height || x + j < 0 || x + j >= board.width) {
                    continue;
                }
                if (y + (i * 2) < 0 || y + (i * 2) >= board.height || x + (j * 2) < 0 || x + (j * 2) >= board.width) {
                    continue;
                }
                if (board.board[y + i][x + j] == null) {
                    continue;
                }
                if (board.board[y + i][x + j].getColor() != color && board.board[y + (i * 2)][x + (j * 2)] == null) {
                    vector.add(new Move(new Position(x, y), new Position(x + (j * 2), y + (i * 2)), true, new Position(x + j, y + i)));
                }
            }
        }
        return vector;
    }
}
