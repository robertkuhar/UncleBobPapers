package org.rekdev.pasth.proxymodel;

import java.sql.*;
import java.util.*;

public class OrderProxy implements Order {
    private int orderId;

    public OrderProxy( int orderId ) {
        this.orderId = orderId;
    }

    @Override
    public String getCustomerId() {
        try {
            OrderData orderData = DB.getOrderData( orderId );
            return orderData.customerId;
        } catch ( SQLException e ) {
            // FIXME: causedBy beats e.toString()!
            throw new Error( e.toString() );
        }
    }

    @Override
    public void setCustomerId( String customerId ) {
        try {
            OrderData orderData = DB.getOrderData( orderId );
            orderData.customerId = customerId;
            DB.store( orderData );
        } catch ( Exception e ) {
            // FIXME: cause beats e.toString()!
            throw new Error( e.toString() );
        }
    }

    @Override
    public void addItem( Product p, int qty ) {
        try {
            ItemData itemData = new ItemData( orderId, qty, p.getSku() );
            DB.store( itemData );
        } catch ( Exception e ) {
            // FIXME: cause beats e.toString()!
            throw new Error( e.toString() );
        }
    }

    @Override
    public int total() {
        try {
            OrderImpl orderImpl = new OrderImpl( getCustomerId() );
            List<ItemData> orderItems = DB.getItemsForOrder( orderId );
            for ( ItemData orderItem : orderItems ) {
                orderImpl.addItem( new ProductProxy( orderItem.sku ), orderItem.qty );
            }
            return orderImpl.total();
        } catch ( Exception e ) {
            // FIXME: cause beats e.toString()!
            throw new Error( e.toString() );
        }
    }

    public int getOrderId() {
        return orderId;
    }

}
