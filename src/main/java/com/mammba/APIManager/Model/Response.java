package com.mammba.APIManager.Model;

public class Response {
    private final int statusCode;
    private final Object body;

    public Response(int statusCode, Object body) {
        this.statusCode = statusCode;
        this.body = body;
    }

    public int getStatusCode() {
        return statusCode;
    }


    public String getBody() {

        return (String) body;
    }

    @Override
    public String toString() {
        return "Status: " + statusCode + ", Body: " + body;
    }
}