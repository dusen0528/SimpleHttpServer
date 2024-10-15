package com.nhnacademy.service;

import java.util.ArrayList;
import java.util.Set;

import org.reflections.Reflections;

public class ServiceFactory {
    private static final ArrayList<Service> serviceList = new ArrayList<>();

    static {
        Reflections reflections = new Reflections("com.nhnacademy.service");
        Set<Class<? extends Service>> classes = reflections.getSubTypesOf(Service.class);

        for (Class<? extends Service> clazz : classes) {
            try {
                Service service = clazz.getDeclaredConstructor().newInstance();
                serviceList.add(service);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static Service getService(String url) {
        for (Service service : serviceList) {
            if (url.contains(service.getClass().toString().toLowerCase().replace("service", ""))) {
                return service;
            }
        }
        return null;
    }
}