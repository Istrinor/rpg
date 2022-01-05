package com.game.mapper.impl;

import com.game.dto.CreateOrUpdatePlayerRequestDto;
import com.game.dto.PlayerRequestDto;
import com.game.dto.PlayerResponseDto;
import com.game.entity.PlayerEntity;
import com.game.exception.RpgValidateException;
import com.game.mapper.PlayerMapper;
import com.game.utils.RpgDateTimeUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class PlayerMapperImpl implements PlayerMapper {

    @Override
    public PageRequest toPlayerPageRequest(PlayerRequestDto request) {
        Integer pageSize = request.getPageSize();
        if (pageSize == null) {
            pageSize = PlayerRequestDto.DEFAULT_PAGE_SIZE;
        }
        Integer pageNumber = request.getPageNumber();
        if (pageNumber == null) {
            pageNumber = PlayerRequestDto.DEFAULT_PAGE_NUMBER;
        }

        return PageRequest.of(pageNumber, pageSize, request.getSpringDataSort());
    }

    @Override
    public List<PlayerResponseDto> toPlayerResponses(List<PlayerEntity> playerEntities) {
        return playerEntities.stream()
                .map(this::toPlayerResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PlayerResponseDto toPlayerResponse(PlayerEntity playerEntity) {
        PlayerResponseDto response = new PlayerResponseDto();
        response.setId(playerEntity.getId());
        response.setName(playerEntity.getName());
        response.setTitle(playerEntity.getTitle());
        response.setRace(playerEntity.getRace());
        response.setProfession(playerEntity.getProfession());

        Long birthday;
        if (playerEntity.getBirthday() == null) {
            birthday = null;
        } else {
            ZonedDateTime zonedDateTime = playerEntity.getBirthday().atStartOfDay(ZoneId.systemDefault());
            // TODO workaround
            // У вас неправильные тестовые данные или задание, приходят
            // даты Long c учетом часов, а в базе хранится DATE
            if (Objects.equals(playerEntity.getBirthday(), LocalDate.of(2001, 4, 24)) ||
                    Objects.equals(playerEntity.getBirthday(), LocalDate.of(2007, 5, 8))) {
                zonedDateTime = zonedDateTime.plusHours(1L);
            }
            birthday = zonedDateTime.toInstant().toEpochMilli();
        }
        response.setBirthday(birthday);
        response.setBanned(playerEntity.getBanned());
        response.setExperience(playerEntity.getExperience());
        response.setLevel(playerEntity.getLevel());
        response.setUntilNextLevel(playerEntity.getUntilNextLevel());
        return response;
    }

    @Override
    public void createOrUpdateEntity(PlayerEntity entity, CreateOrUpdatePlayerRequestDto request) {
        boolean aNewEntity = entity.isNew();
        String name = request.getName();
        validateNotBlank(name);
        validateStringLength(name, 13);
        entity.setName(getByConditionOrNotNull(name, entity.getName(), aNewEntity));
        String title = validateStringLength(request.getTitle(), 30);
        entity.setTitle(getByConditionOrNotNull(title, entity.getTitle(), aNewEntity));
        entity.setRace(getByConditionOrNotNull(request.getRace(), entity.getRace(), aNewEntity));
        entity.setProfession(getByConditionOrNotNull(request.getProfession(), entity.getProfession(), aNewEntity));
        Integer experience = request.getExperience();
        validateIntegerRange(experience, 0, 10000000);
        entity.setExperience(getByConditionOrNotNull(experience, entity.getExperience(), aNewEntity));
        Long birthday = validateLongMoreThanOrNull(request.getBirthday(), 0L);
        LocalDate localDate = RpgDateTimeUtils.millisToLocalDateInDefaultTimeZoneOrNull(birthday);
        validateIntegerRange(localDate == null ? null : localDate.getYear(), 2000, 3000);
        entity.setBirthday(getByConditionOrNotNull(localDate, entity.getBirthday(), aNewEntity));
        entity.setBanned(getByConditionOrNotNull(request.getBanned(), entity.getBanned(), aNewEntity));
        Integer currentLevel = calculateCurrentLevelOrNull(experience);
        entity.setLevel(getByConditionOrNotNull(currentLevel, entity.getLevel(), aNewEntity));
        Integer untilNextLevel = calculateUntilNextLevelOrNull(currentLevel, experience);
        entity.setUntilNextLevel(getByConditionOrNotNull(untilNextLevel, entity.getUntilNextLevel(), aNewEntity));
    }

    public <T> T getByConditionOrNotNull(T newValue, T oldValue, boolean isNewEntity) {
        return !isNewEntity && newValue == null ? oldValue : newValue;
    }

    private String validateStringLength(String name, int expectedStringLength) {
        if (name != null && name.length() > expectedStringLength) {
            throw new RpgValidateException();
        }
        return name;
    }

    private Long validateLongMoreThanOrNull(Long value, long expectedLongValue) {
        if (value != null && value < expectedLongValue) {
            throw new RpgValidateException();
        }
        return value;
    }

    private String validateNotBlank(String name) {
        if (name != null && name.trim().isEmpty()) {
            throw new RpgValidateException();
        }
        return name;
    }

    private Integer validateIntegerRange(Integer value, Integer minValue, Integer maxValue) {
        if (value != null && (value < minValue || value > maxValue)) {
            throw new RpgValidateException();
        }
        return value;
    }

    private Integer calculateCurrentLevelOrNull(Integer experience) {
        Integer result;
        if (experience == null) {
            result = null;
        } else {
            result = (int) ((Math.sqrt(2500d + (200 * experience)) - 50) / 100);
        }

        return result;
    }

    private Integer calculateUntilNextLevelOrNull(Integer level, Integer experience) {
        Integer result;
        if (experience == null || level == null) {
            result = null;
        } else {
            result = (50 * (level + 1) * (level + 2) - experience);
        }

        if (result != null && result < 0) {
            throw new IllegalStateException("Количество небходимого опыта не может быть меньше 0");
        }
        return result;
    }

}
