package com.game.mapper.impl;

import com.game.dto.CreateOrUpdatePlayerRequestDto;
import com.game.dto.PlayerPageRequest;
import com.game.dto.PlayerRequestDto;
import com.game.dto.PlayerResponseDto;
import com.game.entity.PlayerEntity;
import com.game.exception.RpgValidateException;
import com.game.mapper.PlayerMapper;
import com.game.utils.RpgDateTimeUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PlayerMapperImpl implements PlayerMapper {

    @Override
    public PlayerPageRequest toPlayerPageRequest(PlayerRequestDto request) {
        PlayerPageRequest result = new PlayerPageRequest();
        Integer pageSize = request.getPageSize();
        if (pageSize == null) {
            pageSize = PlayerRequestDto.DEFAULT_PAGE_SIZE;
        }
        result.setPageSize(pageSize);
        Integer pageNumber = request.getPageNumber();
        if (pageNumber == null) {
            pageNumber = PlayerRequestDto.DEFAULT_PAGE_NUMBER;
        }
        result.setPageNumber(pageNumber);
        result.setSort(request.getSpringDataSort());

        return result;
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
        Long birthday = playerEntity.getBirthday() == null ?
                null : playerEntity.getBirthday()
                .atStartOfDay(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli();
        response.setBirthday(birthday);
        response.setBanned(playerEntity.getBanned());
        response.setExperience(playerEntity.getExperience());
        response.setLevel(playerEntity.getLevel());
        response.setUntilNextLevel(playerEntity.getUntilNextLevel());
        return response;
    }

    @Override
    public void createOrUpdateEntity(PlayerEntity entity, CreateOrUpdatePlayerRequestDto request) {
        String name = request.getName();
        validateNotBlank(name);
        entity.setName(validateStringLength(name, 13));
        entity.setTitle(validateStringLength(request.getTitle(), 30));
        entity.setRace(request.getRace());
        entity.setProfession(request.getProfession());
        Integer experience = request.getExperience();
        entity.setExperience(validateIntegerRange(experience, 0, 10000000));
        Long birthday = validateLongMoreThanOrNull(request.getBirthday(), 0L);
        LocalDate localDate = RpgDateTimeUtils.millisToLocalDateInDefaultTimeZoneOrNull(birthday);
        validateIntegerRange(localDate == null ? null : localDate.getYear(), 2000, 3000);
        entity.setBirthday(localDate);
        entity.setBanned(request.getBanned());
        Integer currentLevel = calculateCurrentLevelOrNull(experience);
        entity.setLevel(currentLevel);
        entity.setUntilNextLevel(calculateUntilNextLevelOrNull(currentLevel, experience));

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
