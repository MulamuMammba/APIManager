package com.mammba.APIManager.Model;

public class API {
    private String id;
    private String userEmail;
    private String name;
    private String description;
    private String baseUrl;

    public API(String id, String userEmail, String name, String description, String baseUrl) {
        this.id = id;
        this.userEmail = userEmail;
        this.name = name;
        this.description = description;
        this.baseUrl = baseUrl;
    }
    public void addAPI(String id, String userEmail, String name, String description, String baseUrl) {
        this.id = id;
        this.userEmail = userEmail;
        this.name = name;
        this.description = description;
        this.baseUrl = baseUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
}
