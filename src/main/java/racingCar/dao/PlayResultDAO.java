package racingCar.dao;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import racingCar.domain.RacingCar;

@Repository
public class PlayResultDAO {
    private JdbcTemplate jdbcTemplate;

    public PlayResultDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int insert(int trialCount, String winners, Timestamp playTime) {
        String sql = "insert into PLAY_RESULT (trial_count, winners, play_time) values (?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, trialCount);
            ps.setString(2, winners);
            ps.setTimestamp(3, playTime);
            return ps;
        }, keyHolder);

        return (int) keyHolder.getKeys().get("ID");
    }
}