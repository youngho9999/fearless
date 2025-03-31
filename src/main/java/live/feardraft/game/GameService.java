package live.feardraft.game;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
                request.getTimerType()
        );

        gameRepository.save(game);
        return game.getGameId();
    }

}
