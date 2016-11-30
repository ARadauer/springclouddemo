package com.example;

import org.springframework.stereotype.Component;

/**
 * Created by Andreas on 30.11.2016.
 */
@Component
public class Client2Fallback implements Client2 {

    @Override
    public String getMessage() {
        return "client 2 ist tod";
    }
}