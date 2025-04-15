package live.feardraft.champion;

import live.feardraft.champion.crawl.ChampionLane;
import live.feardraft.champion.crawl.ChampionLaneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChampionService {

    private final ChampionRepository championRepository;
    private final ChampionLaneRepository championLaneRepository;

    @Transactional
    public void setLane() {
        List<Champion> champions = championRepository.findAll();

        for(Champion champion : champions) {
            List<ChampionLane> lanes = championLaneRepository.findByChampion(champion.getName());
            List<String> laneStrings = lanes.stream().map(ChampionLane::getLane).toList();

            champion.setPositionList(laneStrings);
        }
    }

    public List<ChampionResponse> getAllChampions() {
        List<Champion> champions = championRepository.findAll();
        return champions.stream().map(ChampionResponse::of)
                .sorted(Comparator.comparing(ChampionResponse::getName))
                .toList();
    }

}
