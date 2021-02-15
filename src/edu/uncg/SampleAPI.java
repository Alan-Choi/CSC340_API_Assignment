package edu.uncg;

import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class SampleAPI {

    private static final String API_URL = "https://covid-19-data.p.rapidapi.com";
    private static final String API_TOTALS_PATH = "/totals";
    private static final String API_KEY_HEADER = "x-rapidapi-key";
    private static final String API_KEY = "e3ad8e480emsh1077ec2e2234c54p1acb91jsn8311c662d23d";
    private static final String API_HOST_HEADER = "x-rapidapi-host";
    private static final String HOST = "covid-19-data.p.rapidapi.com";
    private static final String REQUEST_GET = "GET";

    public static void getLatestCOVIDTotals() {
        try {
            // Send the request to the API.
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL + API_TOTALS_PATH))
                    .header(API_KEY_HEADER, API_KEY)
                    .header(API_HOST_HEADER, HOST)
                    .method(REQUEST_GET, HttpRequest.BodyPublishers.noBody())
                    .build();
            // Save the response and print.
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());

            // Parse that object into a usable Java JSON object, trimming the response to remove '[' and ']' at
            // the beginning and the end.
            JSONObject obj = new JSONObject(response.body().substring(1, response.body().length() - 1));

            // Print out the number of confirmed cases.
            String confirmedCases = obj.getString("confirmed");
            System.out.println("Confirmed cases: " + confirmedCases);
            // Print out the number of recovered cases.
            String recoveredCases = obj.getString("recovered");
            System.out.println("Recovered cases: " + recoveredCases);
            // Print out the number of critical cases.
            String criticalCases = obj.getString("critical");
            System.out.println("Critical cases: " + criticalCases);
            // Print out the number of deaths.
            String deaths = obj.getString("deaths");
            System.out.println("Deaths: " + deaths);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}
