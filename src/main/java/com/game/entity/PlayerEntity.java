package com.game.entity;

import com.game.enums.ProfessionEnum;
import com.game.enums.RaceEnum;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "player")
public class PlayerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "title")
    private String title;
    @Enumerated(EnumType.STRING)
    @Column(name = "race")
    private RaceEnum race;
    @Enumerated(EnumType.STRING)
    @Column(name = "profession")
    private ProfessionEnum profession;
    @Column(name = "experience")
    private Integer experience;
    @Column(name = "birthday")
    private LocalDate birthday;
    @Column(name = "banned", nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean banned;

    // Counted
    @Column(name = "level")
    private Integer level;
    @Column(name = "until_next_level")
    private Integer untilNextLevel;

    @PrePersist
    @PreUpdate
    public void changeDefault() {
        if (this.banned == null) {
            this.banned = false;
        }
    }

    public PlayerEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getUntilNextLevel() {
        return untilNextLevel;
    }

    public void setUntilNextLevel(Integer untilNextLevel) {
        this.untilNextLevel = untilNextLevel;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Boolean getBanned() {
        return banned;
    }

    public void setBanned(Boolean banned) {
        this.banned = banned;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!getClass().isInstance(o)) {
            return false;
        }
        PlayerEntity other = (PlayerEntity) o;
        return getId() != null && Objects.equals(getId(), other.getId());
    }

    @Override
    public final int hashCode() {
        return getClass().hashCode();
    }
}