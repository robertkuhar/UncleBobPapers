package org.rekdev.pasth.proxymodel;

import java.sql.*;
import java.util.*;

public class DB {

    private static Connection con;

    public static void init() throws Exception {
        // Class.forName( "sun.jdbc.odbc.JdbcOdbcDriver" );
        // con = DriverManager.getConnection( "jdbc:odbc:PPP Shopping Cart" );
        Class.forName( "org.hsqldb.jdbc.JDBCDriver" );
        con = DriverManager.getConnection( "jdbc:hsqldb:mem:myunittests", "SA", "" );
    }

    public static void createTables() throws Exception {
        String productsTableSQL = "create memory table Products ( sku varchar(256) not null primary key, name varchar(256), price integer );";
        String ordersTableSQL = "create memory table Orders ( orderId integer not null primary key, customerId varchar(256) );";
        String itemsTableSQL = "create memory table Items ( orderId integer, qty integer, sku varchar(256) );";
        con.createStatement().execute( String.format( "%s\n%s\n%s", productsTableSQL, ordersTableSQL, itemsTableSQL ) );
    }

    public static void dropTables() throws Exception {
        String itemsTableSQL = "drop table Items;";
        String ordersTableSQL = "drop table Orders;";
        String productsTableSQL = "drop table Products;";
        con.createStatement().execute( String.format( "%s\n%s\n%s", itemsTableSQL, ordersTableSQL, productsTableSQL ) );
    }

    public static void store( ProductData pd ) throws Exception {
        PreparedStatement s = buildInsertionStatement( pd );
        executeStatement( s );
    }

    private static PreparedStatement buildInsertionStatement( ProductData pd ) throws SQLException {
        PreparedStatement s = con.prepareStatement( "INSERT into Products VALUES (?, ?, ?)" );
        s.setString( 1, pd.sku );
        s.setString( 2, pd.name );
        s.setInt( 3, pd.price );
        return s;
    }

    public static ProductData getProductData( String sku ) throws Exception {
        PreparedStatement s = buildProductQueryStatement( sku );
        ResultSet rs = executeQueryStatement( s );
        ProductData pd = ( rs.next() ) ? extractProductDataFromResultSet( rs ) : null;
        rs.close();
        s.close();
        return pd;
    }

    private static PreparedStatement buildProductQueryStatement( String sku ) throws SQLException {
        PreparedStatement s = con.prepareStatement( "SELECT * FROM Products WHERE sku = ?;" );
        s.setString( 1, sku );
        return s;
    }

    private static ProductData extractProductDataFromResultSet( ResultSet rs ) throws SQLException {
        ProductData pd = new ProductData();
        pd.sku = rs.getString( 1 );
        pd.name = rs.getString( 2 );
        pd.price = rs.getInt( 3 );
        return pd;
    }

    public static void deleteProductData( String sku ) throws Exception {
        executeStatement( buildProductDeleteStatement( sku ) );
    }

    private static PreparedStatement buildProductDeleteStatement( String sku ) throws SQLException {
        PreparedStatement s = con.prepareStatement( "DELETE from Products where sku = ?" );
        s.setString( 1, sku );
        return s;
    }

    private static void executeStatement( PreparedStatement s ) throws SQLException {
        s.execute();
        s.close();
    }

    private static ResultSet executeQueryStatement( PreparedStatement s ) throws SQLException {
        ResultSet rs = s.executeQuery();
        // rs.next();
        return rs;
    }

    public static OrderData newOrder( String customerId ) throws Exception {
        int newMaxOrderId = getMaxOrderId() + 1;
        PreparedStatement s = con.prepareStatement( "INSERT into Orders( orderId, customerId ) VALUES ( ?, ? );" );
        s.setInt( 1, newMaxOrderId );
        s.setString( 2, customerId );
        executeStatement( s );
        return new OrderData( newMaxOrderId, customerId );
    }

    private static int getMaxOrderId() throws SQLException {
        Statement qs = con.createStatement();
        ResultSet rs = qs.executeQuery( "SELECT max( orderId ) FROM Orders;" );
        // rs.next();
        int maxOrderId = rs.next() ? rs.getInt( 1 ) : 0;
        rs.close();
        return maxOrderId;
    }

    public static void store( ItemData id ) throws Exception {
        PreparedStatement s = buildItemInsertionStatement( id );
        executeStatement( s );
    }

    private static PreparedStatement buildItemInsertionStatement( ItemData id ) throws SQLException {
        PreparedStatement s = con.prepareStatement( "INSERT into Items( orderId, qty, sku ) VALUES ( ?, ?, ? )" );
        s.setInt( 1, id.orderId );
        s.setInt( 2, id.qty );
        s.setString( 3, id.sku );
        return s;
    }

    public static List<ItemData> getItemsForOrder( int orderId ) throws Exception {
        PreparedStatement s = buildItemsForOrderQueryStatement( orderId );
        ResultSet rs = s.executeQuery();
        List<ItemData> itemsForOrder = extractItemDataFromResultSet( rs );
        rs.close();
        s.close();
        return itemsForOrder;
    }

    private static PreparedStatement buildItemsForOrderQueryStatement( int orderId ) throws SQLException {
        PreparedStatement s = con.prepareStatement( "SELECT orderId, qty, sku FROM Items WHERE orderId = ?;" );
        s.setInt( 1, orderId );
        return s;
    }

    private static List<ItemData> extractItemDataFromResultSet( ResultSet rs ) throws SQLException {
        List<ItemData> itemsForOrder = new LinkedList<ItemData>();
        while ( rs.next() ) {
            ItemData itemData = new ItemData();
            itemData.orderId = rs.getInt( "orderId" );
            itemData.qty = rs.getInt( "qty" );
            itemData.sku = rs.getString( "sku" );
            itemsForOrder.add( itemData );
        }
        return itemsForOrder;
    }

    public static OrderData getOrderData( int orderId ) throws SQLException {
        PreparedStatement s = con.prepareStatement( "Select customerId FROM ORDERS WHERE orderid = ?;" );
        s.setInt( 1, orderId );
        ResultSet rs = s.executeQuery();
        OrderData od = null;
        if ( rs.next() )
            od = new OrderData( orderId, rs.getString( "customerId" ) );
        rs.close();
        s.close();
        return od;
    }

    public static void close() throws Exception {
        con.close();
    }

    public static void store( OrderData orderData ) throws Exception {
        PreparedStatement s = buildOrderInsertionStatement( orderData );
        executeStatement( s );

    }

    private static PreparedStatement buildOrderInsertionStatement( OrderData od ) throws SQLException {
        PreparedStatement s = con.prepareStatement( "INSERT into Orders( orderId, customerId ) VALUES ( ?, ? )" );
        s.setInt( 1, od.orderId );
        s.setString( 2, od.customerId );
        return s;
    }

}
