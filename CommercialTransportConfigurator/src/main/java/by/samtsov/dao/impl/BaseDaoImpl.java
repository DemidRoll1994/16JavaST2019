package by.samtsov.dao.impl;

import java.sql.Connection;

public class BaseDaoImpl  {

    Connection connection = null;

    public void setConnection(Connection newConnection){
        connection=newConnection;
    }

}
