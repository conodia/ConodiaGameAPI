package fr.pandaguerrier.conodiagameapi.websocket.entities;

import fr.pandaguerrier.conodiagameapi.websocket.enums.EventEnum;
import org.json.simple.JSONObject;

public class WebsocketResponse {
    public final EventEnum event;
    public final JSONObject data;

    public WebsocketResponse(EventEnum event, JSONObject data) {
        this.event = event;
        this.data = data;
    }
}
