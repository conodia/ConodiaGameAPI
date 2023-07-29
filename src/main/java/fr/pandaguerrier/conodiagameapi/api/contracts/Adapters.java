package fr.pandaguerrier.conodiagameapi.api.contracts;

import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;

public abstract class Adapters {
    final private Map<String, String> headers = new HashMap<>();
    final private JSONObject body = new JSONObject();
    protected final String baseUrl;
    public Adapters(String baseUrl) {
        this.baseUrl = baseUrl;
        addHeader("Content-Type", "application/json");
    }

    abstract public JSONObject send(String url);

    public Map<String, String> getHeaders() {
        return headers;
    }

    public JSONObject getBody() {
        return body;
    }

    public void addHeader(String key, String value) {
        headers.putIfAbsent(key, value);
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers.putAll(headers);
    }

    public void addBody(String key, String value) {
        this.body.put(key, value);
    }

    public void setBody(JSONObject body) {
        this.body.putAll(body);
    }
}
