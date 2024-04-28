package logic;

import javax.swing.*;
import java.util.Objects;
import java.util.Vector;

import static java.lang.Math.abs;

public class Queen implements Piece {
    Color color;

    public ImageIcon getImg(){
        if(color==Color.White){
            return new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/WhiteQueen.png")));
        }else{
            return new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/BlackQueen.png")));
        }
    }

    public Color getColor() {
        return color;
    }

    public Queen(Color color) {
        this.color = color;
    }

    public Vector<Move> checkMove(Integer x, Integer y, Board board) {
        Vector<Move> vector = new Vector<>();
        for (int i = -7; i <= 7; i++) {
            for (int j = -7; j <= 7; j++) {
                if (i == 0 || j == 0) {
                    continue;
                }
                if (y + i < 0 || y + i >= board.height || x + j < 0 || x + j >= board.width) {
                    continue;
                }
                if (board.board[y + i][x + j] == null && abs(i) == abs(j)) {
                    vector.add(new Move(new Position(x, y), new Position(x + j, y + i), false, null));
                }
            }
        }
        return vector;
    }

    public Vector<Move> checkCapture(Integer x, Integer y, Board board) {
        Vector<Move> vector = new Vector<>();
        boolean lastEnemy;
        Position lastEnemyPosition=null;
        for(int k=-1;k<=1;k+=2) {
            lastEnemy = false;
            for (int i = -1; i >= -7; i--) {
                if (y + i < 0 || y + i >= board.height || x + (i*k) < 0 || x + (i*k) >= board.width) {
                    break;
                }
                if(board.board[y + i][x + (i*k)]!=null && board.board[y + i][x + (i*k)].getColor() != board.board[y][x].getColor()){
                    if(!lastEnemy){
                        lastEnemy = true;
                        lastEnemyPosition = new Position(x+(i*k),y+i);
                    }else {
                        break;
                    }
                }
                if (board.board[y + i][x + (i*k)] == null && lastEnemy) {
                    vector.add(new Move(new Position(x, y), new Position(x + (i*k), y + i), true, lastEnemyPosition));
                }
            }
        }
        for(int k=-1;k<=1;k+=2) {
            lastEnemy = false;
            for (int i = 1; i <= 7; i++) {
                if (y + i < 0 || y + i >= board.height || x + (i*k) < 0 || x + (i*k) >= board.width) {
                    break;
                }
                if(board.board[y + i][x + (i*k)]!=null && board.board[y + i][x + (i*k)].getColor() != board.board[y][x].getColor()){
                    if(!lastEnemy){
                        lastEnemy = true;
                        lastEnemyPosition = new Position(x+(i*k),y+i);
                    }else {
                        break;
                    }
                }
                if (board.board[y + i][x + (i*k)] == null && lastEnemy) {
                    vector.add(new Move(new Position(x, y), new Position(x + (i*k), y + i), true, lastEnemyPosition));
                }
            }
        }
        return vector;
    }
}
