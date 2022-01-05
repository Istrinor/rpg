package com.game.controller;

public enum PlayerOrder {
    ID("id"), // default
    NAME("name"),
    EXPERIENCE("experience"),
    BIRTHDAY("birthday"),
    LEVEL("level");

    private static final PlayerOrder DEFAULT_PLAYER_ORDER = ID;

    private final String fieldName;

    PlayerOrder(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public static String getFieldNameOrDefault(PlayerOrder playerOrder) {
        PlayerOrder resultEnum;
        if (playerOrder == null) {
            resultEnum = DEFAULT_PLAYER_ORDER;
        } else {
            resultEnum = playerOrder;
        }
        return resultEnum.fieldName;
    }
}