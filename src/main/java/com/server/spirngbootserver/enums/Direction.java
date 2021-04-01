package com.server.spirngbootserver.enums;

public enum Direction {
    UP(1),
    DOWN(-1),
    STANDING(0);
    int directionAsInt;

    Direction(int directionAsInt) {
        this.directionAsInt = directionAsInt;
    }

    public int getDirectionAsInt() {
        return directionAsInt;
    }
}