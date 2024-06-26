package fr.pandaguerrier.conodiagameapi.api.adapters;

import fr.pandaguerrier.conodia.api.contracts.Adapters;
import fr.pandaguerrier.conodia.utils.Parser;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class GetAdapter extends Adapters {
    public GetAdapter(String baseUrl) {
        super(baseUrl);
    }

    @Override
    public JSONObject send(String url) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest.Builder prepareRequest = HttpRequest.newBuilder()
                .uri(URI.create(super.baseUrl + "" + url))
                .GET();

        for (Map.Entry<String, String> entry : super.getHeaders().entrySet()) {
            prepareRequest.setHeader(entry.getKey(), entry.getValue());
        }

        HttpRequest request = prepareRequest.build();

        HttpResponse<String> response;
        try {
            response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(response.body());
        return Parser.parse(response.body());
    }
}
