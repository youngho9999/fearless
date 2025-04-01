package live.feardraft.champion.champline;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChampionLaneCrawlerController {

    private final ChampionLaneCrawlerService championCrawlerService;


    @GetMapping("/crawl")
    public void crawlChampions() {
        championCrawlerService.crawlChampions();
    }

}
