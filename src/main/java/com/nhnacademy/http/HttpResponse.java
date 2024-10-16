package com.nhnacademy.http;

import java.net.Socket;

public class HttpResponse {
    private int statusCode;
    private String message;

    Socket client;

    public HttpResponse(Socket client) {
        this.client = client;

    }

}
