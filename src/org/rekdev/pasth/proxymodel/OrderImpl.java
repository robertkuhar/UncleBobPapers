package org.rekdev.pasth.proxymodel;

import java.util.*;

public class OrderImpl implements Order {
    private String customerId;
    private List<Item> items = new ArrayList<Item>();
    
    public OrderImpl( String customerId ) {
        this.customerId = customerId;
    }

    @Override
    public String getCustomerId() {
        return customerId;
    }

    @Override
    public void setCustomerId( String customerId ) {
        this.customerId = customerId;
    }

    @Override
    public void addItem( Product p, int qty ) {
        Item item = new ItemImpl( this, p, qty );
        items.add( item );
    }

    @Override
    public int total() {
        int orderTotal = 0;
        try {
            for( Item item : items ) {
                int itemTotal = item.getProduct().getPrice() * item.getQty();
                orderTotal += itemTotal;
            }
        } catch(Exception e) {
            // FIXME: causedBy beats e.toString()!
            throw new Error( e.toString() );
        }
        return orderTotal;
    }

}
