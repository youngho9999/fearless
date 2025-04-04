package live.feardraft.game;

import live.feardraft.game.dto.GameCreateRequest;
import live.feardraft.game.dto.GameSettingResponse;
import live.feardraft.game.round.GameRound;
import live.feardraft.game.round.GameRoundRepository;
import live.feardraft.game.types.GameState;
import live.feardraft.game.types.RoundState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;
    private final GameRoundRepository gameRoundRepository;

    private static final int INITIAL_ROUND = 1;
    private static final RoundState INITIAL_ROUNDSTATE = RoundState.BLUE_BAN_1;

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
        GameRound gameRound = new GameRound(game,INITIAL_ROUND,INITIAL_ROUNDSTATE);
        gameRoundRepository.save(gameRound);

        return game.getGameId();
    }

    public GameSettingResponse getGameSettings(String gameId) {
        Game game = gameRepository.findById(gameId).orElseThrow(RuntimeException::new);
        return new GameSettingResponse(game);
    }

}
