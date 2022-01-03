package com.game.controller;

import com.game.dto.CreateOrUpdatePlayerRequestDto;
import com.game.dto.PlayerRequestDto;
import com.game.dto.PlayerResponseDto;
import com.game.service.PlayerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/players")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public List<PlayerResponseDto> getPlayers(PlayerRequestDto request) {
        return playerService.getPlayers(request);
    }

    @PostMapping
    public PlayerResponseDto createPlayer(@RequestBody CreateOrUpdatePlayerRequestDto request) {
        request.checkOnCreate();
        return playerService.createPlayer(request);
    }

    @GetMapping("/count")
    public Integer countPlayers(PlayerRequestDto request) {
        return playerService.countPlayers(request);
    }

    @GetMapping("/{id}")
    public PlayerResponseDto getPlayer(@PathVariable("id") Long id) {
        return playerService.getPlayer(id);
    }

    @PostMapping("/{id}")
    public PlayerResponseDto editPlayer(@RequestBody CreateOrUpdatePlayerRequestDto request,
                                        @PathVariable("id") Long id) {
        return playerService.editPlayer(id, request);
    }

    @DeleteMapping("/{id}")
    public void deletePlayer(@PathVariable("id") Long id) {
        playerService.deletePlayer(id);
    }
}
