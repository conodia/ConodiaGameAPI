package fr.pandaguerrier.conodiagameapi.api;

import fr.pandaguerrier.conodia.api.adapters.DestroyAdapter;
import fr.pandaguerrier.conodia.api.adapters.GetAdapter;
import fr.pandaguerrier.conodia.api.adapters.PostAdapter;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

public class ApiManager {
    final private String baseUrl;
    final private Map<String, String> headers =  new HashMap<>();

    public ApiManager(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public void init() {
        this.addHeader("Content-Type", "application/json");
        this.addHeader("Accept", "application/json");
    }

    public JSONObject get(String url, JSONObject body) {
        GetAdapter getAdapter = new GetAdapter(this.baseUrl);
        getAdapter.setHeaders(this.getHeaders());
        getAdapter.setBody(body);

       return getAdapter.send(url);
    }

    public JSONObject post(String url, JSONObject body) {
        PostAdapter postAdapter = new PostAdapter(this.baseUrl);
        postAdapter.setHeaders(this.getHeaders());
        postAdapter.setBody(body);

        return postAdapter.send(url);
    }

    public JSONObject put(String url, JSONObject body) {
        PostAdapter putAdapter = new PostAdapter(this.baseUrl);
        putAdapter.setHeaders(this.getHeaders());
        putAdapter.setBody(body);

        return putAdapter.send(url);
    }

    public JSONObject destroy(String url, JSONObject body) {
        DestroyAdapter destroyAdapter = new DestroyAdapter(this.baseUrl);
        destroyAdapter.setHeaders(this.getHeaders());
        destroyAdapter.setBody(body);

        return destroyAdapter.send(url);
    }


    void addHeader(String key, String value) {
        headers.putIfAbsent(key, value);
    }

    public String getBaseUrl() {
        return this.baseUrl;
    }
    public Map<String, String> getHeaders() {
        return headers;
    }
}
