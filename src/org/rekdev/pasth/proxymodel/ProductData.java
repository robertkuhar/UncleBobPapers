package org.rekdev.pasth.proxymodel;

import com.google.common.base.*;

public class ProductData {
    public String name;
    public int price;
    public String sku;

    public ProductData() {
    }

    public ProductData( String name, int price, String sku ) {
        this.name = name;
        this.price = price;
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
        ProductData that = (ProductData) obj;
        return ( Objects.equal( name, that.name ) && Objects.equal( price, that.price ) && Objects.equal( sku, that.sku ) );
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode( name, price, sku );
        return result;
    }

}
