package logic;

public class Move {
    public Position start;
    public Position end;
    public boolean doTakes;
    public Position captured;

    public Move(Position start, Position end, boolean doTakes, Position captured) {
        this.start = start;
        this.end = end;
        this.doTakes = doTakes;
        this.captured = captured;
    }
}