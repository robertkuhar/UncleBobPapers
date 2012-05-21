package org.rekdev.pasth.jdbcmodel;

import java.sql.*;

public class Transaction {
    private Connection connection;
    
    public Transaction( Connection connection ) {
        this.connection = connection;
    }
    
    public Connection getConnection() {
        return connection;
    }
}
