package live.feardraft.champion;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import live.feardraft.champion.champline.ChampionLane;
import live.feardraft.champion.champline.ChampionLaneRepository;
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

    @Value("${champion}")
    private String CHAMPIONS_URL;

    public void insertChampion() throws JsonProcessingException {

        championRepository.deleteAll();

        RestTemplate restTemplate = new RestTemplate();
        String jsonContent = restTemplate.getForObject(CHAMPIONS_URL, String.class);

        List<Champion> champions = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode rootNode = objectMapper.readTree(jsonContent);
        JsonNode dataNode = rootNode.path("data");

        // data 노드 내부의 모든 챔피언 객체를 순회
        Iterator<String> fieldNames = dataNode.fieldNames();
        while (fieldNames.hasNext()) {
            String fieldName = fieldNames.next();
            JsonNode championNode = dataNode.get(fieldName);

            String id = championNode.path("id").asText();
            String name = championNode.path("name").asText();
            // image.full 속성 값을 가져옴
            String image = championNode.path("image").path("full").asText();
            String key = championNode.path("key").asText();

            Champion champion = new Champion(id, name, image,key);
            champions.add(champion);
        }

        championRepository.saveAll(champions);
    }

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
