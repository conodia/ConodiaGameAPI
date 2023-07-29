package fr.pandaguerrier.conodia.websocket;

import fr.pandaguerrier.conodia.ConodiaLinks;

public class HeartBeat {
    final int interval;
    final WebsocketManager websocketManager;

    public HeartBeat(int interval, WebsocketManager websocketManager) {
        this.interval = interval;
        this.websocketManager = websocketManager;
    }

    public void start() {
        ConodiaLinks.getInstance().getServer().getScheduler().runTaskTimer(ConodiaLinks.getInstance(), () -> websocketManager.send(OpCode.HEARTBEAT, null), interval, interval);
    }
}
