package com.myown.demo.repository;

import com.myown.demo.model.User;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.List;
@Component
public class UserDaoJDBC implements Dao<User>{


    @Override
    public User get(int id) {
        return null;
    }

    @Override
    public User getRandomElement() {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public String delete(int id) {
        return null;
    }

    @Override
    public String update(User user, int id) {
        return null;
    }

    @Override
    public String add(User user) {
        String query = "INSERT INTO registered_emails (email) VALUES ('" + user.getEmail() + "');";
        return runQuery(query);
    }

    @Override
    public String deleteAll() {
        String query = "DELETE FROM registered_emails;";
        return runQuery(query);
    }

    @Override
    public String addAll(List<User> t) {
        return null;
    }

    private String runQuery(String query){
        try {
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Codecool3148");
            Statement stmt = con.createStatement();
            stmt.execute(query);
            stmt.close();
            con.close();
        } catch (SQLException e) {
            String errorMessage = e.getMessage();
            System.err.println(errorMessage);
            return errorMessage;
        }
        return "200 ok";
    }
}
