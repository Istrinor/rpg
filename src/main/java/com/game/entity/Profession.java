package com.game.entity;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Profession {
    WARRIOR("WARRIOR"),
    ROGUE("ROGUE"),
    SORCERER("SORCERER"),
    CLERIC("CLERIC"),
    PALADIN("PALADIN"),
    NAZGUL("NAZGUL"),
    WARLOCK("WARLOCK"),
    DRUID("DRUID");

    private static final Map<String, Profession> NAME_MAP = Stream.of(values())
            .collect(Collectors.toMap(Profession::getName, Function.identity()));

    private final String name;

    Profession(String name) {
        this.name = name;
    }

    public static Profession getByName(String name) {
        return NAME_MAP.get(name);
    }

    public String getName() {
        return name;
    }
}
