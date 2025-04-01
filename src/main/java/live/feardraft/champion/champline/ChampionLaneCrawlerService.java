package live.feardraft.champion.champline;

import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChampionLaneCrawlerService {

    private final ChampionLaneRepository championLaneRepository;

    public void crawlChampions() {

        ChampLane[] lanes = ChampLane.values();

        for(ChampLane lane : lanes) {
            List<String> champions = crawlChampionNamesForEachLane(lane.toString().toLowerCase());

            for(String champion : champions) {
                ChampionLane championLane = new ChampionLane(lane.toString(), champion);
                championLaneRepository.save(championLane);
            }
        }

    }




    public List<String> crawlChampionNamesForEachLane(String lane) {
        List<String> championNames = new ArrayList<>();

        String url = "https://op.gg/champions?position=" + lane;

        try {
            // URL에서 HTML 문서 가져오기
            Document doc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")
                    .header("Accept-Language", "ko-KR,ko;q=0.9")
                    .header("Cookie", "customLocale=ko_KR") // 한국어 로케일 쿠키 설정
                    .timeout(30000)
                    .get();

            // tbody 내의 모든 tr 요소 선택
            Elements rows = doc.select("tbody tr");

            for (Element row : rows) {
                try {
                    // 각 행에서 챔피언 이름이 포함된 태그 선택
                    Element nameElement = row.select("td:eq(1) div a strong").first();
                    if (nameElement != null) {
                        String name = nameElement.text();
                        championNames.add(name);
                    }
                } catch (Exception e) {
                    // 개별 행 처리 중 오류는 무시하고 다음 행으로 진행
                    continue;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return championNames;
    }

}
