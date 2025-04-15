package live.feardraft.champion.champline;

import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.net.http.HttpHeaders;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ChampionLaneCrawlerService {

    private final ChampionLaneRepository championLaneRepository;

    @Value("${championlane}")
    private String laneCrawlUrl;

    public void crawlChampions() {

        championLaneRepository.deleteAll();

        ChampLane[] lanes = ChampLane.values();

        for(ChampLane lane : lanes) {
            List<String> champions = crawlChampionNamesForEachLane(lane.toString().toLowerCase());

            for(String champion : champions) {
                ChampionLane championLane = new ChampionLane(lane.toString(), champion);
                championLaneRepository.save(championLane);
            }
        }
    }

    private List<String> crawlChampionNamesForEachLane(String lane) {
        List<String> championNames = new ArrayList<>();
        String url = laneCrawlUrl + lane;

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
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return championNames;
    }

    public void crawlLckData() {

        String url = "url"; // 요청할 URL

        WebClient webClient = WebClient.create(url);

        // 요청 바디 구성
        Map<String, Object> body = new HashMap<>();
        body.put("operationName", "ListChampionStatisticsByTournament");

        Map<String, String> variables = new HashMap<>();
        variables.put("tournamentId", "1253");
        body.put("variables", variables);

        body.put("query", """
            fragment CoreChampionStatistic on ChampionStatistic {
              tournamentId
              championId
              bans
              presence
              position
              games
              wins
              loses
              winRate
              kda
              kills
              deaths
              assists
              wardsPlaced
              wardsKilled
              dpm
              dtpm
              gpm
              cspm
              dpgr
              firstBlood
              firstTower
              __typename
            }
            query ListChampionStatisticsByTournament($tournamentId: ID!, $teamId: ID, $playerId: ID) {
              championStatisticsByTournament(
                tournamentId: $tournamentId
                teamId: $teamId
                playerId: $playerId
              ) {
                ...CoreChampionStatistic
                __typename
              }
            }
        """);

        // 비동기 요청 실행
        Mono<String> response = webClient.post()
                .header("Content-Type", "application/json")
                .bodyValue(body)
                .retrieve()
                .bodyToMono(String.class);

        // 응답 출력
        response.subscribe(System.out::println);

    }
}
