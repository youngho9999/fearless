package live.feardraft.game;

import jakarta.persistence.*;
import live.feardraft.game.types.ModeType;
import live.feardraft.game.types.SeriesType;
import live.feardraft.game.types.TimerType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gameId;
    private String blueTeam;
    private String redTeam;

    @Enumerated(EnumType.STRING)
    private ModeType modeType;
    @Enumerated(EnumType.STRING)
    private SeriesType seriesType;
    @Enumerated(EnumType.STRING)
    private TimerType timerType;

    public Game(String blueTeam, String redTeam, ModeType modeType, SeriesType seriesType, TimerType timerType) {
        this.blueTeam = blueTeam;
        this.redTeam = redTeam;
        this.modeType = modeType;
        this.seriesType = seriesType;
        this.timerType = timerType;
    }
}
