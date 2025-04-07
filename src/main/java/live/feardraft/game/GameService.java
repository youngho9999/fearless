package live.feardraft.game;

import live.feardraft.game.dto.GameCreateRequest;
import live.feardraft.game.dto.GameReadyRequest;
import live.feardraft.game.dto.GameSettingResponse;
import live.feardraft.game.round.GameRound;
import live.feardraft.game.round.GameRoundRepository;
import live.feardraft.game.types.RoundState;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


@Service
@RequiredArgsConstructor
@Slf4j
public class GameService {

    private final GameRepository gameRepository;
    private final GameRoundRepository gameRoundRepository;

    private static final RoundState INITIAL_ROUNDSTATE = RoundState.BLUE_BAN_1;

    @Transactional
    public String createGame(GameCreateRequest request) {

        String gameId = StringUtils.deleteAny(java.util.UUID.randomUUID().toString(), "-").substring(0, 10);

        log.info("=------------------------ get seriesType : {}", request.getSeriesType());

        Game game = new Game(
                gameId,
                request.getBlueTeam(),
                request.getRedTeam(),
                request.getModeType(),
                request.getSeriesType(),
                request.getTimerType()
        );
        gameRepository.save(game);

        for(int i = 1; i <= request.getSeriesType().getRound(); i++) {
            GameRound gameRound = new GameRound(game,i,INITIAL_ROUNDSTATE);
            gameRoundRepository.save(gameRound);
        }

        return game.getGameId();
    }

    public GameSettingResponse getGameSettings(String gameId) {
        Game game = gameRepository.findById(gameId).orElseThrow(RuntimeException::new);
        return new GameSettingResponse(game);
    }

    @Transactional
    public int setReady(GameReadyRequest request) {
        GameRound round = gameRoundRepository.findByGameIdAndRoundNumberWithLock(request.getGameId(), request.getRoundNumber())
                            .orElseThrow(RuntimeException::new);
        round.ready();
        return round.getReadyCount();
    }

}
