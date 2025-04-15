package live.feardraft.champion.champline;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ChampionLaneCrawlerController {

    private final ChampionLaneCrawlerService championCrawlerService;

    @GetMapping("/crawl")
    public void crawlChampions() {
        championCrawlerService.crawlChampions();
    }

    @GetMapping("/lck")
    public void crawlLckData() {
        championCrawlerService.crawlLckData();
    }

}
