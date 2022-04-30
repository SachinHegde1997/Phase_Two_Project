package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.entity.Subject;
public class SubjectDao {
	String databaseURL = "jdbc:mysql://localhost:3306/classdb";
    String user = "root";
    String password = "";
    
    public List<Subject> list() throws SQLException, ClassNotFoundException {
        List<Subject> subList = new ArrayList<>();
        Class.forName("com.mysql.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection(databaseURL, user, password)) {
            String sql = "select * from subject order by subject_name;";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
             
            while (result.next()) {
                int id = result.getInt("subject_id");
                String name = result.getString("subject_name");
                Subject subs = new Subject(id,name);
                     
                subList.add(subs);
            }          
             
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }      
         
        return subList;
    }
}
