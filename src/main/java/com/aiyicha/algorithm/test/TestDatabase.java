package com.aiyicha.algorithm.test;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class TestDatabase {

    public static void main(String[] args) {
        List<Connection> connectionList = new ArrayList<>();
        try {
            for (int i = 0; i < 1000; i++) {
                // 注册驱动，并建立连接对象。
                Class.forName("com.sybase.jdbc4.jdbc.SybDriver").newInstance();
                // 获得数据连接。
                Connection conn = java.sql.DriverManager.getConnection(
                        "jdbc:sybase:Tds:147.91.1.131:4333/wxpt?charset=cp936",
                        "wxuser", "userwxp");
                if (conn != null) {
                    System.out.println(conn.isClosed());
                }
                connectionList.add(conn);
            }
            System.out.println("---------------------------------------------");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            for (Connection connection : connectionList) {
                if (connection != null) {
                    try{
                        connection.close();
                        System.out.println(connection.isClosed());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        }

    }
}
