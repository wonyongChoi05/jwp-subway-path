package subway.domain.entity;

import subway.domain.vo.Distance;

import java.util.Objects;

public class SectionEntity {

    private final Long id;
    private final Long lineId;
    private final Long upStationId;
    private final Long downStationId;
    private final Distance distance;

    private SectionEntity(
            final Long id,
            final Long lineId,
            final Long upStationId,
            final Long downStationId,
            final Distance distance
    ) {
        this.id = id;
        this.lineId = lineId;
        this.upStationId = upStationId;
        this.downStationId = downStationId;
        this.distance = distance;
    }

    public static SectionEntity of(
            final Long lineId,
            final Long upStationId,
            final Long downStationId,
            final int distance
    ) {
        return new SectionEntity(null, lineId, upStationId, downStationId, Distance.from(distance));
    }

    public static SectionEntity of(
            final Long id,
            final Long lineId,
            final Long upStationId,
            final Long downStationId,
            final int distance
    ) {
        return new SectionEntity(id, lineId, upStationId, downStationId, Distance.from(distance));
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
        return distance.getDistance();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final SectionEntity that = (SectionEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(lineId, that.lineId) && Objects.equals(upStationId, that.upStationId) && Objects.equals(downStationId, that.downStationId) && Objects.equals(distance, that.distance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lineId, upStationId, downStationId, distance);
    }

}
