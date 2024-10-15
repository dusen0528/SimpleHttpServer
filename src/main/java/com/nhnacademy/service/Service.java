package com.nhnacademy.service;

import com.nhnacademy.http.HttpRequest;
import com.nhnacademy.http.HttpResponse;

public interface Service {

    public void doGet(HttpRequest httpRequest, HttpResponse httpResponse);

    public void doPost(HttpRequest httpRequest, HttpResponse httpResponse);

}