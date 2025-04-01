package live.feardraft.champion;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ChampionResponse {

    private final String id;
    private final String name;
    private final String image;

    private final List<String> positions;

    public static ChampionResponse of(Champion champion) {
        return new ChampionResponse(champion.getId(), champion.getName(), champion.getImage(), champion.getPositionList());
    }

}
