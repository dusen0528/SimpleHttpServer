package com.nhnacademy.http;

import java.net.*;
import java.io.BufferedReader;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest {

    HttpMethod method;
    Map<String, String> headers;
    String url;

    public HttpRequest(Socket client) {
        headers = new HashMap<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                if (isFirstLine(str)) {
                    method = HttpMethod.valueOf(str.split(" ")[0]);
                    if (str.split(" ")[1].split("?").length >= 2) {
                        url = str.split(" ")[1].split("?")[0];
                        // & 파싱
                        String[] query = str.split(" ")[1].split("?")[1].split("&");
                        for (String param : query) {
                            headers.put(param.split("=")[0], param.split("=")[1]);
                        }

                    } else {
                        url = str.split(" ")[1];
                    }
                } else {
                    headers.put(str.split(": ")[0], str.split(": ")[1]);

                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public String getUrl() {
        return url;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public String getHeadersKey(String key) {
        return headers.get(key);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isFirstLine(String line) {
        return (line.contains("GET") || line.contains("POST"));
    }
}
