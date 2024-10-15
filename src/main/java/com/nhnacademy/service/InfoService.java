package com.nhnacademy.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

import com.nhnacademy.http.HttpRequest;
import com.nhnacademy.http.HttpResponse;
import com.utils.ServiceUtils;

public class InfoService implements Service {

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
        String responseBody = ServiceUtils.setBody("/404.html");

        StringBuilder responseHeader = new StringBuilder();
        responseHeader.append(String.format("HTTP/1.0 %d %s%s", 405,
                "Method Not Allowed", CRLF));
        responseHeader.append(String.format("Server: HTTP server/0.1%s", CRLF));
        responseHeader.append(String.format("Content-type: text/html; charset=%s%s", "utf-8", CRLF));
        responseHeader.append(String.format("Connection: Closed%s", CRLF));
        responseHeader
                .append(String.format("Content-Length:%d %s%s", responseBody.length(), System.lineSeparator(), CRLF));

        httpResponse.send(responseHeader.toString(), responseBody.toString());
    }

}
