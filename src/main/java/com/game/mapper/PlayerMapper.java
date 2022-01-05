package com.game.mapper;

import com.game.dto.CreateOrUpdatePlayerRequestDto;
import com.game.dto.PlayerRequestDto;
import com.game.dto.PlayerResponseDto;
import com.game.entity.PlayerEntity;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface PlayerMapper {

    PageRequest toPlayerPageRequest(PlayerRequestDto request);

    List<PlayerResponseDto> toPlayerResponses(List<PlayerEntity> playerEntities);

    PlayerResponseDto toPlayerResponse(PlayerEntity playerEntity);

    void createOrUpdateEntity(PlayerEntity entity, CreateOrUpdatePlayerRequestDto request);
}
