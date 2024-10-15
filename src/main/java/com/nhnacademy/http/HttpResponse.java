package com.nhnacademy.http;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;

import com.nhnacademy.service.IndexService;

public class HttpResponse {

    private int statusCode;
    private Map<String, String> headers;
    private String body;
    private PrintWriter printWriter;

    public HttpResponse(Socket client) {
        try {
            printWriter = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void send(String headers, String body) {
        printWriter.write(headers);
        printWriter.write(body);
        printWriter.flush();
    }

}
