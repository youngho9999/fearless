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

    @GetMapping("/champions")
    public List<Champion> getAllChampions() {
        return championService.getAllChampions();
    }

}
