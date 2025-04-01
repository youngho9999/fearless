package live.feardraft.champion.champline;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class ChampionLane {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String lane;
    private String champion;

    public ChampionLane(String lane, String champion) {
        this.lane = lane;
        this.champion = champion;
    }
}
