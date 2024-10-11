/*
 * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 * + Copyright 2024. NHN Academy Corp. All rights reserved.
 * + * While every precaution has been taken in the preparation of this resource,  assumes no
 * + responsibility for errors or omissions, or for damages resulting from the use of the information
 * + contained herein
 * + No part of this resource may be reproduced, stored in a retrieval system, or transmitted, in any
 * + form or by any means, electronic, mechanical, photocopying, recording, or otherwise, without the
 * + prior written permission.
 * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 */

package com.nhnacademy.http;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleHttpServer {

    private final int port;
    private static final int DEFAULT_PORT = 8080;
    private final ServerSocket serverSocket;

    private static final String CRLF = "\r\n";

    public SimpleHttpServer() {
        this(DEFAULT_PORT);
    }

    public SimpleHttpServer(int port) {
        if (port <= 0)
            throw new IllegalArgumentException();
        this.port = port;

        try {
            serverSocket = new ServerSocket(this.port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void start() throws IOException {

        while (true) {

            try (
                    Socket client = serverSocket.accept();

                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    BufferedWriter bufferedWriter = new BufferedWriter(
                            new OutputStreamWriter(client.getOutputStream()));) {

                StringBuilder requestBuilder = new StringBuilder();
                log.debug("------HTTP-REQUEST_start()");
                while (true) {
                    String line = bufferedReader.readLine();
                    requestBuilder.append(line);
                    log.debug("{}", line);

                    if (line == null || line.length() == 0)
                        break;
                }
                log.debug("------HTTP-REQUEST_end()");

                /*
                 * <html>
                 * <body>
                 * <h1>hello hava</h1>
                 * </body>
                 * </html>
                 */

                StringBuilder responseBody = new StringBuilder();
                responseBody.append("<html>");
                responseBody.append("<body");
                responseBody.append("<h1>hello java</h1>");
                responseBody.append("</body");
                responseBody.append("</html>");

                StringBuilder responseHeader = new StringBuilder();

                responseHeader.append("HTTP/1.0 200 OK");
                responseHeader.append(String.format("Server: HTTP server/0.1%s", CRLF));

                responseHeader.append(String.format("Content-type: text/html; charset=%s%s", "UTF-8", CRLF));

                responseHeader.append(String.format("Connection: Closed%s", CRLF));

                responseHeader.append(
                        String.format("Content-Length:%d %s%s", responseBody.toString().getBytes().length, CRLF, CRLF));

                bufferedWriter.write(responseHeader.toString());

                bufferedWriter.write(responseBody.toString());

                bufferedWriter.flush();

                log.debug("header:{}", responseHeader);
                log.debug("body:{}", responseBody);

            } catch (IOException e) {
                log.error("socket error : {}", e);
            }
        } // end while
    }// end start
}
