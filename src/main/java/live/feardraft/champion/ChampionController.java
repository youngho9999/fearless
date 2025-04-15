package live.feardraft.champion;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ChampionController {

    private final ChampionService championService;

    @GetMapping("/champions")
    public List<ChampionResponse> getAllChampions() {
        return championService.getAllChampions();
    }

}
