package by.samtsov.controller;

import java.sql.*;

public class controller {
    public static void main(String[] args) {
        try {
            Connection cn = DriverManager.getConnection("jdbc:mysql" +
                            "://localhost:3306/vehiclessales?useUnicode=true" +
                            "&characterEncoding=UTF-8&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    "vehiclesSalesUser1", "vehiclesSalesUser1Password");
            Statement st = cn.createStatement();
            ResultSet resultSet = st.executeQuery("select " +
                    "authentication_string from user");
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                int phone = resultSet.getInt(3);
                String name = resultSet.getString(2);
                System.out.println(id + " " + phone + " " + name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
