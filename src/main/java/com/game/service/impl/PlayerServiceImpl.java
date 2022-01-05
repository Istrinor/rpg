package com.game.service.impl;

import com.game.dto.CreateOrUpdatePlayerRequestDto;
import com.game.dto.PlayerRequestDto;
import com.game.dto.PlayerResponseDto;
import com.game.entity.PlayerEntity;
import com.game.exception.RpgNotFoundException;
import com.game.exception.RpgValidateException;
import com.game.mapper.PlayerMapper;
import com.game.repository.PlayerRepository;
import com.game.service.PlayerService;
import com.game.utils.RpgDateTimeUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;

    public PlayerServiceImpl(PlayerRepository playerRepository, PlayerMapper playerMapper) {
        this.playerRepository = playerRepository;
        this.playerMapper = playerMapper;
    }

    @Override
    public List<PlayerResponseDto> getPlayers(PlayerRequestDto request) {
        Date after = RpgDateTimeUtils.millisToDateInDefaultTimeZoneOrNull(request.getAfter());
        Date before = RpgDateTimeUtils.millisToDateInDefaultTimeZoneOrNull(request.getBefore());
        List<PlayerEntity> playerEntities = playerRepository.findAllPlayers(request.getName(), request.getTitle(),
                request.getRaceStringOrNull(), request.getProfessionStringOrNull(), after, before, request.getBanned(),
                request.getMinExperience(), request.getMaxExperience(), request.getMinLevel(),
                request.getMaxLevel()
                , playerMapper.toPlayerPageRequest(request));
        return playerMapper.toPlayerResponses(playerEntities);
    }

    @Override
    public Integer countPlayers(PlayerRequestDto request) {
        Date after = RpgDateTimeUtils.millisToDateInDefaultTimeZoneOrNull(request.getAfter());
        Date before = RpgDateTimeUtils.millisToDateInDefaultTimeZoneOrNull(request.getBefore());
        long countPlayers = playerRepository.countAllPlayers(request.getName(), request.getTitle(),
                request.getRaceStringOrNull(), request.getProfessionStringOrNull(), after, before, request.getBanned(),
                request.getMinExperience(), request.getMaxExperience(), request.getMinLevel(),
                request.getMaxLevel());
        if (countPlayers < Integer.MIN_VALUE) {
            throw new IllegalStateException("Количество игроков больше ожидаемого!!");
        }
        return (int) countPlayers;
    }

    @Override
    public PlayerResponseDto createPlayer(CreateOrUpdatePlayerRequestDto request) {
        PlayerEntity newPlayer = new PlayerEntity();
        playerMapper.createOrUpdateEntity(newPlayer, request);
        PlayerEntity createdPlayer = playerRepository.saveAndFlush(newPlayer);
        return playerMapper.toPlayerResponse(createdPlayer);
    }

    @Override
    public PlayerResponseDto getPlayer(Long id) {
        PlayerEntity playerEntity = findPlayer(id);
        return playerMapper.toPlayerResponse(playerEntity);
    }

    @Override
    public PlayerResponseDto editPlayer(Long id, CreateOrUpdatePlayerRequestDto request) {
        PlayerResponseDto response;
        PlayerEntity playerEntity = findPlayer(id);
        if (!request.isAllFieldsNull()) {
            playerMapper.createOrUpdateEntity(playerEntity, request);
        }
        PlayerEntity updatedPlayer = playerRepository.saveAndFlush(playerEntity);
        response = playerMapper.toPlayerResponse(updatedPlayer);
        return response;
    }

    @Override
    public void deletePlayer(Long id) {
        checkId(id);
        PlayerEntity playerForDeletion = findPlayer(id);
        playerRepository.delete(playerForDeletion);
    }

    private void checkId(Long id) {
        if (id != null && id < 1) {
            throw new RpgValidateException();
        }
    }

    private PlayerEntity findPlayer(Long id) {
        if (id < 1L) {
            throw new RpgValidateException();
        }
        Optional<PlayerEntity> optionalPlayer = playerRepository.findById(id);
        return optionalPlayer.orElseThrow(RpgNotFoundException::new);
    }

}
