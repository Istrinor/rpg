package com.game.enums;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum ProfessionEnum {
    WARRIOR("WARRIOR"),
    ROGUE("ROGUE"),
    SORCERER("SORCERER"),
    CLERIC("CLERIC"),
    PALADIN("PALADIN"),
    NAZGUL("NAZGUL"),
    WARLOCK("WARLOCK"),
    DRUID("DRUID");

    private static final Map<String, ProfessionEnum> NAME_MAP = Stream.of(values())
            .collect(Collectors.toMap(ProfessionEnum::getName, Function.identity()));

    private final String name;

    ProfessionEnum(String name) {
        this.name = name;
    }

    public static ProfessionEnum getByName(String name) {
        return NAME_MAP.get(name);
    }

    public String getName() {
        return name;
    }
}
