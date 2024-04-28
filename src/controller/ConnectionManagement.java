package controller;

import gui.Connection;
import logic.Color;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

public class ConnectionManagement {
    public ConnectionManagement(JFrame frame){
        Connection connectionView = new Connection();
        frame.setContentPane(connectionView.getMainPanel());

        connectionView.getCreateGameButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new WaitForPlayer(frame);
            }
        });

        connectionView.getJoinGameButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String IPAddress = connectionView.getIPTextField().getText();
                if(IPAddress.matches("\\b(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?):\\d{1,5}$\\b")){
                    try {
                        Socket socket = new Socket(IPAddress.split(":")[0], Integer.parseInt(IPAddress.split(":")[1]));
                        new PlayOnLAN(frame,socket, Color.Black);
                    } catch (Exception ex) {
                        connectionView.getError().setText("Game don't exist!");
                    }
                }else{
                    connectionView.getError().setText("Wrong address!");
                }
            }
        });

        frame.revalidate();
    }
}
