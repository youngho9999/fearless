package live.feardraft.game;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;

    @PostMapping
    public String createGame(@RequestBody final GameCreateRequest request) {
        return gameService.createGame(request);
    }

}
