package live.feardraft.game.round;

import jakarta.persistence.*;
import live.feardraft.game.Game;
import live.feardraft.game.types.RoundState;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class GameRound {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gameRoundId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Game game;
    private int roundNumber;

    private int readyCount;

    @Enumerated(EnumType.STRING)
    private RoundState roundState;

    private String blueBan1;
    private String blueBan2;
    private String blueBan3;
    private String blueBan4;
    private String blueBan5;
    private String redBan1;
    private String redBan2;
    private String redBan3;
    private String redBan4;
    private String redBan5;

    private String bluePick1;
    private String bluePick2;
    private String bluePick3;
    private String bluePick4;
    private String bluePick5;
    private String redPick1;
    private String redPick2;
    private String redPick3;
    private String redPick4;
    private String redPick5;

    public void moveToNextState(RoundState state) {
        roundState = RoundState.values()[state.ordinal()+1];
    }

    public GameRound(Game game, int roundNumber, RoundState roundState) {
        this.game = game;
        this.roundNumber = roundNumber;
        this.roundState = roundState;
    }

    public void ready() {
        readyCount++;
    }
}
