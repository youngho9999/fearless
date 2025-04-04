package live.feardraft.game.dto;

import lombok.Getter;

@Getter
public class PickClickRequest {

    private String gameId;
    private int roundNumber;
    private String champion;
    private String sequence;
}
