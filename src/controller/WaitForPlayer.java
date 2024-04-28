package controller;

import gui.WaitingForPlayer;
import logic.Color;

import javax.swing.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ThreadLocalRandom;

public class WaitForPlayer {
    public WaitForPlayer(JFrame frame) {
        WaitingForPlayer waitingForPlayer = new WaitingForPlayer();
        frame.setContentPane(waitingForPlayer.getMainPanel());

        new Thread(() -> {
            Socket socket;
            ServerSocket serverSocket;
            while (true){
                int randomPort = ThreadLocalRandom.current().nextInt(9000, 10000);
                try {
                    serverSocket = new ServerSocket(randomPort);
                    waitingForPlayer.getPortLabel().setText("Port: "+randomPort);
                    socket = serverSocket.accept();
                    new PlayOnLAN(frame, socket, Color.White);
                    break;
                } catch (Exception ignored) {

                }
            }
        }).start();

        frame.revalidate();
    }
}
