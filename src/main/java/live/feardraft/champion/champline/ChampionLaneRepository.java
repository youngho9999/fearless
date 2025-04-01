package live.feardraft.champion.champline;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChampionLaneRepository extends JpaRepository<ChampionLane, Integer> {
    List<ChampionLane> findByChampion(String champion);
}
