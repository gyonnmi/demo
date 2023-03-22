package com.example.demo.repository;

import com.example.demo.domain.Nekomaru;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcInsertOperations;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

// Spring JDBC를 이용해서 Database 프로그래밍
// @Repository는 @Component 이고 컨테이너가 관리하는 Bean이 된다.
@Repository
public class NekomaruDao {
    // NamedParameterJdbcTemplate는 ? 대신 :컬럼명의 형태로 사용 가능하다.
    private final NamedParameterJdbcTemplate jdbcTemplate; // 필드를 final로 선언하면 반드시 생성자에서 초기화 한다.
    // SimpleJdbcInsertOperations는 SQL 없이 insert를 도와주는 인터페이스
    private SimpleJdbcInsertOperations insertAction; // insert를 쉽게 하도록 도와주는 인터페이스

    // 생성자에 파라미터를 넣어주면 스프링 부트가 자동으로 주입한다. 생성자 주입.
    public NekomaruDao(DataSource dataSource) {
        System.out.println("NekomaruDao 생성자 호출!");
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource); // DataSource를 넣어줘야 한다.

        insertAction = new SimpleJdbcInsert(dataSource).withTableName("nekomaru"); // insert할 테이블 이름을 넣어준다.
    }

    // 데이터 추가 성공하면 1, 실패하면 0
    public boolean insertNeko(Nekomaru nekomaru) {
//        String sql = "INSERT INTO nekomaru(neko_id, neko_name) VALUES (?, ?)";
//        // update 메소드는 insert, update, delete SQL문을 실행할 때 사용한다.
//        int result = jdbcTemplate.update(sql, nekomaru.getNeko_id(), nekomaru.getNeko_name());
//        return result == 1;
        // nekomaru는 neko_id, neko_name 프로퍼티를 가지고 있다.
        // INSERT INTO nekomaru(neko_id, neko_name) VALUES (:neko_id, :neko_name)
        // 위와 같은 SQL을 SimpleJdbcInsert가 내부적으로 만든다.
        // Nekomaru 클래스의 프로퍼티 이름과 컬럼명의 규칙이 맞아야 한다.
        SqlParameterSource params = new BeanPropertySqlParameterSource(nekomaru); // nekomaru 객체의 필드명과 테이블의 컬럼명이 같아야 한다.
        int result = insertAction.execute(params);
        return result == 1;
    }

    // NamedParameterJdbcTemplate 사용
    public boolean deleteNeko(int neko_id) {
        String sql = "DELETE FROM nekomaru WHERE neko_id = :neko_id";
        SqlParameterSource params = new MapSqlParameterSource("neko_id", neko_id);
        int result = jdbcTemplate.update(sql, params);
        return result == 1;
    }

    // 1건의 데이터를 읽어오는 메소드
    public Nekomaru getNekomaru(int neko_id) {
        String sql = "SELECT neko_id, neko_name FROM nekomaru WHERE neko_id = :neko_id"; // neko_id는 PK니까 1건 or 0건의 데이터가 조회된다.
        // queryForObject 메소드는 1건 또는 0건을 읽어오는 메소드.
        try {
            SqlParameterSource params = new MapSqlParameterSource("neko_id", neko_id);
            RowMapper<Nekomaru> nekomaruRowMapper = BeanPropertyRowMapper.newInstance(Nekomaru.class);
            return jdbcTemplate.queryForObject(sql, params, nekomaruRowMapper);
        } catch (Exception e) {
            return null;
        }
    }

    // 모든 데이터를 읽어오는 메소드
    public List<Nekomaru> getNekos() {
        String sql = "SELECT neko_id, neko_name FROM nekomaru";
        RowMapper<Nekomaru> nekomaruRowMapper = BeanPropertyRowMapper.newInstance(Nekomaru.class);
        // query 메소드는 여러건의 결과를 구할 때 사용하는 메소드.
        return (List<Nekomaru>) jdbcTemplate.query(sql, nekomaruRowMapper);
    }
}
