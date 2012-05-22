package org.rekdev.pasth.proxymodel;

import com.google.common.base.*;

public class OrderData {
    public int orderId;
    public String customerId;

    public OrderData() {
    }

    public OrderData( int orderId, String customerId ) {
        this.orderId = orderId;
        this.customerId = customerId;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) {
            return true;
        }
        if ( obj == null || getClass() != obj.getClass() ) {
            return false;
        }
        OrderData that = (OrderData) obj;
        return ( Objects.equal( orderId, that.orderId ) && Objects.equal( customerId, that.customerId ) );
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode( orderId, customerId );
        return result;
    }
}
