package fr.pandaguerrier.conodia.websocket;

import fr.pandaguerrier.conodia.websocket.client.WebsocketRivalsClient;
import org.json.simple.JSONObject;

public class WebsocketManager {
    final WebsocketRivalsClient ws;

    public WebsocketManager(WebsocketRivalsClient ws) {
        this.ws = ws;
    }

    public void send(OpCode code, JSONObject payload) {
        if(!ws.isOpen()) {
            ws.connect();
        }
        // transform payload to json
        JSONObject jsonPayload = new JSONObject();

        jsonPayload.put("code", code.value);
        jsonPayload.put("data", payload);

        ws.send(jsonPayload.toJSONString());
    }
}
