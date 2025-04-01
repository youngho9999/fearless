package live.feardraft.champion;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChampionController {

    private final ChampionService championService;

    @GetMapping("/insert")
    public void insertChampion() throws JsonProcessingException {
        championService.insertChampion();
    }

    @GetMapping("/setlane")
    public void setLane() {
        championService.setLane();
    }

    @GetMapping("/champions")
    public List<ChampionResponse> getAllChampions() {
        return championService.getAllChampions();
    }

}
