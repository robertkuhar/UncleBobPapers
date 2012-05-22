package org.rekdev.pasth.proxymodel;

import java.sql.*;

public class DB {
    
    private static Connection con;

    public static void init() throws Exception {
        // Class.forName( "sun.jdbc.odbc.JdbcOdbcDriver" );
        // con = DriverManager.getConnection( "jdbc:odbc:PPP Shopping Cart" );
        Class.forName( "org.hsqldb.jdbc.JDBCDriver" );
        con = DriverManager.getConnection( "jdbc:hsqldb:mem:myunittests", "SA", "" );
        String bookTableSQL = "create memory table Products ( "
                + "sku varchar(256) not null primary key, "
                + "name varchar(256), "
                + "price integer );";
        Statement st = con.createStatement();
        st.execute( bookTableSQL );
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

    public static void close() throws Exception {
        con.close();
    }
}
