package org.rekdev.pasth.javamodel;

import java.math.*;
import java.util.*;

public class Product {
    private String name;
    private BigDecimal price;
    private List<SKU> skus;
    
    public Product( String name, BigDecimal price ) {
        this( name, price, Collections.<SKU>emptyList() );
    }
    
    public Product( String name, BigDecimal price, List<SKU> skus) {
        this.name = name;
        this.price = price;
        this.skus = new ArrayList<SKU>( skus );
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice( BigDecimal price ) {
        this.price = price;
    }

    public List<SKU> getSkus() {
        return skus;
    }

    public void setSkus( List<SKU> skus ) {
        this.skus = skus;
    }

}
