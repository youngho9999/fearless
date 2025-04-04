package live.feardraft.game;

import live.feardraft.game.dto.GameCreateRequest;
import live.feardraft.game.dto.GameSettingResponse;
import live.feardraft.game.dto.PickClickRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @MessageMapping("/gamesetting")
    public void getGameSettings(@Payload final String gameId) {
        System.out.println(gameId);
        GameSettingResponse gameSetting = gameService.getGameSettings(gameId);
        template.convertAndSend("/sub/" + gameId, gameSetting);
    }

}
