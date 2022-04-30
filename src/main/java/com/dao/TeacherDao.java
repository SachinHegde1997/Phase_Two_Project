package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.entity.TeacherMaster;
public class TeacherDao {
	String databaseURL = "jdbc:mysql://localhost:3306/classdb";
    String user = "root";
    String password = "";
    public List<TeacherMaster> list() throws SQLException, ClassNotFoundException {
        List<TeacherMaster> tList = new ArrayList<>();
        Class.forName("com.mysql.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection(databaseURL, user, password)) {
            String sql = "select * from teachermaster order by teacher_name;";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
             
            while (result.next()) {
                int id = result.getInt("teacher_id");
                String name = result.getString("teacher_name");
                TeacherMaster tch = new TeacherMaster(id,name);
                     
                tList.add(tch);
            }          
             
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }      
         
        return tList;
    }
}
