package com.game.dto;

import com.game.enums.ProfessionEnum;
import com.game.enums.RaceEnum;
import com.game.exception.RpgValidateException;

import java.util.Objects;

public class CreateOrUpdatePlayerRequestDto {

    private String name;
    private String title;
    private RaceEnum race;
    private ProfessionEnum profession;
    private Integer experience;
    private Long birthday;
    private Boolean banned;

    public void checkOnCreate() {
        Object[] allFields = {name, title, race, profession, experience, birthday, banned};
        for (Object field : allFields) {
            if (field == null) {
                throw new RpgValidateException();
            }
        }
    }

    public boolean isAllFieldsNull() {
        Object[] allFields = {name, title, race, profession, experience, birthday, banned};
        int countOfNull = 0;
        for (Object field : allFields) {
            if (field == null) {
                countOfNull++;
            }
        }
        return allFields.length == countOfNull;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public RaceEnum getRace() {
        return race;
    }

    public void setRace(RaceEnum race) {
        this.race = race;
    }

    public ProfessionEnum getProfession() {
        return profession;
    }

    public void setProfession(ProfessionEnum profession) {
        this.profession = profession;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public Long getBirthday() {
        return birthday;
    }

    public void setBirthday(Long birthday) {
        this.birthday = birthday;
    }

    public Boolean getBanned() {
        return banned;
    }

    public void setBanned(Boolean banned) {
        this.banned = banned;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateOrUpdatePlayerRequestDto that = (CreateOrUpdatePlayerRequestDto) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(title, that.title) &&
                race == that.race &&
                profession == that.profession &&
                Objects.equals(experience, that.experience) &&
                Objects.equals(birthday, that.birthday) &&
                Objects.equals(banned, that.banned);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, title, race, profession, experience, birthday, banned);
    }
}
