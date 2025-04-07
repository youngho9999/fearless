package live.feardraft.game.types;

import lombok.Getter;

@Getter
public enum SeriesType {
    SINGLE(1), BO3(3), BO5(5);

    private final int round;

    SeriesType(int round) {
        this.round = round;
    }
}
