package org.rekdev.pasth.proxymodel.tests;

import static org.junit.Assert.*;

import org.junit.*;
import org.rekdev.pasth.proxymodel.*;

public class ProxyTest {
    private static final String TEST_SKU = "ProxyTest1";
    private static final String TEST_NAME = "ProxyTestName1";

    @Before
    public void setup() throws Exception {
        DB.init();
        DB.createTables();
        ProductData pd = new ProductData();
        pd.sku = TEST_SKU;
        pd.name = TEST_NAME;
        pd.price = 456;
        DB.store( pd );
    }

    @After
    public void tearDown() throws Exception {
        DB.deleteProductData( TEST_SKU );
        DB.dropTables();
        DB.close();
    }

    @Test
    public void testProductProxy() throws Exception {
        Product p = new ProductProxy( TEST_SKU );
        assertEquals( 456, p.getPrice() );
        assertEquals( TEST_NAME, p.getName() );
        assertEquals( TEST_SKU, p.getSku() );
    }

    @Test
    public void testOrderProxyTotal() throws Exception {
        DB.store( new ProductData( "Wheaties", 349, "wheaties" ) );
        DB.store( new ProductData( "Crest", 258, "crest" ) );
        ProductProxy wheaties = new ProductProxy( "wheaties" );
        ProductProxy crest = new ProductProxy( "crest" );
        OrderData od = DB.newOrder( "testOrderProxy" );
        OrderProxy order = new OrderProxy( od.orderId );
        order.addItem( crest, 1 );
        order.addItem( wheaties, 2 );
        assertEquals( 956, order.total() );
    }
}
