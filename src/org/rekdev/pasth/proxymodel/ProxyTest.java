package org.rekdev.pasth.proxymodel;

import static org.junit.Assert.*;

import org.junit.*;

public class ProxyTest {
    private static final String TEST_SKU = "ProxyTest1";
    private static final String TEST_NAME = "ProxyTestName1";

    @Before
    public void setup() throws Exception {
        DB.init();
        ProductData pd = new ProductData();
        pd.sku = TEST_SKU;
        pd.name = TEST_NAME;
        pd.price = 456;
        DB.store( pd );
    }

    @After
    public void tearDown() throws Exception {
        DB.deleteProductData( TEST_SKU );
        DB.close();
    }

    @Test
    public void testProductProxy() throws Exception {
        Product p = new ProductProxy( TEST_SKU );
        assertEquals( 456, p.getPrice() );
        assertEquals( TEST_NAME, p.getName() );
        assertEquals( TEST_SKU, p.getSku() );
    }
}
