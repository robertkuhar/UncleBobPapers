package org.rekdev.pasth.proxymodel.tests;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.*;
import org.rekdev.pasth.proxymodel.*;

public class DBTest {
    @Before
    public void setup() throws Exception {
        DB.init();
        DB.createTables();
    }

    @After
    public void tearDown() throws Exception {
        DB.dropTables();
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

    @Test
    public void testOrderKeyGeneration() throws Exception {
        OrderData o1 = DB.newOrder( "Bob" );
        OrderData o2 = DB.newOrder( "Bill" );
        int firstOrderId = o1.orderId;
        int secondOrderId = o2.orderId;
        assertEquals( 1, firstOrderId );
        assertEquals( firstOrderId + 1, secondOrderId );
    }

    @Test
    public void testStoreItem() throws Exception {
        ItemData storedItem = new ItemData( 1, 3, "sku" );
        DB.store( storedItem );
        List<ItemData> retrievedItems = DB.getItemsForOrder( 1 );
        assertEquals( 1, retrievedItems.size() );
        assertEquals( storedItem, retrievedItems.get( 0 ) );
    }

    @Test
    public void testNoItems() throws Exception {
        List<ItemData> retrievedItems = DB.getItemsForOrder( 42 );
        assertEquals( 0, retrievedItems.size() );
    }
}
