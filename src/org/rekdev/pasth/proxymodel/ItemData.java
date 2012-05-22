package org.rekdev.pasth.proxymodel;

import com.google.common.base.*;

public class ItemData {
    public int orderId;
    public int qty;
    public String sku;

    public ItemData() {
    }

    public ItemData( int orderId, int qty, String sku ) {
        this.orderId = orderId;
        this.qty = qty;
        this.sku = sku;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) {
            return true;
        }
        if ( obj == null || getClass() != obj.getClass() ) {
            return false;
        }
        ItemData that = (ItemData) obj;
        return ( Objects.equal( orderId, that.orderId ) && Objects.equal( qty, that.qty ) && Objects.equal( sku, that.sku ) );
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode( orderId, qty, sku );
        return result;
    }

}
