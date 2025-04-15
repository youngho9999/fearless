package live.feardraft.champion.crawl;

import live.feardraft.champion.ChampionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/crawl")
@RequiredArgsConstructor
public class ChampionCrawlerController {

    private final ChampionCrawlerService championCrawlerService;
    private final ChampionService championService;


    @GetMapping("/champions")
    public void crawlChampions() {
        championCrawlerService.crawlChampions();
    }

    @GetMapping("/lanes")
    public void crawlChampionLanes() {
        championCrawlerService.crawlChampionLanes();
        championService.setLane();
    }

    @GetMapping("/lck")
    public void crawlLckData() {
        championCrawlerService.crawlLckData();
    }

}
