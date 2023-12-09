package fr.pandaguerrier.conodiagameapi.websocket.contracts;

import fr.pandaguerrier.conodiagameapi.websocket.entities.WebsocketResponse;
import fr.pandaguerrier.conodiagameapi.websocket.enums.EventEnum;

public abstract class WebsocketEvent {
    public abstract void handle(WebsocketResponse response);
    public abstract EventEnum getEvent();
}
