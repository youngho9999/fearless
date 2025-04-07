package live.feardraft.game.dto;

import lombok.Getter;

@Getter
public class GameReadyRequest {

    private String gameId;
    private int roundNumber;
}
