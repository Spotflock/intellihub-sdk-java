package com.spotflock.intellihub.examples;

import org.json.JSONObject;

import com.spotflock.intellihub.IntellihubClient;

import java.io.IOException;

public class NLPExample {
    public static void main(String[] args) {

        IntellihubClient c = new IntellihubClient("xxx");
        JSONObject r = null;
        try {
            r = c.nerTagger("Apple is a Big Organization");
            System.out.print(r);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
