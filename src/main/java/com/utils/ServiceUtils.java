package com.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

import com.nhnacademy.service.IndexService;

public class ServiceUtils {
    public static String setBody(String url) {
        StringBuilder responseBody = new StringBuilder();
        try (InputStream inputStream = ServiceUtils.class.getResourceAsStream(url);
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"))) {
            while (true) {
                String line = reader.readLine();
                if (Objects.isNull(line)) {
                    break;
                }
                responseBody.append(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return responseBody.toString();
    }

}
