package org.cloud.note;

import com.alibaba.druid.pool.DruidDataSource;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@SpringBootTest
public class NoteApplicationTests {

    @Test
    void contextLoads() throws SQLException {
        DataSource datSource =new DruidDataSource();
        ((DruidDataSource) datSource).setUsername("root");
        ((DruidDataSource) datSource).setPassword("1122");
        ((DruidDataSource) datSource).setDriverClassName("com.mysql.cj.jdbc.Driver");
        ((DruidDataSource) datSource).setUrl("jdbc:mysql://127.0.0.1:3306/note?serverTimezone=GMT%2B8&characterEncoding=utf-8&useSSL=false");
        Connection connection =datSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("select *from user");
        ResultSet resultSet =statement.executeQuery();
        while (resultSet.next()){
              System.out.println(resultSet.getString("user_name"));
        }


        DataSource datSource1 =new MysqlDataSource();
        ((MysqlDataSource) datSource1).setUser("root");
        ((MysqlDataSource) datSource1).setPassword("1122");

        ((MysqlDataSource) datSource1).setUrl("jdbc:mysql://127.0.0.1:3306/note?serverTimezone=GMT%2B8&characterEncoding=utf-8&useSSL=false");
        Connection connection1 =datSource1.getConnection();
        PreparedStatement statement1 = connection1.prepareStatement("select *from user");
        ResultSet resultSet1 =statement1.executeQuery();
        while (resultSet1.next()){
            System.out.println(resultSet1.getString(6));
        }


    }

}
