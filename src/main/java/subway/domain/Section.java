package subway.domain;

public class Section {

    private final Long id;
    private final Long lineId;
    private final Long upStationId;
    private final Long downStationId;
    private final int distance;

    private Section(
            final Long id,
            final Long lineId,
            final Long upStationId,
            final Long downStationId,
            final int distance
    ) {
        this.id = id;
        this.lineId = lineId;
        this.upStationId = upStationId;
        this.downStationId = downStationId;
        this.distance = distance;
    }

    public static Section of(
            final Long lineId,
            final Long upStationId,
            final Long downStationId,
            final int distance
    ) {
        return new Section(null, lineId, upStationId, downStationId, distance);
    }

    public static Section of(
            final Long id,
            final Long lineId,
            final Long upStationId,
            final Long downStationId,
            final int distance
    ) {
        return new Section(id, lineId, upStationId, downStationId, distance);
    }

    public Long getId() {
        return id;
    }

    public Long getLineId() {
        return lineId;
    }

    public Long getUpStationId() {
        return upStationId;
    }

    public Long getDownStationId() {
        return downStationId;
    }

    public int getDistance() {
        return distance;
    }
}
