package live.feardraft.game.dto;

import lombok.Getter;

@Getter
public class NextRoundRequest {
    private String gameId;
    private String nowTeam;
    private String team;
}
