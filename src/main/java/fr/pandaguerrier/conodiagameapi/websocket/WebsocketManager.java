package fr.pandaguerrier.conodiagameapi.websocket;

import fr.pandaguerrier.conodiagameapi.websocket.client.WebsocketConodiaClient;
import fr.pandaguerrier.conodiagameapi.websocket.enums.EventEnum;
import fr.pandaguerrier.conodiagameapi.websocket.enums.OpCode;
import org.json.simple.JSONObject;

public class WebsocketManager {
    final WebsocketConodiaClient ws;
    final WebsocketDispatcher dispatcher = new WebsocketDispatcher(this);

    public WebsocketManager(WebsocketConodiaClient ws) {
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

    public void dispacth(EventEnum event, JSONObject payload) {
        dispatcher.dispatch(event, payload);
    }

    public WebsocketConodiaClient getWs() {
        return ws;
    }

    public WebsocketDispatcher getDispatcher() {
        return dispatcher;
    }
}
