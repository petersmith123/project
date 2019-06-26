package com.springbasetest;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtil {

    public  static Connection getCon() {
        InputStream inputStream = JdbcConnection.class.getClassLoader().getResourceAsStream("jdbc/jdbc.properties");
        Properties prop = new Properties();
        try {
            prop.load(inputStream);
            String driver = prop.getProperty("driver");
            String url = prop.getProperty("url");
            String name = prop.getProperty("username");
            String psw = prop.getProperty("password");
            Class.forName(driver);
            Connection con = DriverManager.getConnection(url, name, psw);
            return con;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public  static  void main(String[]args){
        getCon();
    }
}
