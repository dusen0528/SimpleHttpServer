package com.nhnacademy;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.nhnacademy.http.HttpRequest;

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
            while (true) {
                client = serverSocket.accept();
                // httpRequest 를 통해서 불러온다
                HttpRequest request = new HttpRequest(client);
                request.parser();

                // 응답 해줘야하지
                // HttpResponse response = new HttpResponse(client);
                // method에 따라 Service doGet doPost 해줌
                // resopnse.send(); 를 하면 저 응답 섹션이 클라이언트에게 전송됨

            }
        } catch (Exception e) {
        }
    }

}
