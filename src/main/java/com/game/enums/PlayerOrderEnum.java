package com.game.enums;

public enum PlayerOrderEnum {
    ID("id"), // default
    NAME("name"),
    EXPERIENCE("experience"),
    BIRTHDAY("birthday"),
    LEVEL("level");

    private static final PlayerOrderEnum DEFAULT_PLAYER_ORDER = ID;

    private final String fieldName;

    PlayerOrderEnum(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public static String getFieldNameOrDefault(PlayerOrderEnum playerOrder) {
        PlayerOrderEnum resultEnum;
        if (playerOrder == null) {
            resultEnum = DEFAULT_PLAYER_ORDER;
        } else {
            resultEnum = playerOrder;
        }
        return resultEnum.fieldName;
    }
}