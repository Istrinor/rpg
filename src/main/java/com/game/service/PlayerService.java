package com.game.service;

import com.game.dto.CreateOrUpdatePlayerRequestDto;
import com.game.dto.PlayerRequestDto;
import com.game.dto.PlayerResponseDto;

import java.util.List;

public interface PlayerService {

    List<PlayerResponseDto> getPlayers(PlayerRequestDto request);

    Integer countPlayers(PlayerRequestDto request);

    PlayerResponseDto createPlayer(CreateOrUpdatePlayerRequestDto request);

    PlayerResponseDto getPlayer(Long id);

    PlayerResponseDto editPlayer(Long id, CreateOrUpdatePlayerRequestDto request);

    void deletePlayer(Long id);
}
