package logic;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Vector;

public class Game {
    Board board;
    Color onMove;
    ArrayList<Move> whitePossibleMoves;
    ArrayList<Move> blackPossibleMoves;
    boolean gameFinished;
    Color winner;


    public Game(Integer width, Integer height) {
        this.board = new Board(width, height);
        this.onMove = Color.White;
        whitePossibleMoves = new ArrayList<>();
        blackPossibleMoves = new ArrayList<>();
        calculatePossibleMoves(Color.White);
        calculatePossibleMoves(Color.Black);
        gameFinished = false;
    }

    void calculatePossibleMoves(Color color) {
        clearPossibleMoves(color);
        for (int i = 0; i < board.height; i++) {
            for (int j = 0; j < board.width; j++) {
                if (board.board[i][j] == null) {
                    continue;
                }
                if (board.board[i][j].getColor().equals(color)) {
                    storeVectorInPossibleMoves(board.board[i][j].checkCapture(j, i, board), color);
                }
            }
        }
        if (color == Color.White && !whitePossibleMoves.isEmpty()) {
            return;
        } else if (color == Color.Black && !blackPossibleMoves.isEmpty()) {
            return;
        }
        for (int i = 0; i < board.height; i++) {
            for (int j = 0; j < board.width; j++) {
                if (board.board[i][j] == null) {
                    continue;
                }
                if (board.board[i][j].getColor().equals(color)) {
                    storeVectorInPossibleMoves(board.board[i][j].checkMove(j, i, board), color);
                }
            }
        }
    }

    public boolean move(Integer x, Integer y, Integer newX, Integer newY) {
        if (board.board[y][x] == null) {
            return false;
        }
        if (board.board[y][x].getColor() != onMove) {
            return false;
        }
        Move toMove = null;
        if (onMove == Color.White) {
            for (Move move : whitePossibleMoves) {
                if (Objects.equals(move.start.x, x) && Objects.equals(move.start.y, y) && Objects.equals(move.end.x, newX) && Objects.equals(move.end.y, newY)) {
                    toMove = move;
                    break;
                }
            }
        } else {
            for (Move move : blackPossibleMoves) {
                if (Objects.equals(move.start.x, x) && Objects.equals(move.start.y, y) && Objects.equals(move.end.x, newX) && Objects.equals(move.end.y, newY)) {
                    toMove = move;
                    break;
                }
            }
        }
        if (toMove == null) {
            return false;
        }
        board.board[newY][newX] = board.board[y][x];
        board.board[y][x] = null;
        if (toMove.doTakes) {
            board.board[toMove.captured.y][toMove.captured.x] = null;
            clearPossibleMoves(onMove);
            Vector<Move> vector = board.board[newY][newX].checkCapture(newX, newY, board);
            if (vector.isEmpty()) {
                calculatePossibleMoves(onMove);
                switchOnMove();
                calculatePossibleMoves(onMove);
                checkPromotions();
            } else {
                storeVectorInPossibleMoves(vector, onMove);
            }
        } else {
            calculatePossibleMoves(onMove);
            switchOnMove();
            calculatePossibleMoves(onMove);
            checkPromotions();
        }
        checkEndGame();
        return true;
    }

    void switchOnMove() {
        if (onMove == Color.White) {
            onMove = Color.Black;
        } else {
            onMove = Color.White;
        }
    }

    void clearPossibleMoves(Color color) {
        if (color == Color.White) {
            whitePossibleMoves.clear();
        } else {
            blackPossibleMoves.clear();
        }
    }

    void checkPromotions() {
        for (int i = 0; i < board.width; i++) {
            if (board.board[0][i] == null) {
                continue;
            }
            if (board.board[0][i].getColor().equals(Color.White)) {
                board.board[0][i] = new Queen(Color.White);
            }
        }
        for (int i = 0; i < board.width; i++) {
            if (board.board[board.height - 1][i] == null) {
                continue;
            }
            if (board.board[board.height - 1][i].getColor().equals(Color.Black)) {
                board.board[board.height - 1][i] = new Queen(Color.Black);
            }
        }
    }

    void storeVectorInPossibleMoves(Vector<Move> vector, Color color) {
        for (Move move : vector) {
            if (color == Color.White) {
                whitePossibleMoves.add(move);
            } else {
                blackPossibleMoves.add(move);
            }
        }
    }

    void checkEndGame(){
        if(blackPossibleMoves.isEmpty()) {
            gameFinished = true;
            winner = Color.White;
        }else if(whitePossibleMoves.isEmpty()) {
            gameFinished = true;
            winner = Color.Black;
        }
    }

    public boolean canPositionMove(Integer x, Integer y){
        if(board.board[y][x]==null){
            return false;
        }
        if(board.board[y][x].getColor() != onMove){
            return false;
        }
        if(board.board[y][x].getColor() == Color.White){
            for(Move move:whitePossibleMoves){
                if(Objects.equals(move.start.x, x) && Objects.equals(move.start.y, y)){
                    return true;
                }
            }
        }else {
            for(Move move:blackPossibleMoves){
                if(Objects.equals(move.start.x, x) && Objects.equals(move.start.y, y)){
                    return true;
                }
            }
        }
        return false;
    }

    public Board getBoard() {
        return board;
    }

    public boolean getGameFinished(){
        return gameFinished;
    }

    public Color getOnMove(){
        return onMove;
    }

    public Color getWinner(){
        return winner;
    }

    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }

    public void setWinner(Color winner) {
        this.winner = winner;
    }

    public ArrayList<Move> getWhitePossibleMoves() {
        return whitePossibleMoves;
    }

    public ArrayList<Move> getBlackPossibleMoves() {
        return blackPossibleMoves;
    }
}
