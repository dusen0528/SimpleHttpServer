package com.nhnacademy;

import java.io.IOException;
import java.net.*;

import com.nhnacademy.http.HttpMethod;
import com.nhnacademy.http.HttpRequest;
import com.nhnacademy.http.HttpResponse;
import com.nhnacademy.service.IndexService;
import com.nhnacademy.service.Service;
import com.nhnacademy.service.ServiceFactory;

public class Server {

    private int port;
    public static final int DEFAULT_PORT = 8080;
    Socket client;
    ServerSocket server;

    public Server() {
        this(DEFAULT_PORT);
    }

    public Server(int port) {
        this.port = port;
        try {
            server = new ServerSocket(port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void start() {

        while (true) {
            try {
                client = server.accept();
                HttpRequest request = new HttpRequest(client);
                HttpResponse response = new HttpResponse(client);
                Service service = ServiceFactory.getService(request.getUrl());

                if (request.getMethod() == HttpMethod.GET) {
                    service.doGet(request, response);
                } else if (request.getMethod() == HttpMethod.POST) {
                    service.doPost();
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

    }

}
