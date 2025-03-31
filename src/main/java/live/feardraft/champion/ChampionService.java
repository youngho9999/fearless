package live.feardraft.champion;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChampionService {

    private static final String CHAMPIONS_URL = "https://ddragon.leagueoflegends.com/cdn/15.6.1/data/ko_KR/champion.json";
    private final ChampionRepository championRepository;

    public void insertChampion() throws JsonProcessingException {

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

            Champion champion = new Champion(id, name, image);
            champions.add(champion);
        }

        championRepository.saveAll(champions);

    }

    public List<Champion> getAllChampions() {
        return championRepository.findAll();
    }

}
