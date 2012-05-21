package org.rekdev.pasth.jdbcmodel;

import java.sql.*;

public class AddItemTransaction extends Transaction {

    public AddItemTransaction( Connection connection ) {
        super( connection );
    }

    public void addItem( int orderId, String sku, int qty ) throws SQLException {
        Statement s = getConnection().createStatement();
        s.executeUpdate( String.format( "insert into items values ( %d, %s, %d )", orderId, sku, qty ) );
    }

}
