package com.spotflock.intellihub.examples;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.spotflock.intellihub.IntellihubClient;

import java.io.IOException;

public class Clustering {

    public static void main(String[] args) {

        IntellihubClient c = new IntellihubClient("xxx");
        String clusterData = "";
        String testData = "";
        JSONObject response = null;
        Integer jobId = 0;

        try {

            response = c.store("csv/airoplane_data.csv");
            clusterData = response.get("fileUrl").toString();
            System.out.println(clusterData);


            JSONArray features = new JSONArray();
            features.put("Activity Period");
            features.put("Operating Airline");
            response = c.cluster("clustering", "KMeansClustering", clusterData, features, new JSONObject());
            System.out.println(response.toString());

            jobId = (Integer) ((JSONObject) response.get("data")).get("jobId");

            response = c.jobStatus(jobId);
            System.out.println(response.toString());

            response = c.jobOutput(jobId);
            System.out.println(response.toString());
            String predFileUrl = null;
            if(!org.json.JSONObject.NULL.equals(response.get("output")) && response.get("output").getClass()!=String.class)
            	predFileUrl = (String) ((JSONObject) response.get("output")).get("clusterFileUrl");
            String predictions = c.download(predFileUrl);
            System.out.println(predictions);


        } catch (IOException | JSONException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
