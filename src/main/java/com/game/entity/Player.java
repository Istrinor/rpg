package com.game.entity;

import java.util.Date;

public class Player {
    Long id;
    String name;
    String title;
    Race race;
    Profession profession;
    Integer experience;
    Integer level;
    Integer untilNextLevel;
    Date birthday;
    Boolean banned;

    public Player(Long id, String name, String title, Race race, Profession profession, Integer experience, Integer level, Integer untilNextLevel, Date birthday, Boolean banned) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.race = race;
        this.profession = profession;
        this.experience = experience;
        this.level = level;
        this.untilNextLevel = untilNextLevel;
        this.birthday = birthday;
        this.banned = banned;
    }
}
