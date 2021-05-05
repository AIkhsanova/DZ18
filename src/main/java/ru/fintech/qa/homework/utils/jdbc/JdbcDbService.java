package ru.fintech.qa.homework.utils.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcDbService {
    public final String executeQueryGetColumn(final String column, final String sql, final Connection connection) {
        String value = "";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                value = resultSet.getString(column);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return value;
    }

    public final int executeUpdate(final String sql, final Connection connection) {
        int value = 0;

        try {
            Statement statement = connection.createStatement();
            value = statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return value;

    }

    public final int executeQueryCount(final String column, final String sql, final Connection connection) {
        int value = 0;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                value++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return value;
    }
}

