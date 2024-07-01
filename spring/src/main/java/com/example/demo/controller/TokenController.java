package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

@RestController
public class TokenController {
    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;


    @GetMapping("/confirm")
    public String confirmToken(@RequestParam String token) throws IOException {
        establishConnection("localhost", 5000);
        sendMessage("confirm " + token);
        sendMessage("exit");
        socket.close();
        return "Token received: " + token;
    }

    public boolean establishConnection(String address, int port) {
        try {
            socket = new Socket(address, port);
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private void sendMessage(String message) {
        try {
            dataOutputStream.writeUTF(message);
            dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}