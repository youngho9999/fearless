package live.feardraft.game.dto;

import live.feardraft.game.Game;
import live.feardraft.game.types.GameState;
import live.feardraft.game.types.ModeType;
import live.feardraft.game.types.SeriesType;
import live.feardraft.game.types.TimerType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GameSettingResponse {

    private String gameId;
    private String blueTeam;
    private String redTeam;
    private ModeType modeType;
    private SeriesType seriesType;
    private TimerType timerType;
    private GameState gameState;

    public GameSettingResponse(Game game) {
        this.gameId = game.getGameId();
        this.blueTeam = game.getBlueTeam();
        this.redTeam = game.getRedTeam();
        this.modeType = game.getModeType();
        this.seriesType = game.getSeriesType();
        this.timerType = game.getTimerType();
        this.gameState = game.getGameState();
    }
}
