package edu.uncg;
/*
Last updated: 02/15/2021
A simple prototype class that connects to an API of your choice, returns information, parses the most relevant
piece of that information, and prints it to the system output.
Author: Hyoungjin Choi
 */

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class COVID19API {

    private static final String API_URL = "https://covid-19-data.p.rapidapi.com";
    private static final String API_TOTALS_PATH = "/totals";
    private static final String JSON_FORMAT = "?format=json";
    private static final String API_KEY_HEADER = "x-rapidapi-key";
    private static final String API_KEY = "e3ad8e480emsh1077ec2e2234c54p1acb91jsn8311c662d23d";
    private static final String API_HOST_HEADER = "x-rapidapi-host";
    private static final String HOST = "covid-19-data.p.rapidapi.com";
    private static final String REQUEST_GET = "GET";

    public static void getLatestCOVIDTotals() {
        try {
            // Send the request to the API.
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL + API_TOTALS_PATH + JSON_FORMAT))
                    .header(API_KEY_HEADER, API_KEY)
                    .header(API_HOST_HEADER, HOST)
                    .method(REQUEST_GET, HttpRequest.BodyPublishers.noBody())
                    .build();
            // Save the response and print.
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());

            // Parse that object into a usable Java JSON object
            JSONArray objArr = new JSONArray(response.body());
            JSONObject obj = new JSONObject(objArr.getString(0));
            // Print out the number of confirmed cases.
            String confirmedCases = obj.getString("confirmed");
            System.out.printf("Confirmed cases: " + "%,d\n", Integer.parseInt(confirmedCases));
            // Print out the number of recovered cases.
            String recoveredCases = obj.getString("recovered");
            System.out.printf("Recovered cases: " +  "%,d\n", Integer.parseInt(recoveredCases));
            // Print out the number of critical cases.
            String criticalCases = obj.getString("critical");
            System.out.printf("Critical cases: " + "%,d\n", Integer.parseInt(criticalCases));
            // Print out the number of deaths.
            String deaths = obj.getString("deaths");
            System.out.printf("Deaths: " + "%,d\n",Integer.parseInt(deaths));
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}
