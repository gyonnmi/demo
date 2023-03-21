package com.example.demo.repository;

import com.example.demo.domain.Nekomaru;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

// Spring JDBC를 이용해서 Database 프로그래밍
// @Repository는 @Component 이고 컨테이너가 관리하는 Bean이 된다.
@Repository
public class NekomaruDao {
    private final JdbcTemplate jdbcTemplate; // 필드를 final로 선언하면 반드시 생성자에서 초기화 한다.

    // 생성자에 파라미터를 넣어주면 스프링 부트가 자동으로 주입한다. 생성자 주입.
    public NekomaruDao(DataSource dataSource) {
        System.out.println("NekomaruDao 생성자 호출!");
        jdbcTemplate = new JdbcTemplate(dataSource); // DataSource를 넣어줘야 한다.
    }

    // 데이터 추가 성공하면 1, 실패하면 0
    public boolean insertNeko(Nekomaru nekomaru) {
        String sql = "INSERT INTO nekomaru(neko_id, neko_name) VALUES (?, ?)";
        // update 메소드는 insert, update, delete SQL문을 실행할 때 사용한다.
        int result = jdbcTemplate.update(sql, nekomaru.getNeko_id(), nekomaru.getNeko_name());
        return result == 1;
    }

    public boolean deleteNeko(int neko_id) {
        String sql = "DELETE FROM nekomaru WHERE neko_id = ?";
        int result = jdbcTemplate.update(sql, neko_id);
        return result == 1;
    }

    public Nekomaru getNekomaru(int neko_id) {
      String sql = "SELECT neko_id, neko_name FROM nekomaru WHERE neko_id = ?";
      return jdbcTemplate.queryForObject(sql, ((rs, rowNum) -> {
          Nekomaru nekomaru = new Nekomaru();
          nekomaru.setNeko_id(rs.getInt("neko_id"));
          nekomaru.setNeko_name(rs.getString("neko_name"));
          return nekomaru;
      }), neko_id);
    };

}
