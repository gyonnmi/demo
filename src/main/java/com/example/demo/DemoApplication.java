package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    // main메소드는 스프링이 관리하지 않는다.
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    // 자동으로 주입 받는다.
    @Autowired
    DataSource dataSource;

    // DataSource Bean(스프링이 관리하는 객체)
    @Override
    public void run(String... args) throws Exception {
//        System.out.println("스프링부트가 관리하는 빈을 사용할 수 있다.");
//
//        // JDBC 연결
//        Connection conn = dataSource.getConnection();
//        PreparedStatement ps = conn.prepareStatement("select neko_id, neko_name from nekomaru");
//        ResultSet rs = ps.executeQuery();
//        while(rs.next()) {
//            int neko_id = rs.getInt("neko_id");
//            String neko_name = rs.getString("neko_name");
//            System.out.println(neko_id + ", " + neko_name);
//        }
//
//        rs.close();
//        ps.close();
//        conn.close();

    }
}
