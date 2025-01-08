package com.mammba.APIManager.Model;

public class Endpoints {
    private int id;
    private String apiId;
    private String name;
    private String url;
    private String method;

    public Endpoints( String id,String apiId,  String name, String url, String method) {
        this.apiId = apiId;
        this.id = Integer.parseInt(id);
        this.name = name;
        this.url = url;
        this.method = method;
    }
    public void AddEndpoint( String id,String apiId,  String name, String url, String method) {
        this.apiId = apiId;
        this.id = Integer.parseInt(id);
        this.name = name;
        this.url = url;
        this.method = method;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApiId() {
        return apiId;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
