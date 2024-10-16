package com.nhnacademy.http;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleHttpServer {

    Socket client;
    ServerSocket serverSocket;
    private final int port;

    public SimpleHttpServer(int port) {
        this.port = port;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            log.debug("connected fail");
        }
    }

    public void start() {
        try {
            while(true){
                client = serverSocket.accept();

                
            }
        } catch (Exception e) {
        }
    }

}
