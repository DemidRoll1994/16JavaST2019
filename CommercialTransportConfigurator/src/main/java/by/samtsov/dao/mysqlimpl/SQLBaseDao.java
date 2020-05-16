package by.samtsov.dao.mysqlimpl;

import java.sql.Connection;

public abstract class SQLBaseDao {

    Connection connection = null;

    public void setConnection(Connection newConnection){
        connection=newConnection;
    }

}
