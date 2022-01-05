package com.game.dto;

import com.game.controller.PlayerOrder;
import com.game.entity.Profession;
import com.game.entity.Race;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PlayerRequestDto {

    public static final Integer DEFAULT_PAGE_SIZE = 3;
    public static final Integer DEFAULT_PAGE_NUMBER = 0;

    private String name;
    private String title;
    private Race race;
    private Profession profession;
    private Long after;
    private Long before;
    private Boolean banned;
    private Integer minExperience;
    private Integer maxExperience;
    private Integer minLevel;
    private Integer maxLevel;

    // Pageable parameters
    private PlayerOrder order;
    private Integer pageNumber;
    private Integer pageSize;

    public Sort getSpringDataSort() {
        List<Sort.Order> orders = new ArrayList<>();
        //   orders.add(Sort.Order.asc(PlayerOrderEnum.getFieldNameOrDefault(order)));
        return Sort.by(orders);
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

    public Race getRace() {
        return race;
    }

    public String getRaceStringOrNull() {
        if (race == null) {
            return null;
        }
        return race.name();
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Profession getProfession() {
        return profession;
    }

    public String getProfessionStringOrNull() {
        if (profession == null) {
            return null;
        }
        return profession.name();
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public Long getAfter() {
        return after;
    }

    public void setAfter(Long after) {
        this.after = after;
    }

    public Long getBefore() {
        return before;
    }

    public void setBefore(Long before) {
        this.before = before;
    }

    public Boolean getBanned() {
        return banned;
    }

    public void setBanned(Boolean banned) {
        this.banned = banned;
    }

    public Integer getMinExperience() {
        return minExperience;
    }

    public void setMinExperience(Integer minExperience) {
        this.minExperience = minExperience;
    }

    public Integer getMaxExperience() {
        return maxExperience;
    }

    public void setMaxExperience(Integer maxExperience) {
        this.maxExperience = maxExperience;
    }

    public Integer getMinLevel() {
        return minLevel;
    }

    public void setMinLevel(Integer minLevel) {
        this.minLevel = minLevel;
    }

    public Integer getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(Integer maxLevel) {
        this.maxLevel = maxLevel;
    }

    public PlayerOrder getOrder() {
        return order;
    }

    public void setOrder(PlayerOrder order) {
        this.order = order;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerRequestDto that = (PlayerRequestDto) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(title, that.title) &&
                race == that.race &&
                profession == that.profession &&
                Objects.equals(after, that.after) &&
                Objects.equals(before, that.before) &&
                Objects.equals(banned, that.banned) &&
                Objects.equals(minExperience, that.minExperience) &&
                Objects.equals(maxExperience, that.maxExperience) &&
                Objects.equals(minLevel, that.minLevel) &&
                Objects.equals(maxLevel, that.maxLevel) &&
                order == that.order &&
                Objects.equals(pageNumber, that.pageNumber) &&
                Objects.equals(pageSize, that.pageSize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, title, race, profession, after, before, banned, minExperience, maxExperience, minLevel,
                maxLevel, order, pageNumber, pageSize);
    }
}
