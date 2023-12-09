package fr.pandaguerrier.conodiagameapi.websocket.enums;

public enum OpCode {
    AUTH(0),
    HEARTBEAT(1),
    HEARTBEAT_ACK(2),
    VERIFIED_USER(3);

    public int value;

    OpCode(int value) {
        this.value = value;
    }
}
