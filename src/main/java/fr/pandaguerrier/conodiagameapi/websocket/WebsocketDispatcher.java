package fr.pandaguerrier.conodiagameapi.websocket;

import fr.pandaguerrier.conodiagameapi.websocket.contracts.WebsocketEvent;
import fr.pandaguerrier.conodiagameapi.websocket.entities.WebsocketResponse;
import fr.pandaguerrier.conodiagameapi.websocket.enums.EventEnum;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class WebsocketDispatcher {
    final WebsocketManager websocketManager;
    final Map<EventEnum, WebsocketEvent> events = new HashMap<>();

    public WebsocketDispatcher(WebsocketManager websocketManager) {
        this.websocketManager = websocketManager;

        // events registers
    }


    public void register(EventEnum event, WebsocketEvent handler) {
        events.putIfAbsent(event, handler);
    }
    public void registerAll(Map<EventEnum, WebsocketEvent> events) {
        this.events.putAll(events);
    }
    public void dispatch(EventEnum event, JSONObject payload) {
        System.out.println("§bDispatching event " + event.name());
        System.out.println(events.values());
        if(events.containsKey(event)) {
            events.get(event).handle(new WebsocketResponse(event, payload));
        }
    }
}
