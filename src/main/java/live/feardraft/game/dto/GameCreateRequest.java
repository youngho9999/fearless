package live.feardraft.game.dto;

import live.feardraft.game.types.ModeType;
import live.feardraft.game.types.SeriesType;
import live.feardraft.game.types.TimerType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GameCreateRequest {

    private String blueTeam;
    private String redTeam;
    private ModeType modeType;
    private SeriesType seriesType;
    private TimerType timerType;

}
