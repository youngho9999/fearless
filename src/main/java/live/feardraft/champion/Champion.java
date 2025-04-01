package live.feardraft.champion;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@ToString
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Champion {

    @Id
    private String id;
    private String name;
    private String image;

    private String positions;  // "TOP,JUNGLE,MID,AD,SUPPORT"

    // 문자열을 리스트로 변환하는 메서드
    @Transient  // DB에 저장되지 않는 필드
    public List<String> getPositionList() {
        if (positions == null || positions.isEmpty()) {
            return Collections.emptyList();
        }
        return Arrays.asList(positions.split(","));
    }

    // 리스트를 문자열로 변환하는 메서드
    public void setPositionList(List<String> positionList) {
        if (positionList == null || positionList.isEmpty()) {
            this.positions = "";
        } else {
            this.positions = String.join(",", positionList);
        }
    }

    public Champion(String id, String name, String image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }
}
