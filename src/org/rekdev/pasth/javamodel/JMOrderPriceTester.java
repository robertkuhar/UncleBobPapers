package org.rekdev.pasth.javamodel;

import static org.junit.Assert.*;

import java.math.*;

import org.junit.*;

public class JMOrderPriceTester {

    @Test
    public void testOrderPrice() {
        Customer customer = new Customer( "Bob", "Seattle", "cash" );
        Product toothpaste = new Product( "Toothpaste", new BigDecimal( "1.29" ) );
        Order order = new Order( customer );
        order.addItem( toothpaste, 1 );
        assertEquals( new BigDecimal( "1.29" ), order.total() );

        Product mouthwash = new Product( "Mouthwash", new BigDecimal( "3.42" ) );
        order.addItem( mouthwash, 2 );
        assertEquals( new BigDecimal( "8.13" ), order.total() );
    }

}
