package live.feardraft.champion;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import live.feardraft.champion.crawl.ChampionLane;
import live.feardraft.champion.crawl.ChampionLaneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
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
