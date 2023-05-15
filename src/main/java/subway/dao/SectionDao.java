package subway.dao;


import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import subway.domain.SectionEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class SectionDao {

    private RowMapper<SectionEntity> sectionRowMapper = (rs, rowNum) -> SectionEntity.of(
            rs.getLong("id"),
            rs.getLong("line_id"),
            rs.getLong("up_station_id"),
            rs.getLong("down_station_id"),
            rs.getInt("distance"));


    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert insertAction;

    public SectionDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        insertAction = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("SECTIONS")
                .usingGeneratedKeyColumns("id");
    }

    public Long insert(final SectionEntity sectionEntity) {
        Map<String, Object> params = new HashMap<>();
        params.put("line_id", sectionEntity.getLineId());
        params.put("up_station_id", sectionEntity.getUpStationId());
        params.put("down_station_id", sectionEntity.getDownStationId());
        params.put("distance", sectionEntity.getDistance());

        return insertAction.executeAndReturnKey(params).longValue();
    }

    public Optional<SectionEntity> findByUpStationId(final Long upStationId) {
        String sql = "select * from SECTIONS where up_station_id = ?";
        try {
            return Optional.of(jdbcTemplate.queryForObject(sql, sectionRowMapper, upStationId));
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    public void deleteById(final Long id) {
        String sql = "DELETE FROM SECTIONS WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

    public List<SectionEntity> findAll() {
        String sql = "select * from SECTIONS";
        return jdbcTemplate.query(sql, sectionRowMapper);
    }

    public Optional<SectionEntity> findLeftSectionByStationId(Long stationId) {
        String sql = "select * from sections se " +
                "where se.down_station_id = ?";
        try {
            return Optional.of(jdbcTemplate.queryForObject(sql, sectionRowMapper, stationId));
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    public Optional<SectionEntity> findRightSectionByStationId(Long stationId) {
        String sql = "select * from sections se " +
                "where se.up_station_id = ?";
        try {
            return Optional.of(jdbcTemplate.queryForObject(sql, sectionRowMapper, stationId));
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

}
