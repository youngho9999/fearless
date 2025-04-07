package live.feardraft.game.round;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface GameRoundRepository extends JpaRepository<GameRound, Long> {

    Optional<GameRound> findByGame_GameIdAndRoundNumber(String gameId, int roundNumber);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select g from GameRound g where g.game.gameId = :gameId and g.roundNumber = :roundNumber")
    Optional<GameRound> findByGameIdAndRoundNumberWithLock(String gameId, int roundNumber);

}
