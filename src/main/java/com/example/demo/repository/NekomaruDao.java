package com.example.demo.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

// Spring JDBC를 이용해서 Database 프로그래밍
// @Repository는 @Component 이고 컨테이너가 관리하는 Bean이 된다.
@Repository
public class NekomaruDao {
    private final JdbcTemplate jdbcTemplate; // 필드를 final로 선언하면 반드시 생성자에서 초기화 한다.

    // 생성자에 파라미터를 넣어주면 스프링 부트가 자동으로 주입한다.
    public NekomaruDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(); // DataSource를 넣어줘야 한다.
    }
}
