package live.feardraft.champion.crawl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class LckDataDto {

    private String name;
    private String banPickRate;
    private String pick;
    private String ban;
    private String wins;
    private String losses;
    private String winRate;
}
