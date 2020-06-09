package com.util;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Dbutil {


    public static Vector<Connection> connectionPool = new Vector<Connection>();

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            for (int i = 0; i < 10; i++) {
                Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/s82", "root", "12369874");
                connectionPool.add(connection);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static Connection getConnection() {
        Connection connection = connectionPool.get(0);
        connectionPool.remove(0);
        return connection;

    }

    public static void releaseConnection(Connection connection) {
        connectionPool.add(connection);
    }

    public static int zsg(String sql, Object... p) {

        Connection connection = getConnection();

        int n = 0;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            for (int i = 0; i < p.length; i++) {
                ps.setObject(i + 1, p[i]);
            }

            n = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            releaseConnection(connection);
        }
        return n;
    }

    public static List query(String sql, Class c, Object... p) {
        Connection connection = getConnection();
        List list = new ArrayList();

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            for (int i = 0; i < p.length; i++) {
                ps.setObject(i + 1, p[i]);
            }

            ResultSet rs = ps.executeQuery();

            ResultSetMetaData rsmd = rs.getMetaData();
            int count = rsmd.getColumnCount();//得到总列数

            while(rs.next()){
                Object object = c.newInstance();

                for (int i = 0; i <count ; i++) {
                    String filedname = rsmd.getColumnLabel(i+1);
                    Field field = c.getDeclaredField(filedname);
                    field.setAccessible(true);
                    field.set(object , rs.getObject(i+1));

                }
                list.add(object);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            releaseConnection(connection);
        }

        return list ;

    }

    public static int uniqueQuery(String sql, Object... p) {
        Connection connection = getConnection();

        int n = 0;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            for (int i = 0; i < p.length; i++) {
                ps.setObject(i + 1, p[i]);
            }

            ResultSet rs = ps.executeQuery();

            rs.next();
            n = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            releaseConnection(connection);
        }

        return n;


    }

}
