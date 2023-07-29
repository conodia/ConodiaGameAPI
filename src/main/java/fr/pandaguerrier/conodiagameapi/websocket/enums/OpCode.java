package fr.pandaguerrier.conodiagameapi.websocket;

public enum OpCode {
    AUTH(0),
    HEARTBEAT(1),
    HEARTBEAT_ACK(2),
    VERIFIED_USER(3);

    final int value;

    OpCode(int value) {
        this.value = value;
    }
}
