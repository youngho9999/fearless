package live.feardraft.game.round;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GameRoundRepository extends JpaRepository<GameRound, Long> {

    Optional<GameRound> findByGame_GameIdAndRoundNumber(String gameId, int roundNumber);
}
