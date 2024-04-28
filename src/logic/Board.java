package logic;

public class Board {


    Piece[][] board;
    Integer width;
    Integer height;

    public Board(Integer width, Integer height) {
        this.width = width;
        this.height = height;
        this.board = new Piece[height][width];
        for (int k = 0; k < 2; k++) {
            for (int i = ((height / 2) + 1) * k; i < (height / 2) - 1 + ((height / 2) + 1) * k; i++) {
                for (int j = 0; j < width; j++) {
                    if ((i + j) % 2 == 1) {
                        board[i][j] = new Pawn(Color.values()[k]);
                    }
                }
            }
        }
    }

    public Piece[][] getBoard() {
        return board;
    }
}
