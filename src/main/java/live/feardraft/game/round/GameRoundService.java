package live.feardraft.game.round;

import live.feardraft.game.dto.PickClickRequest;
import live.feardraft.game.types.RoundState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import static live.feardraft.game.types.RoundState.*;

@Service
@RequiredArgsConstructor
public class GameRoundService {
    private static final Map<RoundState, BiConsumer<GameRound, String>> UPDATERS = new HashMap<>();
    private final GameRoundRepository gameRoundRepository;

    static {
        UPDATERS.put(BLUE_BAN_1, GameRound::setBlueBan1);
        UPDATERS.put(BLUE_BAN_2, GameRound::setBlueBan2);
        UPDATERS.put(BLUE_BAN_3, GameRound::setBlueBan3);
        UPDATERS.put(BLUE_BAN_4, GameRound::setBlueBan4);
        UPDATERS.put(BLUE_BAN_5, GameRound::setBlueBan5);
        UPDATERS.put(RED_BAN_1, GameRound::setRedBan1);
        UPDATERS.put(RED_BAN_2, GameRound::setRedBan2);
        UPDATERS.put(RED_BAN_3, GameRound::setRedBan3);
        UPDATERS.put(RED_BAN_4, GameRound::setRedBan4);
        UPDATERS.put(RED_BAN_5, GameRound::setRedBan5);
        UPDATERS.put(BLUE_PICK_1, GameRound::setBluePick1);
        UPDATERS.put(BLUE_PICK_2, GameRound::setBluePick2);
        UPDATERS.put(BLUE_PICK_3, GameRound::setBluePick3);
        UPDATERS.put(BLUE_PICK_4, GameRound::setBluePick4);
        UPDATERS.put(BLUE_PICK_5, GameRound::setBluePick5);
        UPDATERS.put(RED_PICK_1, GameRound::setRedPick1);
        UPDATERS.put(RED_PICK_2, GameRound::setRedPick2);
        UPDATERS.put(RED_PICK_3, GameRound::setRedPick3);
        UPDATERS.put(RED_PICK_4, GameRound::setRedPick4);
        UPDATERS.put(RED_PICK_5, GameRound::setRedPick5);
    }


    private void updateGameRound(GameRound gameRound, PickClickRequest request) {
        BiConsumer<GameRound, String> updater = UPDATERS.get(request.getRoundState());
        if (updater != null) {
            updater.accept(gameRound, request.getChampion());
        } else {
            throw new IllegalArgumentException("Invalid sequence: " + request.getRoundState());
        }
    }

    @Transactional
    public void pickLock(PickClickRequest request) {
        GameRound round = gameRoundRepository.findByGame_GameIdAndRoundNumber(request.getGameId(), request.getRoundNumber()).orElseThrow(RuntimeException::new);
        updateGameRound(round, request);
        round.moveToNextState(request.getRoundState());
    }
}
