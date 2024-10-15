package com.nhnacademy.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Objects;

import com.nhnacademy.http.HttpRequest;
import com.nhnacademy.http.HttpResponse;
import com.utils.ServiceUtils;

public class RegisterService implements Service {

    final static String CRLF = "\r\n";

    @Override
    public void doGet(HttpRequest httpRequest, HttpResponse httpResponse) {
        String responseBody = ServiceUtils.setBody(httpRequest.getUrl());

        StringBuilder responseHeader = new StringBuilder();
        responseHeader.append(String.format("HTTP/1.0 %d %s%s", 200,
                "OK", CRLF));
        responseHeader.append(String.format("Server: HTTP server/0.1%s", CRLF));
        responseHeader.append(String.format("Content-type: text/html; charset=%s%s", "utf-8", CRLF));
        responseHeader.append(String.format("Connection: Closed%s", CRLF));
        responseHeader
                .append(String.format("Content-Length:%d %s%s", responseBody.length(), System.lineSeparator(), CRLF));

        httpResponse.send(responseHeader.toString(), responseBody);

    }

    @Override
    public void doPost(HttpRequest httpRequest, HttpResponse httpResponse) {
        String responseBody = ServiceUtils.setBody(httpRequest.getUrl());
        try {
            requestBody = ResponseUtils.tryGetBodyFromFile("/index.html");
            StringBuilder sending = new StringBuilder();
            sending.append("HTTP/1.1 301 Moved Permanently" + System.lineSeparator());
            sending.append(
                    "Location: http://localhost:8080/index.html?userId=" + httpRequest.getAttribute("userId")
                            + System.lineSeparator());
            sending.append("Content-Length: 0" + System.lineSeparator());
            sending.append("Connection: Closed" + System.lineSeparator());
            String send = sending.toString();

            // PrintWriter를 try-with-resources로 사용하여 자동으로 닫히도록 합니다.
            try (PrintWriter printWriter = httpResponse.getWriter()) {
                printWriter.write(send);
                printWriter.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
