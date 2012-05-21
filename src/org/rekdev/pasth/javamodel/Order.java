package org.rekdev.pasth.javamodel;

import java.math.*;
import java.util.*;

public class Order {
    private Customer customer;
    private List<Item> items;

    public Order( Customer customer ) {
        this( customer, Collections.<Item> emptyList() );
    }

    public Order( Customer customer, List<Item> items ) {
        this.customer = customer;
        this.items = new ArrayList<Item>( items );
    }

    public void addItem( Product product, int qty ) {
        Item item = new Item( this, product, qty );
        items.add( item );
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer( Customer customer ) {
        this.customer = customer;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems( List<Item> items ) {
        this.items = items;
    }

    public BigDecimal total() {
        BigDecimal total = new BigDecimal( 0 );
        for ( Item item : items ) {
            BigDecimal pricePer = item.getProduct().getPrice();
            int qty = item.getQty();
            total = total.add( pricePer.multiply( new BigDecimal( qty ) ) );
        }
        return total;
    }

}
