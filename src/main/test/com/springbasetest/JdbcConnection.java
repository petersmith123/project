package com.springbasetest;
import org.junit.Test;


import java.sql.*;


public class JdbcConnection {




    @Test
    public  void  selectTest(){

        Connection con= null;
        try {
            con = JdbcUtil.getCon();
            con.setAutoCommit(false);
            String sql="select * from teacher";
            PreparedStatement preparedStatement=con.prepareStatement(sql);
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
            int id=  resultSet.getInt("id");
            String name=resultSet.getString("name");
            System.out.println("id:"+id+"---"+"name"+name);
            }
            String sql2="insert into teacher(name,age) values('李永浩',88)";
            PreparedStatement preparedStatement1=con.prepareStatement(sql2);
            preparedStatement1.execute();
                int i=1/0;
            con.commit();




           } catch (Exception e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();

            }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
