package fr.pandaguerrier.conodiagameapi.websocket;


import fr.pandaguerrier.conodiagameapi.ConodiaGameAPI;
import fr.pandaguerrier.conodiagameapi.websocket.enums.OpCode;

public class HeartBeat {
    final int interval;
    final WebsocketManager websocketManager;

    public HeartBeat(int interval, WebsocketManager websocketManager) {
        this.interval = interval;
        this.websocketManager = websocketManager;
    }

    public void start() {
        ConodiaGameAPI.getInstance().getServer().getScheduler().runTaskTimer(ConodiaGameAPI.getInstance(), () -> websocketManager.send(OpCode.HEARTBEAT, null), interval, interval);
    }
}
