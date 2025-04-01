package live.feardraft.game;

import live.feardraft.game.dto.GameCreateRequest;
import live.feardraft.game.dto.GameSettingResponse;
import live.feardraft.game.types.GameState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;

    public String createGame(GameCreateRequest request) {

        String gameId = StringUtils.deleteAny(java.util.UUID.randomUUID().toString(), "-").substring(0, 10);

        Game game = new Game(
                gameId,
                request.getBlueTeam(),
                request.getRedTeam(),
                request.getModeType(),
                request.getSeriesType(),
                request.getTimerType(),
                GameState.NOT_READY
        );

        gameRepository.save(game);
        return game.getGameId();
    }

    public GameSettingResponse getGameSettings(String gameId) {
        Game game = gameRepository.findById(gameId).orElse(null);

        return new GameSettingResponse(game);
    }

}
