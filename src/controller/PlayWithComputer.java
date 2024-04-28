package controller;

import gui.EndGamePopUp;
import gui.GameView;
import logic.*;
import logic.Color;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.Duration;
import java.util.Collections;

import static java.lang.Math.floor;

public class PlayWithComputer {
    public PlayWithComputer(JFrame frame, Color playerSide) {
        Game checkers = new Game(8, 8);
        GameView gameView = new GameView();
        frame.setContentPane(gameView.getMainPanel());
        gameView.drawBoard(checkers.getBoard(), playerSide);
        frame.revalidate();
        final Component[] selected = {null};
        final Position[] copy = {null};

        Component boardStart = null;
        for (Component component : gameView.getBoardPanel().getComponents()) {
            if (component.getClass() == JPanel.class) {
                boardStart = component;
                break;
            }
        }
        Component finalBoardStart = boardStart;

        final Duration[] whiteTime = {Duration.ofSeconds(300)};
        final Duration[] blackTime = {Duration.ofSeconds(300)};
        gameView.getTimerWhite().setText(durationToString(whiteTime[0]));
        gameView.getTimerBlack().setText(durationToString(blackTime[0]));
        final Timer timer = new Timer(1000, null);
        timer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkers.getOnMove() == Color.White) {
                    whiteTime[0] = whiteTime[0].minusSeconds(1);
                    gameView.getTimerWhite().setText(durationToString(whiteTime[0]));
                } else {
                    blackTime[0] = blackTime[0].minusSeconds(1);
                    gameView.getTimerBlack().setText(durationToString(blackTime[0]));
                }

                if (whiteTime[0].isZero()) {
                    checkers.setGameFinished(true);
                    checkers.setWinner(Color.Black);
                    timer.stop();
                } else if (blackTime[0].isZero()) {
                    checkers.setGameFinished(true);
                    checkers.setWinner(Color.White);
                    timer.stop();
                }
            }
        });
        timer.start();

        final Timer computerTimer = new Timer(1000, null);
        computerTimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!checkers.getGameFinished() && checkers.getOnMove() != playerSide) {
                    Move toMove;
                    if (playerSide == Color.Black) {
                        Collections.shuffle(checkers.getWhitePossibleMoves());
                        toMove = checkers.getWhitePossibleMoves().getFirst();
                    } else {
                        Collections.shuffle(checkers.getBlackPossibleMoves());
                        toMove = checkers.getBlackPossibleMoves().getFirst();
                    }
                    checkers.move(toMove.start.x, toMove.start.y, toMove.end.x, toMove.end.y);
                    gameView.drawBoard(checkers.getBoard(), playerSide);
                }
            }
        });
        computerTimer.start();

        final Timer endTimer = new Timer(100, null);
        endTimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkers.getGameFinished()) {
                    EndGamePopUp endGamePopUp = new EndGamePopUp(frame, checkers.getWinner());
                    endGamePopUp.setLocationRelativeTo(gameView.getMainPanel());
                    endGamePopUp.getMenuButton().addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            new Menu(frame);
                            endGamePopUp.setVisible(false);
                        }
                    });

                    endGamePopUp.getPlayAgainButton().addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            new ChooseSideVsComputer(frame);
                            endGamePopUp.setVisible(false);
                        }
                    });
                    endTimer.stop();
                    timer.stop();
                    computerTimer.stop();
                    endGamePopUp.setVisible(true);
                }
            }
        });
        endTimer.start();

        gameView.getBoardPanel().addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (e.getX() - finalBoardStart.getX() - 32 > 0 && e.getY() - finalBoardStart.getY() - 32 > 0 && selected[0] != null) {
                    Position indexes = positionToIndex(e.getX() - finalBoardStart.getX(), e.getY() - finalBoardStart.getY(), playerSide);
                    if (indexes.x <= 7 && indexes.y <= 7) {
                        selected[0].setLocation(e.getX() - 32, e.getY() - 32);
                    }
                }
            }
        });


        gameView.getBoardPanel().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getX() - finalBoardStart.getX() > 0 && e.getY() - finalBoardStart.getY() > 0 && gameView.getBoardPanel().getComponentAt(e.getX(), e.getY()).getClass() == JLabel.class && !checkers.getGameFinished()) {
                    copy[0] = positionToIndex(e.getX() - finalBoardStart.getX(), e.getY() - finalBoardStart.getY(), playerSide);
                    if (checkers.canPositionMove(copy[0].x, copy[0].y) && checkers.getBoard().getBoard()[copy[0].y][copy[0].x].getColor() == playerSide) {
                        selected[0] = gameView.getBoardPanel().getComponentAt(e.getX(), e.getY());
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (selected[0] != null) {
                    Position newPos = positionToIndex(e.getX() - finalBoardStart.getX(), e.getY() - finalBoardStart.getY(), playerSide);
                    checkers.move(copy[0].x, copy[0].y, newPos.x, newPos.y);
                    gameView.drawBoard(checkers.getBoard(), playerSide);
                    selected[0] = null;
                }
            }
        });
    }

    Position positionToIndex(int x, int y, Color color) {
        int[] blackIndexes = {7, 6, 5, 4, 3, 2, 1, 0};

        if (color == logic.Color.White) {
            return new Position((int) floor(x / 64.0), (int) floor(y / 64.0));
        } else {
            return new Position(blackIndexes[(int) floor(x / 64.0)], blackIndexes[(int) floor(y / 64.0)]);
        }
    }

    String durationToString(Duration duration) {
        long sec = (duration.toSeconds()) - (duration.toMinutes() * 60);
        String sep = ":";
        if (sec < 10) {
            sep += "0";
        }
        return duration.toMinutes() + sep + sec;
    }
}
