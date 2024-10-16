package com.nhnacademy.http;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest {

    private String method;
    private String url;
    private Map<String, String> headres = new HashMap<>();
    private Map<String, String> body = new HashMap<>();

    Socket client;

    public HttpRequest(Socket client) {
        this.client = client;

    }

    public void parser() {
        try (
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (isFirstLine(line)) { // GET /test.html?fdasf=1&dddd=7889 HTTP/1.1
                    String firstLine = line.split("\\?")[0]; // GET /test.html
                    this.method = firstLine.split(" ")[0]; // GET
                    this.url = firstLine.split(" ")[1]; // /test.html
                    String paramString = line.split(" ")[1].split("\\?")[1]; // fdasf=1&dddd=7889
                    String[] paramList = paramString.split("&"); // // [asdf=1, dddd=7889]
                    for (String data : paramList) {
                        headres.put(data.split("=")[0], data.split("=")[1]); // asdf, 1 -> dddd, 7889
                    }

                } else {
                    if (line.equals(System.lineSeparator())) {
                        if (headres.get("ContentLength") != null
                                && Integer.parseInt(headres.get("ContentLength")) > 0) {
                            line = bufferedReader.readLine();

                            String[] query = line.split("&");
                            for (String obj : query) {
                                body.put(obj.split("=")[0], obj.split("=")[1]);
                            }
                        }
                    } else {
                        headres.put(line.split(": ")[0], line.split(": ")[1]);
                    }

                }
            }
        } catch (Exception e) {
        }
    }

    public boolean isFirstLine(String line) {
        return line.contains("GET") || line.contains("POST");
    }

    public String getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }

    public String getHeaders(String name) {
        return headres.get(name);
    }

    public String getBody(String name) {
        return body.get(name);
    }

}
