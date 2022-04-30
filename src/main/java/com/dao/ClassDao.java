package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.entity.ClassMaster;
public class ClassDao {
	String databaseURL = "jdbc:mysql://localhost:3306/classdb";
    String user = "root";
    String password = "";
    
    public List<ClassMaster> list() throws SQLException, ClassNotFoundException {
        List<ClassMaster> classList = new ArrayList<>();
        Class.forName("com.mysql.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection(databaseURL, user, password)) {
            String sql = "select * from classmaster order by class_name;";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
             
            while (result.next()) {
                int id = result.getInt("class_id");
                String name = result.getString("class_name");
                ClassMaster cls = new ClassMaster(id,name);
                     
                classList.add(cls);
            }          
             
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }      
         
        return classList;
    }
}
