package com.spotflock.intellihub.examples;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.spotflock.intellihub.IntellihubClient;

import java.io.IOException;

public class Regression {
    public static void main(String[] args) {

        IntellihubClient c = new IntellihubClient("xxx");
        String trainData = "";
        String testData = "";
        JSONObject response = null;
        Integer jobId = 0;

        try {

            response = c.store("csv/housing_train.csv");
            trainData = response.get("fileUrl").toString();
            System.out.println(trainData);

            response = c.store("csv/housing_test.csv");
            testData = response.get("fileUrl").toString();
            System.out.println(testData);


            JSONArray features = new JSONArray();
            features.put("LotShape");
            features.put("Street");
            response = c.train("regression", "LinearRegression", trainData, "SalePrice", features, new JSONObject());
            System.out.println(response.toString());

            jobId = (Integer) ((JSONObject) response.get("data")).get("jobId");
            System.out.println(jobId);
            response = c.jobStatus(jobId);
            System.out.println(response.toString());


            response = c.jobOutput(jobId);
            System.out.println(response.toString());
            String modelUrl = null;
            if(!org.json.JSONObject.NULL.equals(response.get("output")) && response.get("output").getClass()!=String.class)
            	modelUrl = (String) ((JSONObject) response.get("output")).get("modelUrl");
            response = c.predict("regression", testData, modelUrl, new JSONObject());
            System.out.println(response.toString());

            jobId = (Integer) ((JSONObject) response.get("data")).get("jobId");

            response = c.jobStatus(jobId);
            System.out.println(response.toString());

            response = c.jobOutput(jobId);
            System.out.println(response.toString());
            String predFileUrl = null;
            if(!org.json.JSONObject.NULL.equals(response.get("output")) && response.get("output").getClass()!=String.class)
            	predFileUrl = (String) ((JSONObject) response.get("output")).get("predFileUrl");
            String predictions = c.download(predFileUrl);
            System.out.println(predictions);


        } catch (IOException | JSONException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
