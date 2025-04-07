package live.feardraft.game.dto;

import live.feardraft.game.types.RoundState;
import lombok.Getter;

@Getter
public class PickClickRequest {

    private String gameId;
    private int roundNumber;
    private String champion;
    private RoundState roundState;
}
