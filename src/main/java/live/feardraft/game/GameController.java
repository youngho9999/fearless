package live.feardraft.game;

import live.feardraft.game.dto.GameCreateRequest;
import live.feardraft.game.dto.GameReadyRequest;
import live.feardraft.game.dto.GameSettingResponse;
import live.feardraft.game.dto.PickClickRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;
    private final SimpMessagingTemplate template;

    @PostMapping
    public String createGame(@RequestBody final GameCreateRequest request) {
        return gameService.createGame(request);
    }

    @GetMapping("/{gameId}")
    public GameSettingResponse getGameSettings(@PathVariable final String gameId) {
        return gameService.getGameSettings(gameId);
    }

    @MessageMapping("/ready")
    public void setReady(@Payload final GameReadyRequest request) {
        int readyCount = gameService.setReady(request);
        template.convertAndSend("/sub/ready/" + request.getGameId(), readyCount);
    }

}
