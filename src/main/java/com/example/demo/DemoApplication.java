package com.example.demo;

import com.example.demo.domain.Nekomaru;
import com.example.demo.repository.NekomaruDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    // main메소드는 스프링이 관리하지 않는다.
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    // 자동으로 주입 받는다.
    @Autowired
    NekomaruDao nekomaruDao;

    @Override
    public void run(String... args) throws Exception {
        Nekomaru nekomaru = new Nekomaru();
        nekomaru.setNeko_id(4);
        nekomaru.setNeko_name("dao");
        nekomaruDao.insertNeko(nekomaru);

//        boolean flag = nekomaruDao.deleteNeko(4);
//        System.out.println("flag : " + flag);

//        Nekomaru nekomaru = nekomaruDao.getNekomaru(5);
//        if (nekomaru != null) {
//            System.out.println(nekomaru.getNeko_id() + ", " + nekomaru.getNeko_name());
//        }

//        List<Nekomaru> list = (List<Nekomaru>) nekomaruDao.getNekos();
//        for(Nekomaru nekomaru : list)
//            System.out.println(nekomaru.getNeko_id() + ", " + nekomaru.getNeko_name());

    }
}
