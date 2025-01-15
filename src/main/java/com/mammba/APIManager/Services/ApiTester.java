package com.mammba.APIManager.Services;


import com.mammba.APIManager.Model.Response;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiTester {


    public Response ApiTest(String url, String endpoint, String jsonBody, String method) {

        if (url == null || endpoint == null) {
            throw new IllegalArgumentException("URL, endpoint, and email must not be null.");
        }

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + endpoint))
                .header("Content-Type", "application/json")
                .method(method, HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        try {
            HttpResponse<String> response = HttpClient.newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());

            int statusCode = response.statusCode();
            String responseBody = response.body();

            return new Response(statusCode, responseBody);

        } catch (IOException | InterruptedException e) {
            return new Response(500, "Error: " + e.getMessage()); // 500 for internal error
        }
    }
    public Response ApiTestSecured(String url, String key, String endpoint, String jsonBody, String method) {

        if (url == null || endpoint == null) {
            throw new IllegalArgumentException("URL, endpoint, and email must not be null.");
        }

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + endpoint))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer "+ key)
                .method(method, HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        try {
            HttpResponse<String> response = HttpClient.newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());

            int statusCode = response.statusCode();
            String responseBody = response.body();

            return new Response(statusCode, responseBody);

        } catch (IOException | InterruptedException e) {
            return new Response(500, "Error: " + e.getMessage()); // 500 for internal error
        }
    }
}