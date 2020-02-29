package by.samtsov.service.mysql;

import by.samtsov.dao.transaction.Transaction;

public abstract class MysqlService {

    protected Transaction transaction = null;

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
