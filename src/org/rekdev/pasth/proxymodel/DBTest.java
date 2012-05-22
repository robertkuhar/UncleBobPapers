package org.rekdev.pasth.proxymodel;

import static org.junit.Assert.*;

import org.junit.*;

public class DBTest {
    @Before
    public void setup() throws Exception {
        DB.init();
    }

    @After
    public void tearDown() throws Exception {
        DB.close();
    }

    @Test
    public void testStoreProduct() throws Exception {
        ProductData storedProduct = new ProductData();
        storedProduct.name = "MyProduct";
        storedProduct.price = 1234;
        storedProduct.sku = "999";
        DB.store( storedProduct );
        ProductData retreivedProduct = DB.getProductData( "999" );
        assertEquals( storedProduct, retreivedProduct );
        DB.deleteProductData( "999" );
        retreivedProduct = DB.getProductData( "999" );
        assertNull( retreivedProduct );
    }

}
