package live.feardraft.game;

import jakarta.persistence.*;
import live.feardraft.game.types.ModeType;
import live.feardraft.game.types.SeriesType;
import live.feardraft.game.types.TimerType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Game {

    @Id
    private String gameId;
    private String blueTeam;
    private String redTeam;

    @Enumerated(EnumType.STRING)
    private ModeType modeType;
    @Enumerated(EnumType.STRING)
    private SeriesType seriesType;
    @Enumerated(EnumType.STRING)
    private TimerType timerType;
}
