package fr.pandaguerrier.conodiagameapi.websocket.enums;

public enum EventEnum {
    READY(0),
    STAFF_JOIN_ACCEPT(5),
    STAFF_JOIN_DENY(6),
    EMERGENCY(9);

    public final int value;
    EventEnum(int value) {
        this.value = value;
    }

    public static EventEnum get(int value) {
        for (EventEnum eventEnum : EventEnum.values()) {
            if (eventEnum.value == value) {
                return eventEnum;
            }
        }
        return null;
    }
}
