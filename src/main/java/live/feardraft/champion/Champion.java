package live.feardraft.champion;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Champion {

    @Id
    String id;
    String name;
    String image;
}
